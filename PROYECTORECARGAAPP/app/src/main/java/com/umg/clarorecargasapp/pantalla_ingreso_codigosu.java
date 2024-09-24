package com.umg.clarorecargasapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class pantalla_ingreso_codigosu extends AppCompatActivity {

    private EditText precioEditText, secuenciaEditText;
    private LinearLayout guardarButton, modificarButton, eliminarButton; // Cambiado a LinearLayout
    private DBHelper dbHelper;
    private Spinner spinnerEstado, spinnerTipo;
    private LinearLayout verRegistro, verRegistrosButton;
    private boolean isEditing = false;
    private int storeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_ingreso_codigosu);

        // Initialize EditText and Spinner views
        precioEditText = findViewById(R.id.precioCodigo);
        secuenciaEditText = findViewById(R.id.secuenciaCodigo);
        spinnerEstado = findViewById(R.id.spinnerEstado2);
        spinnerTipo = findViewById(R.id.spinnerTipocodigo);
        // Initialize LinearLayouts for buttons
        guardarButton = findViewById(R.id.btnGuardarCU);
        modificarButton = findViewById(R.id.btnModificarCU);
        eliminarButton = findViewById(R.id.btnEliminarCU);
        verRegistrosButton = findViewById(R.id.btnVerRegistrosCU);
        final TextView textGuardar = findViewById(R.id.text_guardarCU);
        spinnerEstado.setEnabled(false);
        spinnerTipo.setEnabled(false);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnRegresar = findViewById(R.id.btnVolverCU);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(pantalla_ingreso_codigosu.this, MenuDesplegable.class);
            startActivity(intent);
        });

        // Configurar adaptador para el Spinner estados
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estado2_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        // Configurar adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.tipoCodigo_options, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter2);

        verRegistrosButton.setOnClickListener(v -> {
            // Iniciar la nueva actividad para ver registros
            Intent intent = new Intent(pantalla_ingreso_codigosu.this, PantallaDeRegistrosCodigos.class);
            startActivity(intent);
        });

        dbHelper = new DBHelper(this);
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditing) {
                    // Habilitar los componentes cuando se hace clic por primera vez
                    precioEditText.setEnabled(true);
                    secuenciaEditText.setEnabled(true);
                    spinnerEstado.setEnabled(true);
                    spinnerTipo.setEnabled(true);
                    textGuardar.setText("Guardar");
                    modificarButton.setEnabled(false);
                    eliminarButton.setEnabled(false);
                    Toast.makeText(pantalla_ingreso_codigosu.this, "Componentes habilitados", Toast.LENGTH_SHORT).show();
                    isEditing = true;
                } else {
                    // Confirmar si el usuario quiere guardar los datos
                    new AlertDialog.Builder(pantalla_ingreso_codigosu.this)
                            .setTitle("Confirmación")
                            .setMessage("¿Desea guardar los datos?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Guardar los datos en la base de datos
                                    saveData();
                                    Toast.makeText(pantalla_ingreso_codigosu.this, "Datos registrados", Toast.LENGTH_SHORT).show();
                                    precioEditText.setEnabled(false);
                                    secuenciaEditText.setEnabled(false);
                                    spinnerEstado.setEnabled(false);
                                    spinnerTipo.setEnabled(false);
                                    textGuardar.setText("Agregar");
                                    modificarButton.setEnabled(true);
                                    eliminarButton.setEnabled(true);
                                    isEditing = false;
                                    resetComponents(precioEditText, secuenciaEditText, spinnerEstado, spinnerTipo);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // No hacer nada, solo informar al usuario
                                    Toast.makeText(pantalla_ingreso_codigosu.this, "Registro cancelado", Toast.LENGTH_SHORT).show();
                                    modificarButton.setEnabled(true);
                                    eliminarButton.setEnabled(true);
                                    resetComponents(precioEditText, secuenciaEditText, spinnerEstado, spinnerTipo);

                                }
                            })
                            .show();
                }
            }
        });

        modificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditing) {
                    showSelectCodeDialog();
                    precioEditText.setEnabled(true);
                    secuenciaEditText.setEnabled(true);
                    spinnerEstado.setEnabled(true);
                    spinnerTipo.setEnabled(true);
                    guardarButton.setEnabled(false);
                    eliminarButton.setEnabled(false);
                    isEditing = true;
                } else {
                    updateCodeData();
                    Toast.makeText(getApplicationContext(), "Datos modificados exitosamente.", Toast.LENGTH_SHORT).show();
                    precioEditText.setEnabled(false);
                    secuenciaEditText.setEnabled(false);
                    spinnerEstado.setEnabled(false);
                    spinnerTipo.setEnabled(false);
                    guardarButton.setEnabled(true);
                    eliminarButton.setEnabled(true);
                    isEditing = false;
                    resetComponents(precioEditText, secuenciaEditText, spinnerEstado, spinnerTipo);
                }
            }
        });

    }

    private void resetComponents(EditText precioEditText, EditText secuenciaEditText, Spinner spinnerEstado, Spinner spinnerTipo) {
        precioEditText.setText(""); // Limpia el texto del EditText precio
        secuenciaEditText.setText(""); // Limpia el texto del EditText secuencia
        spinnerEstado.setSelection(0); // Reestablece el Spinner estado a la primera opción
        spinnerTipo.setSelection(0); // Reestablece el Spinner tipo a la primera opción
    }

    private void saveData() {
        String tipo = spinnerTipo.getSelectedItem().toString(); // Tipo_codigo
        String precioString = precioEditText.getText().toString(); // Precio_codigo
        String secuencia = secuenciaEditText.getText().toString(); // Secuencia_codigo
        String estado = spinnerEstado.getSelectedItem().toString(); // estado_codigo

        // Verifica si algún campo está vacío
        if (tipo.isEmpty() || precioString.isEmpty() || secuencia.isEmpty() || estado.isEmpty()) {
            // Opcional: Muestra un mensaje de error si los campos están vacíos
            return;
        }

        int precio;
        try {
            precio = Integer.parseInt(precioString); // Convierte el precio en un número entero
        } catch (NumberFormatException e) {
            // Maneja el error si el precio no es un número válido
            return;
        }

        // Inserta los datos en la tabla tbl_codigosRecarga
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tipo_codigo", tipo); // Llena el campo Tipo_codigo
        values.put("Precio_codigo", precio); // Llena el campo Precio_codigo
        values.put("Secuencia_codigo", secuencia); // Llena el campo Secuencia_codigo
        values.put("estado_codigo", estado); // Llena el campo estado_codigo

        // Inserta los valores en la base de datos
        db.insert("tbl_codigosRecarga", null, values);
        db.close(); // Cierra la base de datos
    }

    private void showSelectCodeDialog() {
        // Crear el diálogo
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_seleccionar_codigo);

        // Referencias a los componentes del diálogo
        final Spinner spinnerCodigos = dialog.findViewById(R.id.spinnerCodigos);
        final Spinner spinnerPrecio = dialog.findViewById(R.id.spinnerPrecio);
        final Button btnSeleccionar = dialog.findViewById(R.id.btnSeleccionar);
        final Button btnCancelar = dialog.findViewById(R.id.btnCancelar);

        // Rellenar el spinner con los códigos de recarga
        ArrayAdapter<String> adapterCodigos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getTipoCodigoRecarga());
        adapterCodigos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCodigos.setAdapter(adapterCodigos);

        // Rellenar el spinner con los precios de recarga
        ArrayAdapter<String> adapterPrecios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapterPrecios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrecio.setAdapter(adapterPrecios);
        spinnerPrecio.setEnabled(false); // Deshabilitar el spinner de precios inicialmente

        // Acción para cuando el usuario selecciona un código
        spinnerCodigos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCode = spinnerCodigos.getSelectedItem().toString();
                spinnerPrecio.setEnabled(true); // Habilitar el spinner de precios

                // Actualizar el spinner de precios con el precio del tipo seleccionado
                ArrayList<String> preciosFiltrados = getPreciosRecargaPorTipo(selectedCode);
                ArrayAdapter<String> adapterPreciosFiltrados = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, preciosFiltrados);
                adapterPreciosFiltrados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPrecio.setAdapter(adapterPreciosFiltrados);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerPrecio.setEnabled(false); // Deshabilitar el spinner de precios si no hay selección
            }
        });

        btnSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCode = spinnerCodigos.getSelectedItem().toString();
                String selectedPrecio = spinnerPrecio.getSelectedItem().toString();
                loadCodeData(selectedCode, Integer.parseInt(selectedPrecio));
                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Cierra el diálogo
                isEditing = false;  // Restablece el estado al cancelar
                precioEditText.setEnabled(false);
                secuenciaEditText.setEnabled(false);
                spinnerEstado.setEnabled(false);
                spinnerTipo.setEnabled(false);
                guardarButton.setEnabled(true);
                eliminarButton.setEnabled(true);
            }
        });

        // Configura el diálogo para que no se cancele tocando fuera
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

    // Método para obtener los códigos de recarga desde la base de datos sin duplicados
    private List<String> getTipoCodigoRecarga() {
        Set<String> codigoRecargasSet = new HashSet<>(); // Usamos un Set para evitar duplicados
        List<String> codigoRecargas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT DISTINCT Tipo_codigo FROM tbl_codigosRecarga", null);

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("Tipo_codigo");

                if (columnIndex >= 0) {
                    do {
                        codigoRecargasSet.add(cursor.getString(columnIndex)); // Añadimos al Set
                    } while (cursor.moveToNext());
                } else {
                    Log.e("DB_ERROR", "Columna 'Tipo_codigo' no encontrada.");
                }
            }
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error al obtener los tipos de códigos de recarga: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        codigoRecargas.addAll(codigoRecargasSet); // Convertimos el Set a una lista
        return codigoRecargas;
    }


    // Método para obtener los precios de recarga filtrados por tipo
    private ArrayList<String> getPreciosRecargaPorTipo(String tipo) {
        ArrayList<String> preciosRecargas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Filtrar por tipo
            cursor = db.rawQuery("SELECT Precio_codigo FROM tbl_codigosRecarga WHERE Tipo_codigo = ?", new String[]{tipo});

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("Precio_codigo");

                if (columnIndex >= 0) {
                    do {
                        preciosRecargas.add(cursor.getString(columnIndex));
                    } while (cursor.moveToNext());
                } else {
                    Log.e("DB_ERROR", "Columna 'Precio_codigo' no encontrada.");
                }
            }
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error al obtener los precios de recarga: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return preciosRecargas;
    }

    private void loadCodeData(String code, int price) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Convertir el precio a String
            String priceString = String.valueOf(price);

            // Ejecutar la consulta con los parámetros correctos
            cursor = db.rawQuery("SELECT * FROM tbl_codigosRecarga WHERE Tipo_codigo = ? AND Precio_codigo = ?", new String[]{code, priceString});

            if (cursor != null && cursor.moveToFirst()) {
                // Obtener índices de las columnas
                int idIndex = cursor.getColumnIndex("ID_codigo");
                int tipoIndex = cursor.getColumnIndex("Tipo_codigo");
                int precioIndex = cursor.getColumnIndex("Precio_codigo");
                int secuenciaIndex = cursor.getColumnIndex("Secuencia_codigo");
                int estadoIndex = cursor.getColumnIndex("estado_codigo");

                if (idIndex >= 0 && tipoIndex >= 0 && precioIndex >= 0 && secuenciaIndex >= 0 && estadoIndex >= 0) {
                    // Guardar el ID del código
                    storeId = cursor.getInt(idIndex);

                    // Cargar los datos en los campos de edición
                    precioEditText.setText(cursor.getString(precioIndex));
                    secuenciaEditText.setText(cursor.getString(secuenciaIndex));
                    spinnerEstado.setSelection(getSpinnerIndex(spinnerEstado, cursor.getString(estadoIndex)));
                    spinnerTipo.setSelection(getSpinnerIndex(spinnerTipo, cursor.getString(tipoIndex)));
                } else {
                    Log.e("DB_ERROR", "Una o más columnas no se encontraron.");
                }
            } else {
                Log.e("DB_ERROR", "No se encontraron datos para el código: " + code + " y precio: " + priceString);
            }
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error al cargar los datos del código: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    // Método para obtener el índice del spinner
    private int getSpinnerIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(value)) {
                return i;
            }
        }
        return 0;
    }

    // Método para guardar los cambios en la base de datos
    private void updateCodeData() {
        String tipo = spinnerTipo.getSelectedItem().toString();
        String precio = precioEditText.getText().toString();
        String secuencia = secuenciaEditText.getText().toString();
        String estado = spinnerEstado.getSelectedItem().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Tipo_codigo", tipo);
        values.put("Precio_codigo", precio);
        values.put("Secuencia_codigo",secuencia);
        values.put("estado_codigo", estado);

        // Actualiza usando el ID del código
        db.update("tbl_codigosRecarga", values, "ID_codigo = ?", new String[]{String.valueOf( storeId)});
        db.close();
    }
}
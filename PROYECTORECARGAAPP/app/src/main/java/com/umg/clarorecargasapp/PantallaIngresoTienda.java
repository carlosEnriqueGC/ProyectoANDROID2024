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
import java.util.List;

public class PantallaIngresoTienda extends AppCompatActivity {

    private EditText nombreEditText, pinEditText;
    private LinearLayout guardarButton, modificarButton; // Cambiado a LinearLayout
    private DBHelper dbHelper;
    private Spinner spinnerEstado;
    private LinearLayout verRegistro, verRegistrosButton;
    private boolean isEditing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_ingreso_tienda);

        nombreEditText = findViewById(R.id.etNombre);
        pinEditText = findViewById(R.id.etPIN);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        guardarButton = findViewById(R.id.btnGuardar);
        modificarButton = findViewById(R.id.btnModificar);
        verRegistrosButton = findViewById(R.id.btnVerRegistros);
        final TextView textGuardar = findViewById(R.id.text_guardar);

        dbHelper = new DBHelper(this);
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditing) {
                    // Habilitar los componentes cuando se hace clic por primera vez
                    nombreEditText.setEnabled(true);
                    pinEditText.setEnabled(true);
                    spinnerEstado.setEnabled(true);
                    textGuardar.setText("Guardar");
                    isEditing = true;
                } else {
                    // Confirmar si el usuario quiere guardar los datos
                    new AlertDialog.Builder(PantallaIngresoTienda.this)
                            .setTitle("Confirmación")
                            .setMessage("¿Desea guardar los datos?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Guardar los datos en la base de datos
                                    saveData();
                                    Toast.makeText(PantallaIngresoTienda.this, "Datos registrados", Toast.LENGTH_SHORT).show();
                                    nombreEditText.setEnabled(false);
                                    pinEditText.setEnabled(false);
                                    spinnerEstado.setEnabled(false);
                                    textGuardar.setText("Agregar");
                                    isEditing = false;
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // No hacer nada, solo informar al usuario
                                    Toast.makeText(PantallaIngresoTienda.this, "Registro cancelado", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnRegresar = findViewById(R.id.btnVolverTienda);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(PantallaIngresoTienda.this, MenuDesplegable.class);
            startActivity(intent);
        });

        // Configurar adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estado_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        verRegistrosButton.setOnClickListener(v -> {
            // Iniciar la nueva actividad para ver registros
            Intent intent = new Intent(PantallaIngresoTienda.this, PantalladeRegistrosTienda.class);
            startActivity(intent);
        });

        modificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditing) {
                    showSelectStoreDialog();
                    nombreEditText.setEnabled(true);
                    pinEditText.setEnabled(true);
                    spinnerEstado.setEnabled(true);
                    isEditing = true;
                } else {
                    updateStoreData();
                    Toast.makeText(getApplicationContext(), "Datos modificados exitosamente.", Toast.LENGTH_SHORT).show();
                    nombreEditText.setEnabled(false);
                    pinEditText.setEnabled(false);
                    spinnerEstado.setEnabled(false);
                    isEditing = false;
                }
            }
        });


    }
    private void saveData() {
        String nombre = nombreEditText.getText().toString();
        String pinString = pinEditText.getText().toString();
        String estado = spinnerEstado.getSelectedItem().toString();

        if (nombre.isEmpty() || pinString.isEmpty() || estado.isEmpty()) {
            // Opcional: Muestra un mensaje de error si los campos están vacíos
            return;
        }

        int pin;
        try {
            pin = Integer.parseInt(pinString);
        } catch (NumberFormatException e) {
            // Opcional: Maneja el error si el PIN no es un número válido
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nombre_tienda", nombre);
        values.put("PIN", pin);
        values.put("Estado", estado);

        db.insert("tbl_datosTienda", null, values);
        db.close();
    }

    // Método para mostrar el diálogo
    private void showSelectStoreDialog() {
        // Crear el diálogo
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_seleccionar_tienda);

        // Referencias a los componentes del diálogo
        final Spinner spinnerTiendas = dialog.findViewById(R.id.spinnerTiendas);
        final Button btnSeleccionar = dialog.findViewById(R.id.btnSeleccionar);

        // Rellenar el spinner con los nombres de las tiendas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getStoreNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiendas.setAdapter(adapter);

        // Acción para cuando el usuario selecciona una tienda
        btnSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedStore = spinnerTiendas.getSelectedItem().toString();
                loadStoreData(selectedStore);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // Método para obtener nombres de tiendas desde la base de datos
    private List<String> getStoreNames() {
        List<String> storeNames = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT Nombre_tienda FROM tbl_datosTienda", null);

            // Verificar si hay columnas y si el cursor tiene filas
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("Nombre_tienda");

                if (columnIndex >= 0) {
                    do {
                        storeNames.add(cursor.getString(columnIndex));
                    } while (cursor.moveToNext());
                } else {
                    Log.e("DB_ERROR", "Columna 'Nombre_tienda' no encontrada.");
                }
            }
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error al obtener los nombres de las tiendas: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return storeNames;
    }


    // Método para cargar los datos de la tienda en los campos de edición
    private void loadStoreData(String storeName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM tbl_datosTienda WHERE Nombre_tienda = ?", new String[]{storeName});

            // Verificar si el cursor tiene filas
            if (cursor != null && cursor.moveToFirst()) {
                int nombreIndex = cursor.getColumnIndex("Nombre_tienda");
                int pinIndex = cursor.getColumnIndex("PIN");
                int estadoIndex = cursor.getColumnIndex("Estado");

                if (nombreIndex >= 0 && pinIndex >= 0 && estadoIndex >= 0) {
                    nombreEditText.setText(cursor.getString(nombreIndex));
                    pinEditText.setText(cursor.getString(pinIndex));
                    spinnerEstado.setSelection(getSpinnerIndex(spinnerEstado, cursor.getString(estadoIndex)));
                } else {
                    Log.e("DB_ERROR", "Una o más columnas no se encontraron.");
                }
            } else {
                Log.e("DB_ERROR", "No se encontraron datos para la tienda: " + storeName);
            }
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error al cargar los datos de la tienda: " + e.getMessage());
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
    private void updateStoreData() {
        String nombre = nombreEditText.getText().toString();
        String pin = pinEditText.getText().toString();
        String estado = spinnerEstado.getSelectedItem().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nombre_tienda", nombre);
        values.put("PIN", pin);
        values.put("Estado", estado);

        db.update("tbl_datosTienda", values, "Nombre_tienda = ?", new String[]{nombre});
        db.close();
    }


}
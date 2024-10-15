package com.umg.clarorecargasapp;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class IngresoDatosCliente extends AppCompatActivity {

    private String opcion;
    private int precio;
    private EditText editTextTelefono;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingreso_datos_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DBHelper(this);

        // Obtener datos del Intent
        opcion = getIntent().getStringExtra("opcion");
        precio = getIntent().getIntExtra("precio", 0);

        // aqui obtenemos los botones que estan en el XML
        editTextTelefono = findViewById(R.id.etPhoneNumber);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(v -> {
            // Obtener el número de teléfono del EditText
            String phoneNumber = editTextTelefono.getText().toString().trim();

            // Verificar si el EditText está vacío
            if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(IngresoDatosCliente.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show();
                return; // Salir si está vacío
            }

            // Verificar la longitud del número de teléfono (opcional)
            if (phoneNumber.length() != 8) {
                Toast.makeText(IngresoDatosCliente.this, "El número de teléfono debe tener 8 dígitos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar si el texto contiene solo números
            if (phoneNumber.matches("\\d+")) {
                // Buscar la secuencia correspondiente al tipo y precio seleccionados
                String secuencia = buscarSecuencia(opcion, precio);
                if (secuencia != null) {
                    // Mostrar el diálogo para seleccionar la tienda
                    showSelectStoreDialog(secuencia, phoneNumber);
                } else {
                    Toast.makeText(IngresoDatosCliente.this, "No se encontró la secuencia para el tipo y precio seleccionados.", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(IngresoDatosCliente.this, "Los caracteres no son válidos", Toast.LENGTH_SHORT).show();
            }
        });



        Button btnCancelarS = findViewById(R.id.btnCancelar);
        btnCancelarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un cuadro de diálogo (AlertDialog)
                new AlertDialog.Builder(IngresoDatosCliente.this)
                        .setTitle("Cancelar")
                        .setMessage("¿Deseas cancelar?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Si el usuario elige 'Sí', navega al menú
                                Intent intent = new Intent(IngresoDatosCliente.this, MainActivity.class); // Asegúrate de reemplazar MenuActivity con tu actividad de menú real
                                startActivity(intent);
                                finish(); // Opcional: cierra la actividad actual
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Si el usuario elige 'No', simplemente cierra el diálogo
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });
    }

    private String buscarSecuencia(String tipo, int precio) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String secuencia = null;

        // Consulta SQL para obtener la secuencia
        String query = "SELECT Secuencia_codigo FROM tbl_codigosRecarga WHERE Tipo_codigo = ? AND Precio_codigo = ?";
        try {
            Cursor cursor = db.rawQuery(query, new String[]{tipo, String.valueOf(precio)});

            // Comprobar si se obtuvo alguna fila
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("Secuencia_codigo");
                    if (columnIndex != -1) {
                        secuencia = cursor.getString(columnIndex);
                    } else {
                        Log.e("DB Error", "Columna 'Secuencia_codigo' no encontrada.");
                    }
                } else {
                    Log.e("DB Error", "No se encontraron resultados para tipo: " + tipo + ", precio: " + precio);
                }
                cursor.close();
            } else {
                Log.e("DB Error", "Cursor es nulo.");
            }
        } catch (Exception e) {
            Log.e("DB Error", "Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            db.close();
        }

        return secuencia;
    }

    private void showSelectStoreDialog(String secuencia, String phoneNumber) {

        // Crear el diálogo
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_buscar_pin);

        // Referencias a los componentes del diálogo
        final Spinner spinnerTiendas = dialog.findViewById(R.id.spinnerSeleccionTiendas);
        final Button btnSeleccionar = dialog.findViewById(R.id.btnSeleccionarTienda);
        final Button btnCancelar = dialog.findViewById(R.id.btnCancelarOperacion);

        // Rellenar el spinner con los nombres de las tiendas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getStoreNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiendas.setAdapter(adapter);

        // Acción para cuando el usuario selecciona una tienda
        btnSeleccionar.setOnClickListener(v -> {
            String selectedStore = spinnerTiendas.getSelectedItem().toString();
            int pin = loadStorePin(selectedStore); // Obtener el PIN

            if (pin != -1) {
                // Verificar estados
                String estadoSecuencia = buscarEstadoSecuencia(opcion, precio); // Ahora busca por tipo y precio
                String estadoPin = buscarEstadoPin(selectedStore);  // Busca por tienda, tipo y precio

                if ("Inactivo".equals(estadoSecuencia) || "Suspendido".equals(estadoSecuencia) ||
                        "Inactivo".equals(estadoPin) || "Suspendido".equals(estadoPin)) {
                    // Mostrar diálogo de advertencia
                    new AlertDialog.Builder(this)
                            .setTitle("Estado Inactivo")
                            .setMessage("La secuencia o el PIN están inactivos o suspendidos. No se puede realizar la llamada.")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    // Concatenar el PIN y mostrar el mensaje final
                    String mensajeFinal = secuencia + phoneNumber + "*" + pin + "*1#"; // Agregar '*' y finalizar con '*1#'

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + Uri.encode(mensajeFinal)));

                    // Verificar el permiso de llamada
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(IngresoDatosCliente.this, "No tienes permisos, regresando a la pantalla principal.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IngresoDatosCliente.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Finalizar la actividad actual
                    } else {
                        startActivity(callIntent); // Realizar la llamada
                    }
                }
            } else {
                Toast.makeText(IngresoDatosCliente.this, "No se encontró el PIN para la tienda seleccionada.", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });

        btnCancelar.setOnClickListener(v -> dialog.dismiss());

        // Configura el diálogo para que no se cancele tocando fuera
        dialog.setCanceledOnTouchOutside(false);
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

    private int loadStorePin(String storeName) {
        int pin = -1; // Valor predeterminado si no se encuentra
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT PIN FROM tbl_datosTienda WHERE Nombre_tienda = ?", new String[]{storeName});
            if (cursor != null && cursor.moveToFirst()) {
                // Obtener el índice de la columna
                int pinIndex = cursor.getColumnIndex("PIN");
                if (pinIndex >= 0) { // Asegúrate de que el índice es válido
                    pin = cursor.getInt(pinIndex);
                } else {
                    Log.e("DB_ERROR", "Columna 'PIN' no encontrada.");
                }
            } else {
                Log.e("DB_ERROR", "No se encontraron filas para la tienda: " + storeName);
            }
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error al obtener el PIN: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return pin;
    }

    private String buscarEstadoSecuencia(String tipo, int precio) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String estado = null;

        String query = "SELECT estado_codigo FROM tbl_codigosRecarga WHERE Tipo_codigo = ? AND Precio_codigo = ?";
        try (Cursor cursor = db.rawQuery(query, new String[]{tipo, String.valueOf(precio)})) {
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("estado_codigo");
                if (columnIndex != -1) {
                    estado = cursor.getString(columnIndex);
                }
            }
        } catch (Exception e) {
            Log.e("DB Error", "Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            db.close();
        }

        return estado;
    }

    private String buscarEstadoPin(String storeName) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String estado = null;

        String query = "SELECT Estado FROM tbl_datosTienda WHERE Nombre_tienda = ?";
        try (Cursor cursor = db.rawQuery(query, new String[]{storeName})) {
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex("Estado");
                if (columnIndex != -1) {
                    estado = cursor.getString(columnIndex);
                }
            }
        } catch (Exception e) {
            Log.e("DB Error", "Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            db.close();
        }

        return estado;
    }

}
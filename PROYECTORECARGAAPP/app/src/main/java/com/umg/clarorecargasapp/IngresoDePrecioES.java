package com.umg.clarorecargasapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity; // Para el posicionamiento del Toast
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView; // Para personalizar el texto en el Toast
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class IngresoDePrecioES extends AppCompatActivity {
    private EditText editTextPrecio; // Campo para ingresar el precio
    private String precio; // Variable para almacenar el precio
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingreso_de_precio_es);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        // Inicializar el EditText y el botón desde el XML
        editTextPrecio = findViewById(R.id.editTextNumber); // Cambia esto por el ID de tu EditText para precio
        Button btnConfirmar = findViewById(R.id.btnConfirmarS);
        Button btnCancelar = findViewById(R.id.btnCancelarS);
        dbHelper = new DBHelper(this); // Inicializar DBHelper

        // Acción al hacer clic en el botón "Confirmar"
        btnConfirmar.setOnClickListener(v -> {
            // Obtener el precio del EditText
            String precioInput = editTextPrecio.getText().toString().trim();

            // Verificar si el EditText está vacío
            if (TextUtils.isEmpty(precioInput)) {
                Toast.makeText(IngresoDePrecioES.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar si el texto contiene solo números
            if (precioInput.matches("\\d+")) {
                // Guardar el precio en la variable
                precio = precioInput;
                Toast.makeText(IngresoDePrecioES.this, "Precio guardado correctamente: " + precio, Toast.LENGTH_SHORT).show();

                // Buscar la secuencia correspondiente
                String tipo = "Saldo"; // Cambia esto por el tipo que necesites
                String secuencia = buscarSecuencia(tipo, Integer.parseInt(precio));
                if (secuencia != null) {
                    Toast.makeText(IngresoDePrecioES.this, "Secuencia encontrada: " + secuencia, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(IngresoDePrecioES.this, "No se encontró secuencia para el precio ingresado.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(IngresoDePrecioES.this, "Los caracteres no son válidos", Toast.LENGTH_SHORT).show();
            }
        });

        // Configura el botón de cancelar
        btnCancelar.setOnClickListener(v -> {
            // Crea un cuadro de diálogo (AlertDialog)
            new AlertDialog.Builder(IngresoDePrecioES.this)
                    .setTitle("Cancelar")
                    .setMessage("¿Deseas cancelar?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        // Si el usuario elige 'Sí', navega al menú
                        Intent intent = new Intent(IngresoDePrecioES.this, MainActivity.class); // Cambia esto por tu actividad de menú real
                        startActivity(intent);
                        finish(); // Opcional: cierra la actividad actual
                    })
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss()) // Si elige 'No', cierra el diálogo
                    .show();
        });
    }

    private String buscarSecuencia(String tipo, int precio) {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // Usar el método adecuado para lectura
        String secuencia = null;

        // Consulta SQL para obtener la secuencia
        String query = "SELECT Secuencia_codigo FROM tbl_codigosRecarga WHERE Saldo = ? AND Precio_codigo = ?";
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
                cursor.close(); // Cerrar el cursor después de usarlo
            } else {
                Log.e("DB Error", "Cursor es nulo.");
            }
        } catch (Exception e) {
            Log.e("DB Error", "Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            db.close(); // Asegúrate de cerrar la base de datos al final
        }

        return secuencia; // Retornar la secuencia encontrada
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
            int pin = loadStorePin(selectedStore); // Método que busca el PIN basado en la tienda
            if (pin != -1) {
                // Concatenar el PIN y mostrar el mensaje final
                String mensajeFinal = secuencia + phoneNumber + "*" + pin + "*1#"; // Agregar '*' y finalizar con '*1#'
                new AlertDialog.Builder(IngresoDePrecioES.this)
                        .setTitle("Secuencia Generada")
                        .setMessage(mensajeFinal)
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                Toast.makeText(IngresoDePrecioES.this, "No se encontró el PIN para la tienda seleccionada.", Toast.LENGTH_SHORT).show();
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

}


package com.umg.clarorecargasapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity; // Para el posicionamiento del Toast
import android.view.View;
import android.widget.Button;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView; // Para personalizar el texto en el Toast
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class IngresoDatosCliente extends AppCompatActivity {

    private String opcion;
    private int precio;
    private EditText editTextTelefono;

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

            // Verificar si el texto contiene solo números
            if (!phoneNumber.matches("\\d+")) {
                Toast.makeText(IngresoDatosCliente.this, "Los caracteres no son válidos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar la longitud del número de teléfono (opcional)
            if (phoneNumber.length() != 8) {
                Toast.makeText(IngresoDatosCliente.this, "El número de teléfono debe tener 10 dígitos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Buscar la secuencia correspondiente al tipo y precio seleccionados
            String secuencia = buscarSecuencia(opcion, precio);
            if (secuencia != null) {
                // Concatenar la secuencia con el número de teléfono
                String mensaje = secuencia + phoneNumber + "*"; // Agregar '*' al final
                // Mostrar el mensaje en un AlertDialog
                new AlertDialog.Builder(IngresoDatosCliente.this)
                        .setTitle("Secuencia Generada")
                        .setMessage(mensaje)
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                Toast.makeText(IngresoDatosCliente.this, "No se encontró la secuencia para el tipo y precio seleccionados.", Toast.LENGTH_SHORT).show();
            }

            // Mensaje informativo al abrir el sistema
            Toast.makeText(IngresoDatosCliente.this, "Abriendo el sistema claro", Toast.LENGTH_SHORT).show();
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

}
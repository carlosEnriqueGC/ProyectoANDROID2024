package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
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
public class IngresoDatosClienteES extends AppCompatActivity {

    private String opcion;
    private int precio;
    private EditText editTextTelefono;
    private DBHelper dbHelper;
    private String numeroTelefono; // Dec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingreso_datos_cliente_es);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Inicializar el EditText y el botón desde el XML
        editTextTelefono = findViewById(R.id.etPhoneNumber2);
        Button btnConfirmar = findViewById(R.id.btnContinuar);

        // Inicializar el EditText y el botón desde el XML

        // Acción al hacer clic en el botón "Continuar"
        btnConfirmar.setOnClickListener(v -> {
            // Obtener el número de teléfono del EditText
            String phoneNumber = editTextTelefono.getText().toString().trim();

            // Verificar si el EditText está vacío
            if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(IngresoDatosClienteES.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar la longitud del número de teléfono
            if (phoneNumber.length() != 8) {
                Toast.makeText(IngresoDatosClienteES.this, "El número de teléfono debe tener 8 dígitos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar si el texto contiene solo números
            if (phoneNumber.matches("\\d+")) {
                // Guardar el número de teléfono en una variable para usar más adelante
                numeroTelefono = phoneNumber;
                Toast.makeText(IngresoDatosClienteES.this, "Número guardado correctamente: " + numeroTelefono, Toast.LENGTH_SHORT).show();

                // Redirigir a la siguiente interfaz (otra actividad)
                Intent intent = new Intent(IngresoDatosClienteES.this, IngresoDePrecioES.class);
                startActivity(intent);
            } else {
                Toast.makeText(IngresoDatosClienteES.this, "Los caracteres no son válidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
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

        Button btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(v -> {
            // Crear el AlertDialog para confirmar la cancelación
            new AlertDialog.Builder(IngresoDatosCliente.this)
                    .setTitle("Cancelar")
                    .setMessage("¿Seguro que deseas cancelar?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        // Si selecciona "Sí", redirigir a MainActivity
                        Intent intent = new Intent(IngresoDatosCliente.this, MainActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // Si selecciona "No", cerrar el diálogo y permanecer en la misma pantalla
                        dialog.dismiss();
                    })
                    .show();
        });
        // aqui obtenemos los botones que estan en el XML
        EditText etPhoneNumber = findViewById(R.id.etPhoneNumber);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString();

                // Verificar si el EditText está vacío
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(IngresoDatosCliente.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Verificar si el texto contiene solo números
                if (phoneNumber.matches("\\d+")) {
                    Toast.makeText(IngresoDatosCliente.this, "Abriendo el sistema claro", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(IngresoDatosCliente.this, "Los caracteres no son válidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
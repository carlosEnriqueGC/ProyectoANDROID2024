package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IngresoDePrecioES extends AppCompatActivity {

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

        Button btnCancelar = findViewById(R.id.btnCancelarS);
        btnCancelar.setOnClickListener(v -> {
            // Crear el AlertDialog
            new AlertDialog.Builder(IngresoDePrecioES.this)
                    .setTitle("Cancelar")
                    .setMessage("¿Seguro que quieres cancelar?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        // Redirigir al MenuInternet si el usuario elige "No"
                        Intent intent = new Intent(IngresoDePrecioES.this, MainActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // Simplemente cierra el diálogo si elige "Sí"
                        dialog.dismiss();
                    })
                    .show();
        });

    }
}
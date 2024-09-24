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
            Intent intent = new Intent(IngresoDePrecioES.this, MainActivity.class);
            startActivity(intent);
        });
        // Configura el botón de cancelar
        Button btnCancelarS = findViewById(R.id.btnCancelarS);
        btnCancelarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un cuadro de diálogo (AlertDialog)
                new AlertDialog.Builder(IngresoDePrecioES.this)
                        .setTitle("Cancelar")
                        .setMessage("¿Deseas cancelar?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Si el usuario elige 'Sí', navega al menú
                                Intent intent = new Intent(IngresoDePrecioES.this, MainActivity.class); // Asegúrate de reemplazar MenuActivity con tu actividad de menú real
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
}
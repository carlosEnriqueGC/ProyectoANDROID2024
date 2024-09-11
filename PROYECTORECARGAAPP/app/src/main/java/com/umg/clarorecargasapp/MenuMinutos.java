package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MenuMinutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_minutos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnVolver = findViewById(R.id.btnVolver3);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, MainActivity.class);
            startActivity(intent);
        });

// Configurar todos los botones con información
        configureButton(R.id.btnPrecio1M, "Plan de minutos 1: 100 minutos.");
        configureButton(R.id.btnPrecio2M, "Plan de minutos 2: 200 minutos.");
        configureButton(R.id.btnPrecio3M, "Plan de minutos 3: 300 minutos.");
        configureButton(R.id.btnPrecio4M, "Plan de minutos 4: 400 minutos.");
        configureButton(R.id.btnPrecio5M, "Plan de minutos 5: 500 minutos.");
    }

    // Método para configurar los botones
    private void configureButton(int buttonId, String message) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Redirigir a IngresoDatosCliente cuando se hace clic en el botón
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        button.setOnLongClickListener(v -> {
            // Mostrar un AlertDialog con la información al mantener presionado el botón
            showInfoDialog(message);
            return true; // Retornar true para indicar que el evento ha sido manejado
        });
    }

    // Método para mostrar un diálogo de información
    private void showInfoDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuMinutos.this);
        builder.setTitle("Información")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

}
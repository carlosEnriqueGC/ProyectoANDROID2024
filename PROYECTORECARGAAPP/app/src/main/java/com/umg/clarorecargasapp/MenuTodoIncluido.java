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
public class MenuTodoIncluido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_todo_incluido);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnVolver = findViewById(R.id.btnVolver2);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, MainActivity.class);
            startActivity(intent);
        });

// Configurar todos los botones con información
        configureButton(R.id.btnPrecio1TI, "Plan todo incluido 1: Llamadas, mensajes y 5GB de datos.");
        configureButton(R.id.btnPrecio2TI, "Plan todo incluido 2: Llamadas, mensajes y 10GB de datos.");
        configureButton(R.id.btnPrecio3TI, "Plan todo incluido 3: Llamadas, mensajes y 15GB de datos.");
        configureButton(R.id.btnPrecio4TI, "Plan todo incluido 4: Llamadas, mensajes y 20GB de datos.");
        configureButton(R.id.btnPrecio5TI, "Plan todo incluido 5: Llamadas, mensajes y 25GB de datos.");
        configureButton(R.id.btnPrecio6TI, "Plan todo incluido 6: Llamadas, mensajes y 30GB de datos.");
    }

    // Método para configurar los botones
    private void configureButton(int buttonId, String message) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Redirigir a IngresoDatosCliente cuando se hace clic en el botón
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuTodoIncluido.this);
        builder.setTitle("Información")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    } }
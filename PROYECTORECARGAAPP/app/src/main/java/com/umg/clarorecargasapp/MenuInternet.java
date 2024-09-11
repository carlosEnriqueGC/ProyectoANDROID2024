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

public class MenuInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_internet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el botón y configúralo para regresar a la actividad principal
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, MainActivity.class);
            startActivity(intent);
        });

        // Configurar todos los botones con información
        configureButton(R.id.btnPrecio1, "Redes ilimitadas, llamadas, 5G, etc.");
        configureButton(R.id.btnPrecio2, "Plan básico: 2GB de datos, llamadas ilimitadas.");
        configureButton(R.id.btnPrecio3, "Plan estándar: 5GB de datos, llamadas y mensajes ilimitados.");
        configureButton(R.id.btnPrecio4, "Plan premium: 10GB de datos, llamadas y mensajes ilimitados.");
        configureButton(R.id.btnPrecio5, "Plan familiar: 15GB de datos, llamadas y mensajes ilimitados.");
        configureButton(R.id.btnPrecio6, "Plan empresarial: 20GB de datos, llamadas y mensajes ilimitados.");
        configureButton(R.id.btnPrecio7, "Plan VIP: 30GB de datos, llamadas y mensajes ilimitados.");
    }

    private void configureButton(int buttonId, String message) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Redirigir a IngresoDatosCliente cuando se hace clic en el botón
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        button.setOnLongClickListener(v -> {
            // Mostrar un AlertDialog con la información al mantener presionado el botón
            showInfoDialog(message);
            return true; // Retornar true para indicar que el evento ha sido manejado
        });
    }

    private void showInfoDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuInternet.this);
        builder.setTitle("Información")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
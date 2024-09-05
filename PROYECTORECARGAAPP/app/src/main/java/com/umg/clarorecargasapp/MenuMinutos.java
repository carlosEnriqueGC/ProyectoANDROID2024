package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        // Encuentra el botón y configúralo para regresar a la actividad principal
        Button btnVolver = findViewById(R.id.btnVolver3);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1M = findViewById(R.id.btnPrecio1M);
        btnPrecio1M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio2M = findViewById(R.id.btnPrecio2M);
        btnPrecio2M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio3M = findViewById(R.id.btnPrecio3M);
        btnPrecio3M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio4M = findViewById(R.id.btnPrecio4M);
        btnPrecio4M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio5M = findViewById(R.id.btnPrecio5M);
        btnPrecio5M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });
    }
}
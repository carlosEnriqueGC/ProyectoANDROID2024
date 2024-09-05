package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuRedes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_redes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el botón y configúralo para regresar a la actividad principal
        Button btnVolver = findViewById(R.id.btnVolver4);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1R = findViewById(R.id.btnPrecio1R);
        btnPrecio1R.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio2R = findViewById(R.id.btnPrecio2R);
        btnPrecio2R.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio3R = findViewById(R.id.btnPrecio3R);
        btnPrecio3R.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio4R = findViewById(R.id.btnPrecio4R);
        btnPrecio4R.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio5R = findViewById(R.id.btnPrecio5R);
        btnPrecio5R.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio6R = findViewById(R.id.btnPrecio6R);
        btnPrecio6R.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            startActivity(intent);
        });
    }
}
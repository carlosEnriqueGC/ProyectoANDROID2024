package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
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

        Button btnPrecio1 = findViewById(R.id.btnPrecio1);
        btnPrecio1.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio2 = findViewById(R.id.btnPrecio2);
        btnPrecio2.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio3 = findViewById(R.id.btnPrecio3);
        btnPrecio3.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio4 = findViewById(R.id.btnPrecio4);
        btnPrecio4.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio5 = findViewById(R.id.btnPrecio5);
        btnPrecio5.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio6 = findViewById(R.id.btnPrecio6);
        btnPrecio6.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio7 = findViewById(R.id.btnPrecio7);
        btnPrecio7.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

    }
}
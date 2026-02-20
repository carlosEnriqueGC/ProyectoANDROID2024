package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuRedes extends AppCompatActivity {

    String tipo = "Redes sociales";
    private int Identificador;

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
        ImageButton btnRegresar = findViewById(R.id.btnVolverR);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuRedes.this, MainActivity.class);
            startActivity(intent);
        });

        // Botón para Precio 1
        Button btnPrecio1R = findViewById(R.id.btnPrecio1R);
        btnPrecio1R.setOnClickListener(v -> {
            Identificador = 24;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

// Botón para Precio 2
        Button btnPrecio2R = findViewById(R.id.btnPrecio2R);
        btnPrecio2R.setOnClickListener(v -> {
            Identificador = 25;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

// Botón para Precio 3
        Button btnPrecio3R = findViewById(R.id.btnPrecio3R);
        btnPrecio3R.setOnClickListener(v -> {
            Identificador = 26;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

// Botón para Precio 4
        Button btnPrecio4R = findViewById(R.id.btnPrecio4R);
        btnPrecio4R.setOnClickListener(v -> {
            Identificador = 27;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

// Botón para Precio 5
        Button btnPrecio5R = findViewById(R.id.btnPrecio5R);
        btnPrecio5R.setOnClickListener(v -> {
            Identificador = 28;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

// Botón para Precio 6
        Button btnPrecio6R = findViewById(R.id.btnPrecio6R);
        btnPrecio6R.setOnClickListener(v -> {
            Identificador = 29;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });
    }
}
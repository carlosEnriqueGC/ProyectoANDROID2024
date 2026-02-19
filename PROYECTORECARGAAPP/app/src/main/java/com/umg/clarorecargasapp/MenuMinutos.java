package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuMinutos extends AppCompatActivity {

    String tipo = "Minutos";
    private int precioSeleccionado;


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
        ImageButton btnRegresar = findViewById(R.id.btnVolverM);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1M = findViewById(R.id.btnPrecio1M);
        btnPrecio1M.setOnClickListener(v -> {
            precioSeleccionado = 7;
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

        Button btnPrecio2M = findViewById(R.id.btnPrecio2M);
        btnPrecio2M.setOnClickListener(v -> {
            precioSeleccionado = 12;
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

        Button btnPrecio3M = findViewById(R.id.btnPrecio3M);
        btnPrecio3M.setOnClickListener(v -> {
            precioSeleccionado = 15;
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

        Button btnPrecio4M = findViewById(R.id.btnPrecio4M);
        btnPrecio4M.setOnClickListener(v -> {
            precioSeleccionado = 25;
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

        Button btnPrecio5M = findViewById(R.id.btnPrecio5M);
        btnPrecio5M.setOnClickListener(v -> {
            precioSeleccionado = 50;
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });
    }
}
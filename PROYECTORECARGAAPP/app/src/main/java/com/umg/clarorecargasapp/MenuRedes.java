package com.umg.clarorecargasapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuRedes extends AppCompatActivity {

    String tipo = "Redes sociales";
    private int precioSeleccionado;

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
            precioSeleccionado = 6;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

// Botón para Precio 2
        Button btnPrecio2R = findViewById(R.id.btnPrecio2R);
        btnPrecio2R.setOnClickListener(v -> {
            precioSeleccionado = 25;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

// Botón para Precio 3
        Button btnPrecio3R = findViewById(R.id.btnPrecio3R);
        btnPrecio3R.setOnClickListener(v -> {
            precioSeleccionado = 60;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

// Botón para Precio 4
        Button btnPrecio4R = findViewById(R.id.btnPrecio4R);
        btnPrecio4R.setOnClickListener(v -> {
            precioSeleccionado = 11;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

// Botón para Precio 5
        Button btnPrecio5R = findViewById(R.id.btnPrecio5R);
        btnPrecio5R.setOnClickListener(v -> {
            precioSeleccionado = 15;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });

// Botón para Precio 6
        Button btnPrecio6R = findViewById(R.id.btnPrecio6R);
        btnPrecio6R.setOnClickListener(v -> {
            precioSeleccionado = 30;
            Intent intent = new Intent(MenuRedes.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("precio", precioSeleccionado);
            startActivity(intent);
        });
    }
}
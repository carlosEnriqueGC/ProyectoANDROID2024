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

public class MenuInternet extends AppCompatActivity {

    String tipo = "Internet";
    private int Identificador;

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
        ImageButton btnRegresar = findViewById(R.id.btnVolverI);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1 = findViewById(R.id.btnPrecio1);
        btnPrecio1.setOnClickListener(v -> {
            Identificador = 1;
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio2 = findViewById(R.id.btnPrecio2);
        btnPrecio2.setOnClickListener(v -> {
            Identificador = 2;
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio3 = findViewById(R.id.btnPrecio3);
        btnPrecio3.setOnClickListener(v -> {
            Identificador = 3;
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio4 = findViewById(R.id.btnPrecio4);
        btnPrecio4.setOnClickListener(v -> {
            Identificador = 4; // este en realidad es 11 pero para que se distinga la recarga se coloca 12
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio5 = findViewById(R.id.btnPrecio5);
        btnPrecio5.setOnClickListener(v -> {
            Identificador = 5;
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio6 = findViewById(R.id.btnPrecio6);
        btnPrecio6.setOnClickListener(v -> {
            Identificador = 6;
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio7 = findViewById(R.id.btnPrecio7);
        btnPrecio7.setOnClickListener(v -> {
            Identificador = 7;
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });
        Button btnPrecio8 = findViewById(R.id.btnPrecio8);
        btnPrecio8.setOnClickListener(v -> {
            Identificador = 8;
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });
    }
}


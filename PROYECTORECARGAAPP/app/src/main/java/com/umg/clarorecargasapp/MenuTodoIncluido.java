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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsCompat.Type;


public class MenuTodoIncluido extends AppCompatActivity {

    String tipo = "Todo incluido";
    private int Identificador;

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

// Encuentra el botón y configúralo para regresar a la actividad principal
        ImageButton btnRegresar = findViewById(R.id.btnVolverTI);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1TI = findViewById(R.id.btnPrecio1TI);
        btnPrecio1TI.setOnClickListener(v -> {
            Identificador = 9;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio2TI = findViewById(R.id.btnPrecio2TI);
        btnPrecio2TI.setOnClickListener(v -> {
            Identificador = 10;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio3TI = findViewById(R.id.btnPrecio3TI);
        btnPrecio3TI.setOnClickListener(v -> {
            Identificador = 11;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio4TI = findViewById(R.id.btnPrecio4TI);
        btnPrecio4TI.setOnClickListener(v -> {
            Identificador = 12;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio5TI = findViewById(R.id.btnPrecio5TI);
        btnPrecio5TI.setOnClickListener(v -> {
            Identificador = 13;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio6TI = findViewById(R.id.btnPrecio6TI);
        btnPrecio6TI.setOnClickListener(v -> {
            Identificador = 14;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio7TI = findViewById(R.id.btnPrecio7TI);
        btnPrecio7TI.setOnClickListener(v -> {
            Identificador = 15;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio8TI = findViewById(R.id.btnPrecio8TI);
        btnPrecio8TI.setOnClickListener(v -> {
            Identificador = 16;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });

        Button btnPrecio9TI = findViewById(R.id.btnPrecio9TI);
        btnPrecio9TI.setOnClickListener(v -> {
            Identificador = 17;
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
            intent.putExtra("id", Identificador);
            startActivity(intent);
        });
    }
}


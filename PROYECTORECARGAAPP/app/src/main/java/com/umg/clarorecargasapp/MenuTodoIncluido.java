package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        // Encuentra el botón y configúralo para regresar a la actividad principal
        Button btnVolver = findViewById(R.id.btnVolver2);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1TI = findViewById(R.id.btnPrecio1TI);
        btnPrecio1TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio2TI = findViewById(R.id.btnPrecio2TI);
        btnPrecio2TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio3TI = findViewById(R.id.btnPrecio3TI);
        btnPrecio3TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio4TI = findViewById(R.id.btnPrecio4TI);
        btnPrecio4TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio5TI = findViewById(R.id.btnPrecio5TI);
        btnPrecio5TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio6TI = findViewById(R.id.btnPrecio6TI);
        btnPrecio6TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });
    }
}
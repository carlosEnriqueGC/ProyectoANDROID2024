package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configurar la base de datos
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            // Conexión exitosa
            Toast.makeText(this, "Conexión a la base de datos establecida", Toast.LENGTH_SHORT).show();
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el botón y configúralo para iniciar la nueva actividad
        Button btnInternet = findViewById(R.id.btnInternet);
        btnInternet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuInternet.class);
            startActivity(intent);
        });

        // Encuentra el botón y configúralo para iniciar la nueva actividad
        Button btnTodoIncluido = findViewById(R.id.btnTodoIncluido);
        btnTodoIncluido.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuTodoIncluido.class);
            startActivity(intent);
        });

        // Encuentra el botón y configúralo para iniciar la nueva actividad
        Button btnMinutos = findViewById(R.id.btnMinutos);
        btnMinutos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuMinutos.class);
            startActivity(intent);
        });

        // Encuentra el botón y configúralo para iniciar la nueva actividad
        Button btnRedes = findViewById(R.id.btnRedes);
        btnRedes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuRedes.class);
            startActivity(intent);
        });

        // Encuentra el botón y configúralo para iniciar la nueva actividad
        Button btnSaldo = findViewById(R.id.btnSaldo);
        btnSaldo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IngresoDatosClienteES.class);
            startActivity(intent);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Cierra la aplicación al presionar el botón de retroceso
                finishAffinity();  // Cierra todas las actividades y sale de la aplicación
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}
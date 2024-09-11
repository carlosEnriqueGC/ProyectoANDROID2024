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
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_LAUNCH = "first_launch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configurar la base de datos
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            boolean isFirstLaunch = preferences.getBoolean(KEY_FIRST_LAUNCH, true);

            if (isFirstLaunch) {
                // Conexión exitosa
                Toast.makeText(this, "Conexión a la base de datos establecida", Toast.LENGTH_SHORT).show();

                // Guardar que ya se mostró el Toast
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(KEY_FIRST_LAUNCH, false);
                editor.apply();
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar los botones
        configureButton(R.id.btnInternet, MenuInternet.class);
        configureButton(R.id.btnTodoIncluido, MenuTodoIncluido.class);
        configureButton(R.id.btnMinutos, MenuMinutos.class);
        configureButton(R.id.btnRedes, MenuRedes.class);
        configureButton(R.id.btnSaldo, IngresoDatosClienteES.class);
        configureImageButton(R.id.IconMenu, MenuDesplegable.class);

        // Configurar el manejo del botón "Atrás"
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Salir de la aplicación")
                        .setMessage("¿Está seguro de que desea salir de la aplicación?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            // Si el usuario selecciona "Sí", cerrar la aplicación
                            finishAffinity(); // Cierra todas las actividades
                            System.exit(0);   // Opcionalmente terminar el proceso
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            // Si el usuario selecciona "No", cerrar el diálogo
                            dialog.dismiss();
                        })
                        .setCancelable(false) // Evita que el diálogo se cierre al tocar fuera de él
                        .show();
            }
        });
    }

    private void configureButton(int buttonId, Class<?> activityClass) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activityClass);
            startActivity(intent);
        });
    }

    private void configureImageButton(int buttonId, Class<?> activityClass) {
        ImageButton button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activityClass);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cuando la aplicación se cierra, restablecer la bandera para la próxima vez que se abra
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_FIRST_LAUNCH, true);
        editor.apply();
    }
}
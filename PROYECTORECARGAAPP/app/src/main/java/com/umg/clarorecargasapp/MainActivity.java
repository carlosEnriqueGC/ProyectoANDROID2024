package com.umg.clarorecargasapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_LAUNCH = "first_launch";
    private boolean isCallPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Verificar si ya se han concedido los permisos
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            isCallPermissionGranted = true; // Si ya se tiene permiso
        }

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

        ImageButton MenuDesplegable = findViewById(R.id.IconMenu);
        MenuDesplegable.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuDesplegable.class);
            startActivity(intent);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Mostrar un cuadro de diálogo para confirmar la salida
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Salir")
                        .setMessage("¿Deseas salir de la aplicación?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Si el usuario elige 'Sí', cerrar la aplicación
                                finishAffinity();  // Cierra todas las actividades y sale de la aplicación
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Si el usuario elige 'No', simplemente cierra el diálogo
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); // Llama al método de la clase base

        if (requestCode == 1) {
            if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isCallPermissionGranted = true;
                    Toast.makeText(this, "Permiso de llamada concedido.", Toast.LENGTH_SHORT).show();
                } else {
                    isCallPermissionGranted = false;
                    Toast.makeText(this, "Permiso de llamada denegado. No podrás realizar llamadas.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Error en la solicitud de permisos.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


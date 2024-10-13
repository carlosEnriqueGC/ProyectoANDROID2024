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
import android.provider.Settings;
import android.net.Uri;
public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_LAUNCH = "first_launch";
    private boolean isCallPermissionGranted = false;
    private boolean isMessageShown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Configuración de bordes para pantalla completa
        setContentView(R.layout.activity_main);
        // Inicializa el estado del mensaje desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MiApp", MODE_PRIVATE);
        isMessageShown = sharedPreferences.getBoolean("isMessageShown", false);
        iniciarSolicitudDePermiso();

        // Verificar si ya se han concedido los permisos de llamada
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar el permiso si no ha sido concedido
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            // Si el permiso ya ha sido concedido
            isCallPermissionGranted = true;
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

        // Encuentra los botones y configúralos para iniciar las nuevas actividades
        Button btnInternet = findViewById(R.id.btnInternet);
        btnInternet.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuInternet.class);
            startActivity(intent);
        });

        Button btnTodoIncluido = findViewById(R.id.btnTodoIncluido);
        btnTodoIncluido.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuTodoIncluido.class);
            startActivity(intent);
        });

        Button btnMinutos = findViewById(R.id.btnMinutos);
        btnMinutos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuMinutos.class);
            startActivity(intent);
        });

        Button btnRedes = findViewById(R.id.btnRedes);
        btnRedes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuRedes.class);
            startActivity(intent);
        });

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

        // Manejo del botón de retroceso
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Salir")
                        .setMessage("¿Deseas salir de la aplicación?")
                        .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                        .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
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

    private int contadorDenegaciones = 0; // Contador para rastrear cuántas veces se ha denegado el permiso

    public void solicitarPermisoLlamada() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); // Llama al método de la clase base

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Si el usuario concede el permiso
                isCallPermissionGranted = true;

                // Verificar si el mensaje ya fue mostrado
                if (!isMessageShown) {
                    Toast.makeText(this, "Permiso de llamada concedido.", Toast.LENGTH_SHORT).show();

                    // Guardar el estado de que se mostró el mensaje
                    SharedPreferences sharedPreferences = getSharedPreferences("MiApp", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isMessageShown", true);
                    editor.apply();
                }
            }  else {
                // Si el permiso es denegado
                isCallPermissionGranted = false;
                Toast.makeText(this, "Permiso de llamada denegado. No podrás realizar llamadas.", Toast.LENGTH_SHORT).show();

                // Incrementar el contador de denegaciones
                contadorDenegaciones++;

                // Si el permiso ha sido denegado menos de 3 veces, volver a mostrar el diálogo de permisos
                if (contadorDenegaciones < 3) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        mostrarDialogoPermiso(); // Llamar al método para mostrar el diálogo
                    }
                } else {
                    // Si el permiso ha sido denegado 3 veces, mostrar el diálogo para ir a la configuración
                    mostrarDialogoAbrirConfiguracion();
                }
            }
        } else {
            Toast.makeText(this, "Error en la solicitud de permisos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para mostrar el diálogo de permisos
    private void mostrarDialogoPermiso() {
        new AlertDialog.Builder(this)
                .setMessage("El permiso de llamadas es necesario para realizar llamadas desde la aplicación.")
                .setPositiveButton("Intentar de nuevo", (dialogInterface, i) -> solicitarPermisoLlamada())
                .setCancelable(false) // Evita que el usuario cierre el diálogo de otra manera
                .show();
    }

    // Método para mostrar un diálogo que redirige a la configuración
    private void mostrarDialogoAbrirConfiguracion() {
        new AlertDialog.Builder(this)
                .setMessage("El permiso de llamadas es necesario. Ve a la configuración de la aplicación para habilitarlo manualmente.")
                .setPositiveButton("Abrir configuración", (dialogInterface, i) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                })
                .setCancelable(false) // No permite cerrar el diálogo tocando fuera
                .show();
    }

    // Llama a este método en el punto apropiado de tu aplicación para iniciar la solicitud de permiso
    public void iniciarSolicitudDePermiso() {
        solicitarPermisoLlamada();
    } }
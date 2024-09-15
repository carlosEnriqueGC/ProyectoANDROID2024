package com.umg.clarorecargasapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PantallaIngresoTienda extends AppCompatActivity {

    private EditText nombreEditText, pinEditText;
    private LinearLayout guardarButton; // Cambiado a LinearLayout
    private DBHelper dbHelper;
    private Spinner spinnerEstado;
    private LinearLayout verRegistro, verRegistrosButton;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_ingreso_tienda);

        nombreEditText = findViewById(R.id.etNombre);
        pinEditText = findViewById(R.id.etPIN);
        spinnerEstado = findViewById(R.id.spinnerEstado);
        guardarButton = findViewById(R.id.btnGuardar);
        verRegistrosButton = findViewById(R.id.btnVerRegistros);
        final TextView textGuardar = findViewById(R.id.text_guardar);

        dbHelper = new DBHelper(this);
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditing) {
                    // Habilitar los componentes cuando se hace clic por primera vez
                    nombreEditText.setEnabled(true);
                    pinEditText.setEnabled(true);
                    spinnerEstado.setEnabled(true);
                    textGuardar.setText("Guardar");
                    isEditing = true;
                } else {
                    // Confirmar si el usuario quiere guardar los datos
                    new AlertDialog.Builder(PantallaIngresoTienda.this)
                            .setTitle("Confirmación")
                            .setMessage("¿Desea guardar los datos?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Guardar los datos en la base de datos
                                    saveData();
                                    Toast.makeText(PantallaIngresoTienda.this, "Datos registrados", Toast.LENGTH_SHORT).show();
                                    nombreEditText.setEnabled(false);
                                    pinEditText.setEnabled(false);
                                    spinnerEstado.setEnabled(false);
                                    textGuardar.setText("Agregar");
                                    isEditing = false;
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // No hacer nada, solo informar al usuario
                                    Toast.makeText(PantallaIngresoTienda.this, "Registro cancelado", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnRegresar = findViewById(R.id.btnVolverTienda);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(PantallaIngresoTienda.this, MenuDesplegable.class);
            startActivity(intent);
        });

        // Configurar adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estado_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        verRegistrosButton.setOnClickListener(v -> {
            // Iniciar la nueva actividad para ver registros
            Intent intent = new Intent(PantallaIngresoTienda.this, PantalladeRegistrosTienda.class);
            startActivity(intent);
        });

    }
    private void saveData() {
        String nombre = nombreEditText.getText().toString();
        String pinString = pinEditText.getText().toString();
        String estado = spinnerEstado.getSelectedItem().toString();

        if (nombre.isEmpty() || pinString.isEmpty() || estado.isEmpty()) {
            // Opcional: Muestra un mensaje de error si los campos están vacíos
            return;
        }

        int pin;
        try {
            pin = Integer.parseInt(pinString);
        } catch (NumberFormatException e) {
            // Opcional: Maneja el error si el PIN no es un número válido
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nombre_tienda", nombre);
        values.put("PIN", pin);
        values.put("Estado", estado);

        db.insert("tbl_datosTienda", null, values);
        db.close();
    }
}
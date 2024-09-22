package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pantalla_ingreso_codigosu extends AppCompatActivity {

    private EditText precioEditText, secuenciaEditText;
    private LinearLayout guardarButton, modificarButton, eliminarButton; // Cambiado a LinearLayout
    private DBHelper dbHelper;
    private Spinner spinnerEstado, spinnerTipo;
    private LinearLayout verRegistro, verRegistrosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_ingreso_codigosu);

        // Initialize EditText and Spinner views
        precioEditText = findViewById(R.id.precioCodigo);
        secuenciaEditText = findViewById(R.id.secuenciaCodigo);
        spinnerEstado = findViewById(R.id.spinnerEstado2);
        spinnerTipo = findViewById(R.id.spinnerTipocodigo);

        // Initialize LinearLayouts for buttons
        guardarButton = findViewById(R.id.btnGuardarCU);
        modificarButton = findViewById(R.id.btnModificarCU);
        eliminarButton = findViewById(R.id.btnEliminarCU);
        verRegistrosButton = findViewById(R.id.btnVerRegistrosCU);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnRegresar = findViewById(R.id.btnVolverCU);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(pantalla_ingreso_codigosu.this, MenuDesplegable.class);
            startActivity(intent);
        });

        // Configurar adaptador para el Spinner estados
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estado2_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        // Configurar adaptador para el Spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.tipoCodigo_options, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter2);

        verRegistrosButton.setOnClickListener(v -> {
            // Iniciar la nueva actividad para ver registros
            Intent intent = new Intent(pantalla_ingreso_codigosu.this, PantallaDeRegistrosCodigos.class);
            startActivity(intent);
        });
    }
}
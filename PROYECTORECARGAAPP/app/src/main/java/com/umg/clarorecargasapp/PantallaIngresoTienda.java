package com.umg.clarorecargasapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

public class PantallaIngresoTienda extends AppCompatActivity {

    private EditText nombreEditText, pinEditText, estadoEditText;
    private LinearLayout guardarButton; // Cambiado a LinearLayout
    private RecyclerView recyclerView;
    private TiendaAdapter adapter;
    private List<Tienda> tiendaList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_ingreso_tienda);

        nombreEditText = findViewById(R.id.etNombre);
        pinEditText = findViewById(R.id.etPIN);
        estadoEditText = findViewById(R.id.etEstado);
        guardarButton = findViewById(R.id.btnGuardar);
        recyclerView = findViewById(R.id.recyclerViewTienda);

        dbHelper = new DBHelper(this);
        tiendaList = new ArrayList<>();
        adapter = new TiendaAdapter(tiendaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Cargar datos al iniciar
        loadTiendaData();

        guardarButton.setOnClickListener(v -> {
            saveData();
            loadTiendaData(); // Actualiza el RecyclerView

            // Mostrar el Toast
            Toast.makeText(PantallaIngresoTienda.this, "Datos registrados", Toast.LENGTH_SHORT).show();
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
    }
    private void saveData() {
        String nombre = nombreEditText.getText().toString();
        String pinString = pinEditText.getText().toString();
        String estado = estadoEditText.getText().toString();

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
    private void loadTiendaData() {
        tiendaList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_datosTienda", null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    int idColumnIndex = cursor.getColumnIndexOrThrow("ID_tienda");
                    int nombreColumnIndex = cursor.getColumnIndexOrThrow("Nombre_tienda");
                    int pinColumnIndex = cursor.getColumnIndexOrThrow("PIN");
                    int estadoColumnIndex = cursor.getColumnIndexOrThrow("Estado");

                    // Extrae los valores de las columnas
                    int id = cursor.getInt(idColumnIndex);
                    String nombre = cursor.getString(nombreColumnIndex);
                    int pin = cursor.getInt(pinColumnIndex);
                    String estado = cursor.getString(estadoColumnIndex);

                    // Añade el objeto Tienda a la lista
                    tiendaList.add(new Tienda(id, nombre, pin, estado));
                } while (cursor.moveToNext());
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Maneja el error aquí
        } finally {
            cursor.close();
            db.close();
        }

        adapter.notifyDataSetChanged();
    }

}
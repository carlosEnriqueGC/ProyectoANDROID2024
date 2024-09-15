package com.umg.clarorecargasapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PantalladeRegistrosTienda extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TiendaAdapter adapter;
    private List<Tienda> tiendaList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantallade_registros_tienda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnRegresar = findViewById(R.id.btnRegregarPT);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(PantalladeRegistrosTienda.this, PantallaIngresoTienda.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerViewRegistros);
        tiendaList = new ArrayList<>();
        adapter = new TiendaAdapter(tiendaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        dbHelper = new DBHelper(this);
        loadTiendaData();
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

                    int id = cursor.getInt(idColumnIndex);
                    String nombre = cursor.getString(nombreColumnIndex);
                    int pin = cursor.getInt(pinColumnIndex);
                    String estado = cursor.getString(estadoColumnIndex);

                    tiendaList.add(new Tienda(id, nombre, pin, estado));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            db.close();
        }

        adapter.notifyDataSetChanged();
    }
}
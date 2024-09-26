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


public class PantallaDeRegistrosCodigos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CodigoUAdapter adapter;
    private List<CodigoU> codigoUList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_de_registros_codigos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton btnRegresar = findViewById(R.id.btnRegregarPCU);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(PantallaDeRegistrosCodigos.this, pantalla_ingreso_codigosu.class); // Cambia a tu actividad
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerViewRegistros);
        codigoUList = new ArrayList<>();
        adapter = new CodigoUAdapter(codigoUList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        dbHelper = new DBHelper(this);
        loadCodigoUData();
    }
    private void loadCodigoUData() {
        codigoUList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_codigosRecarga", null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    int tipoColumnIndex = cursor.getColumnIndexOrThrow("Tipo_codigo");
                    int precioColumnIndex = cursor.getColumnIndexOrThrow("Precio_codigo");
                    int secuenciaColumnIndex = cursor.getColumnIndexOrThrow("Secuencia_codigo");
                    int estadoColumnIndex = cursor.getColumnIndexOrThrow("estado_codigo");

                    String tipo = cursor.getString(tipoColumnIndex);
                    int precio = cursor.getInt(precioColumnIndex);
                    String secuencia = cursor.getString(secuenciaColumnIndex);
                    String estado = cursor.getString(estadoColumnIndex);

                    codigoUList.add(new CodigoU(tipo, precio, secuencia, estado));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            db.close();
        }

        adapter.notifyDataSetChanged();
    }
}
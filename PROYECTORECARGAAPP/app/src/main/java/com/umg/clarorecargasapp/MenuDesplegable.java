package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.RelativeLayout;


public class MenuDesplegable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_desplegable);

        RelativeLayout datosTienda = findViewById(R.id.layoutDatosTienda);
        RelativeLayout codigosUtilizados = findViewById(R.id.layoutCodigosUtilizados);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        datosTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad Datos de tienda
                Intent intent = new Intent(MenuDesplegable.this, PantallaIngresoTienda.class);
                startActivity(intent);
            }
        });

        codigosUtilizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la actividad Códigos utilizados
                Intent intent = new Intent(MenuDesplegable.this, pantalla_ingreso_codigosu.class);
                startActivity(intent);
            }
        });

        ImageButton btnRegresar = findViewById(R.id.btnVolverMD);
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuDesplegable.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
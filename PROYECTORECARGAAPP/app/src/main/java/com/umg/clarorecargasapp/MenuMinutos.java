package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuMinutos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_minutos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el botón y configúralo para regresar a la actividad principal
        Button btnVolver = findViewById(R.id.btnVolver3);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1M = findViewById(R.id.btnPrecio1M);
        btnPrecio1M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio2M = findViewById(R.id.btnPrecio2M);
        btnPrecio2M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio3M = findViewById(R.id.btnPrecio3M);
        btnPrecio3M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio4M = findViewById(R.id.btnPrecio4M);
        btnPrecio4M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio5M = findViewById(R.id.btnPrecio5M);
        btnPrecio5M.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMinutos.this, IngresoDatosCliente.class);
            startActivity(intent);
        });
        //Q6 min
        ImageView infoImageView1 = findViewById(R.id.info1);
        infoImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un cuadro de diálogo (AlertDialog)
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuMinutos.this);
                builder.setTitle("Información"); // Título del diálogo
                builder.setMessage("1 dia + 50 Minutos y 50 SMS Nac./USA"); // Mensaje dentro del cuadro
                builder.setIcon(R.drawable.informacion);
                // Configura el botón de cerrar
                builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());

                // Muestra el cuadro de diálogo
                builder.show();
            }
        });

        //Q11 min
        ImageView infoImageView2 = findViewById(R.id.info2);
        infoImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un cuadro de diálogo (AlertDialog)
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuMinutos.this);
                builder.setTitle("Información"); // Título del diálogo
                builder.setMessage("2 dia + 120 Minutos y 120 SMS Nac./USA"); // Mensaje dentro del cuadro
                builder.setIcon(R.drawable.informacion);
                // Configura el botón de cerrar
                builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());

                // Muestra el cuadro de diálogo
                builder.show();
            }
        });

        //Q15 min
        ImageView infoImageView3 = findViewById(R.id.info3);
        infoImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un cuadro de diálogo (AlertDialog)
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuMinutos.this);
                builder.setTitle("Información"); // Título del diálogo
                builder.setMessage("3 dias + 120 Minutos y 120 SMS Nac./USA"); // Mensaje dentro del cuadro
                builder.setIcon(R.drawable.informacion);
                // Configura el botón de cerrar
                builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());

                // Muestra el cuadro de diálogo
                builder.show();
            }
        });

        //Q25 min
        ImageView infoImageView4 = findViewById(R.id.info4);
        infoImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un cuadro de diálogo (AlertDialog)
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuMinutos.this);
                builder.setTitle("Información"); // Título del diálogo
                builder.setMessage("7 dias + 225 Minutos y 225 SMS Nac./USA"); // Mensaje dentro del cuadro
                builder.setIcon(R.drawable.informacion);
                // Configura el botón de cerrar
                builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());

                // Muestra el cuadro de diálogo
                builder.show();
            }
        });

        //Q25 min
        ImageView infoImageView5 = findViewById(R.id.info5);
        infoImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un cuadro de diálogo (AlertDialog)
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuMinutos.this);
                builder.setTitle("Información"); // Título del diálogo
                builder.setMessage("15 dias + 600 Minutos y 600 SMS Nac./USA"); // Mensaje dentro del cuadro
                builder.setIcon(R.drawable.informacion);
                // Configura el botón de cerrar
                builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());

                // Muestra el cuadro de diálogo
                builder.show();
            }
        });
    }
}
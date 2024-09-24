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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsCompat.Type;


public class MenuTodoIncluido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_todo_incluido);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

// Encuentra el botón y configúralo para regresar a la actividad principal
        Button btnVolver = findViewById(R.id.btnVolver2);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1TI = findViewById(R.id.btnPrecio1TI);
        btnPrecio1TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio2TI = findViewById(R.id.btnPrecio2TI);
        btnPrecio2TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio3TI = findViewById(R.id.btnPrecio3TI);
        btnPrecio3TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio4TI = findViewById(R.id.btnPrecio4TI);
        btnPrecio4TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio5TI = findViewById(R.id.btnPrecio5TI);
        btnPrecio5TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio6TI = findViewById(R.id.btnPrecio6TI);
        btnPrecio6TI.setOnClickListener(v -> {
            Intent intent = new Intent(MenuTodoIncluido.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

// Agregar los íconos a cada información
        setupInfoClickListener(R.id.info1, "1 día + Llamadas Nacionales/USA + 650MB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.clarosport);
        setupInfoClickListener(R.id.info2, "2 día + Llamadas Nacionales/USA + 2GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.clarosport);
        setupInfoClickListener(R.id.info3, "7 día + Llamadas Nacionales/USA + 5GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
        setupInfoClickListener(R.id.info4, "15 día + Llamadas Nacionales/USA + 7GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
        setupInfoClickListener(R.id.info5, "30 día + Llamadas Nacionales/USA + 12GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
        setupInfoClickListener(R.id.info6, "3 día + Llamadas Nacionales/USA + 2.5GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
    }

    // Método para agregar íconos
    private void setupInfoClickListener(int imageViewId, String messageText, int... iconDrawables) {
        ImageView infoImageView = findViewById(imageViewId);
        infoImageView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MenuTodoIncluido.this);
            builder.setTitle("Información"); // Título del diálogo

            // Crear un SpannableStringBuilder para el mensaje con íconos
            SpannableStringBuilder message = new SpannableStringBuilder();

            // Añadir texto normal
            message.append(messageText);

            // Añadir íconos con tamaño personalizado (por ejemplo, 40x40 px)
            for (int iconDrawable : iconDrawables) {
                message.append("    "); // Espacio entre íconos
                ImageSpan iconSpan = getImageSpan(iconDrawable, 55, 55);
                if (iconSpan != null) {
                    message.setSpan(iconSpan, message.length() - 3, message.length(), 0); // Ajusta los índices para que el ícono no sobrescriba el texto
                }
            }

            // Establecer el mensaje en el AlertDialog
            builder.setMessage(message);
            builder.setIcon(R.drawable.informacion);

            // Configura el botón de cerrar
            builder.setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss());

            // Muestra el cuadro de diálogo
            builder.show();
        });
    }

    private ImageSpan getImageSpan(int drawableId, int width, int height) {
        // Usa ContextCompat para obtener el Drawable de manera compatible con todas las versiones de Android
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);

        if (drawable == null) {
            // Maneja el caso en el que el Drawable no se puede obtener
            return null;
        }

        // Si el Drawable es una instancia de BitmapDrawable, redimensiona la imagen
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            BitmapDrawable scaledDrawable = new BitmapDrawable(getResources(), scaledBitmap);
            scaledDrawable.setBounds(0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());
            return new ImageSpan(scaledDrawable);
        }

        // Si no es una instancia de BitmapDrawable, usa el Drawable original
        drawable.setBounds(0, 0, width, height);
        return new ImageSpan(drawable);
    }

}
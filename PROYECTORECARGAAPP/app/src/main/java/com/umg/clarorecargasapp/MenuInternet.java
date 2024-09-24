package com.umg.clarorecargasapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_internet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el botón y configúralo para regresar a la actividad principal
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnPrecio1 = findViewById(R.id.btnPrecio1);
        btnPrecio1.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio2 = findViewById(R.id.btnPrecio2);
        btnPrecio2.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio3 = findViewById(R.id.btnPrecio3);
        btnPrecio3.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio4 = findViewById(R.id.btnPrecio4);
        btnPrecio4.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio5 = findViewById(R.id.btnPrecio5);
        btnPrecio5.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio6 = findViewById(R.id.btnPrecio6);
        btnPrecio6.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        Button btnPrecio7 = findViewById(R.id.btnPrecio7);
        btnPrecio7.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInternet.this, IngresoDatosCliente.class);
            startActivity(intent);
        });

        // Configura los clics para los ImageViews
        setupInfoClickListener(R.id.info1, "1 día + 500MB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.clarosport);
        setupInfoClickListener(R.id.info2, "2 días + 1.2GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.clarosport);
        setupInfoClickListener(R.id.info3, "3 días + 2.2GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
        setupInfoClickListener(R.id.info4, "1 día + 3GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.clarosport);
        setupInfoClickListener(R.id.info5, "7 días + 6GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
        setupInfoClickListener(R.id.info6, "15 días + 8GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
        setupInfoClickListener(R.id.info7, "30 días + 15GB + ", R.drawable.whatsappp, R.drawable.facebook, R.drawable.instagram, R.drawable.mensajero, R.drawable.tiktok, R.drawable.gorjeo, R.drawable.spotify, R.drawable.youtube, R.drawable.clarosport);
    }

    private void setupInfoClickListener(int imageViewId, String messageText, int... iconDrawables) {
        ImageView infoImageView = findViewById(imageViewId);
        infoImageView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MenuInternet.this);
            builder.setTitle("Información"); // Título del diálogo

            // Crear un SpannableStringBuilder para el mensaje con iconos
            SpannableStringBuilder message = new SpannableStringBuilder();

            // Añadir texto normal
            message.append(messageText);

            // Añadir íconos con tamaño personalizado (por ejemplo, 40x40 px)
            for (int iconDrawable : iconDrawables) {
                // Añadir un espaciado mayor entre íconos
                message.append("  +  "); // Espacio entre íconos, ajusta el número de espacios según sea necesario
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


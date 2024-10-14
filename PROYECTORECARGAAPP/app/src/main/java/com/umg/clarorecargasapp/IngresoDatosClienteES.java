package com.umg.clarorecargasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.TextUtils;
import android.widget.Toast;

public class IngresoDatosClienteES extends AppCompatActivity {

    private EditText editTextTelefono;
    private DBHelper dbHelper;

    String tipo = "Saldo";
    private String NumeroCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingreso_datos_cliente_es);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar el EditText y el botón desde el XML
        editTextTelefono = findViewById(R.id.etPhoneNumber2);
        Button btnConfirmar = findViewById(R.id.btnContinuar);
        Button btnVolver = findViewById(R.id.btnRegresar);

        // Acción al hacer clic en el botón "Continuar"
        btnConfirmar.setOnClickListener(v -> {
            // Obtener el número de teléfono del EditText
            String phoneNumber = editTextTelefono.getText().toString().trim();

            // Verificar si el EditText está vacío
            if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(IngresoDatosClienteES.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar la longitud del número de teléfono
            if (phoneNumber.length() != 8) {
                Toast.makeText(IngresoDatosClienteES.this, "El número de teléfono debe tener 8 dígitos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Verificar si el texto contiene solo números
            if (phoneNumber.matches("\\d+")) {

                NumeroCliente = phoneNumber; //guardamos el numero del cliente

                // Redirigir a la siguiente interfaz
                Intent intent = new Intent(IngresoDatosClienteES.this, IngresoDePrecioES.class);
                intent.putExtra("opcion", tipo); // Aquí se pasa el tipo
                intent.putExtra("numero", NumeroCliente); //Aqui pasa el numero
                startActivity(intent);
            } else {
                Toast.makeText(IngresoDatosClienteES.this, "Los caracteres no son válidos", Toast.LENGTH_SHORT).show();
            }
        });

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(IngresoDatosClienteES.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
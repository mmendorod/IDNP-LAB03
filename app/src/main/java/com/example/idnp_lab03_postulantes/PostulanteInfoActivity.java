package com.example.idnp_lab03_postulantes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PostulanteInfoActivity extends AppCompatActivity {
    private EditText dniEditText;
    private Button buscarButton;
    private TextView infoTextView;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulante_info);

        dbHelper = new MyDatabaseHelper(this);

        dniEditText = findViewById(R.id.dni_edit_text);
        buscarButton = findViewById(R.id.buscar_button);
        infoTextView = findViewById(R.id.info_text_view);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = dniEditText.getText().toString();
                buscarPostulante(dni);
            }
        });
    }

    private void buscarPostulante(String dni) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "dni",
                "apellido_paterno",
                "apellido_materno",
                "nombres",
                "fecha_nacimiento",
                "colegio_procedencia",
                "carrera_postula"
        };

        String selection = "dni = ?";
        String[] selectionArgs = { dni };

        Cursor cursor = db.query(
                "Postulantes",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            String apellidoPaterno = cursor.getString(cursor.getColumnIndexOrThrow("apellido_paterno"));
            String apellidoMaterno = cursor.getString(cursor.getColumnIndexOrThrow("apellido_materno"));
            String nombres = cursor.getString(cursor.getColumnIndexOrThrow("nombres"));
            String fechaNacimiento = cursor.getString(cursor.getColumnIndexOrThrow("fecha_nacimiento"));
            String colegioProcedencia = cursor.getString(cursor.getColumnIndexOrThrow("colegio_procedencia"));
            String carreraPostula = cursor.getString(cursor.getColumnIndexOrThrow("carrera_postula"));

            String info = "Información del postulante:\n" +
                    "DNI: " + dni + "\n" +
                    "Apellido Paterno: " + apellidoPaterno + "\n" +
                    "Apellido Materno: " + apellidoMaterno + "\n" +
                    "Nombres: " + nombres + "\n" +
                    "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                    "Colegio de Procedencia: " + colegioProcedencia + "\n" +
                    "Carrera a la que Postula: " + carreraPostula;

            infoTextView.setText(info);
        } else {
            infoTextView.setText("No se encontró ningún postulante con el DNI ingresado.");
        }

        cursor.close();
    }
}

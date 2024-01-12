package com.example.idnp_lab03_postulantes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostulanteRegistroActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE ="EXTRA_MESSAGE";

    private EditText dniEditText;
    private EditText apellidoPaternoEditText;
    private EditText apellidoMaternoEditText;
    private EditText nombresEditText;
    private EditText fechaNacimientoEditText;
    private EditText colegioProcedenciaEditText;
    private EditText carreraPostulaEditText;
    private Button registrarButton;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postulante_registro);

        dbHelper = new MyDatabaseHelper(this);

        dniEditText = findViewById(R.id.dni_edit_text);
        apellidoPaternoEditText = findViewById(R.id.apellido_paterno_edit_text);
        apellidoMaternoEditText = findViewById(R.id.apellido_materno_edit_text);
        nombresEditText = findViewById(R.id.nombres_edit_text);
        fechaNacimientoEditText = findViewById(R.id.fecha_nacimiento_edit_text);
        colegioProcedenciaEditText = findViewById(R.id.colegio_procedencia_edit_text);
        carreraPostulaEditText = findViewById(R.id.carrera_postula_edit_text);
        registrarButton = findViewById(R.id.registrar_button);

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = dniEditText.getText().toString();
                String apellidoPaterno = apellidoPaternoEditText.getText().toString();
                String apellidoMaterno = apellidoMaternoEditText.getText().toString();
                String nombres = nombresEditText.getText().toString();
                String fechaNacimiento = fechaNacimientoEditText.getText().toString();
                String colegioProcedencia = colegioProcedenciaEditText.getText().toString();
                String carreraPostula = carreraPostulaEditText.getText().toString();

                registerPostulante(dni, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento, colegioProcedencia, carreraPostula);


            }
        });
    }

    private void registerPostulante(String dni, String apellidoPaterno, String apellidoMaterno, String nombres, String fechaNacimiento, String colegioProcedencia, String carreraPostula) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dni", dni);
        values.put("apellido_paterno", apellidoPaterno);
        values.put("apellido_materno", apellidoMaterno);
        values.put("nombres", nombres);
        values.put("fecha_nacimiento", fechaNacimiento);
        values.put("colegio_procedencia", colegioProcedencia);
        values.put("carrera_postula", carreraPostula);

        long result = db.insert("Postulantes", null, values);

        if (result != -1) {
            // Registro exitoso, muestra los datos del postulante en LogCat
            String logMessage = "Postulante registrado:\n" +
                    "DNI: " + dni + "\n" +
                    "Apellido Paterno: " + apellidoPaterno + "\n" +
                    "Apellido Materno: " + apellidoMaterno + "\n" +
                    "Nombres: " + nombres + "\n" +
                    "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                    "Colegio de Procedencia: " + colegioProcedencia + "\n" +
                    "Carrera a la que Postula: " + carreraPostula;
            Log.d("RegistroPostulante", logMessage);

            //MENSAJE ENVIADO A MENUACTIVITY
            Intent intent =new Intent();
            intent.putExtra(EXTRA_MESSAGE, "REGISTRO CORRECTO DE POSTULANTE");
            setResult(Activity.RESULT_OK,intent);
            finish();


        } else {
            Log.d("RegistroPostulante", "Error al registrar el postulante");
        }
    }
}

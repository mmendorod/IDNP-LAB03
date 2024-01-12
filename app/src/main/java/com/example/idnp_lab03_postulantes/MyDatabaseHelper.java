package com.example.idnp_lab03_postulantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createPostulantesTableQuery = "CREATE TABLE IF NOT EXISTS Postulantes (id INTEGER PRIMARY KEY AUTOINCREMENT, dni TEXT, apellido_paterno TEXT, apellido_materno TEXT, nombres TEXT, fecha_nacimiento TEXT, colegio_procedencia TEXT, carrera_postula TEXT)";
        db.execSQL(createPostulantesTableQuery);
        // Crea la tabla "users" con las columnas "username" y "password"
        String createTableQuery = "CREATE TABLE users (username TEXT, password TEXT)";
        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si necesitas realizar cambios en la estructura de la base de datos, puedes hacerlo aquí
        // Por ejemplo, puedes eliminar la tabla existente y crear una nueva
        String dropTableQuery = "DROP TABLE IF EXISTS users";
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
}

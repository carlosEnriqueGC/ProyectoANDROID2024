package com.umg.clarorecargasapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "BDRecargasClaro.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creación de las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTienda = "CREATE TABLE IF NOT EXISTS tbl_datosTienda (" +
                "ID_tienda INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre_tienda TEXT NOT NULL, " +
                "PIN INTEGER NOT NULL, " +
                "Estado TEXT NOT NULL)";
        db.execSQL(createTableTienda);

        String createTableCodigos = "CREATE TABLE IF NOT EXISTS tbl_codigosRecarga (" +
                "ID_codigo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Tipo_codigo TEXT NOT NULL, " +
                "Precio_codigo INTEGER NOT NULL, " +
                "Secuencia_codigo TEXT NOT NULL, " +
                "estado_codigo TEXT NOT NULL)";
        db.execSQL(createTableCodigos);
    }

    // Actualización de la base de datos si cambia la versión
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_datosTienda");
        db.execSQL("DROP TABLE IF EXISTS tbl_codigosRecarga");
        onCreate(db);
    }
}
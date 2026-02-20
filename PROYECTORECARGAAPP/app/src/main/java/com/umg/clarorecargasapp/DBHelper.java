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

        db.execSQL("INSERT INTO tbl_codigosRecarga (Tipo_codigo, Precio_codigo, Secuencia_codigo, estado_codigo) VALUES " +
                "('Internet', 8, '*905*2*1*', 'Activo'), " +
                "('Internet', 13, '*905*2*2*', 'Activo'), " +
                "('Internet', 18, '*905*2*3*', 'Activo'), " +
                "('Internet', 15, '*905*2*4*', 'Activo'), " +
                "('Internet', 35, '*905*2*5*', 'Activo'), " +
                "('Internet', 65, '*905*2*6*', 'Activo'), " +
                "('Internet', 120, '*905*2*7*', 'Activo'), " +
                "('Internet', 6, '*905*2*8*', 'Activo'), " +
                "('Todo incluido', 10, '*905*4*1*', 'Activo'), " +
                "('Todo incluido', 15, '*905*4*2*', 'Activo'), " +
                "('Todo incluido', 35, '*905*4*3*', 'Activo'), " +
                "('Todo incluido', 65, '*905*4*4*', 'Activo'), " +
                "('Todo incluido', 120, '*905*4*5*', 'Activo'), " +
                "('Todo incluido', 20, '*905*4*6*', 'Activo'), " +
                "('Todo incluido', 12, '*905*4*7*', 'Activo'), " +
                "('Todo incluido', 40, '*905*4*8*', 'Activo'), " +
                "('Todo incluido', 75, '*905*4*9*', 'Activo'), " +
                "('Minutos', 8, '*905*3*1*', 'Activo'), " +
                "('Minutos', 13, '*905*3*2*', 'Activo'), " +
                "('Minutos', 15, '*905*3*3*', 'Activo'), " +
                "('Minutos', 25, '*905*3*4*', 'Activo'), " +
                "('Minutos', 50, '*905*3*5*', 'Activo'), " +
                "('Minutos', 15, '*905*3*6*', 'Activo'), " +
                "('Redes sociales', 6, '*905*5*1*', 'Activo'), " +
                "('Redes sociales', 25, '*905*5*2*', 'Activo'), " +
                "('Redes sociales', 60, '*905*5*3*', 'Activo'), " +
                "('Redes sociales', 11, '*905*5*4*', 'Activo'), " +
                "('Redes sociales', 15, '*905*5*5*', 'Activo'), " +
                "('Redes sociales', 30, '*905*5*6*', 'Activo'),"+
                 "('Saldo', 0, '*777*1*', 'Activo');");
    }
    // Actualización de la base de datos si cambia la versión
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_datosTienda");
        db.execSQL("DROP TABLE IF EXISTS tbl_codigosRecarga");
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Lógica para manejar el downgrade, similar al upgrade
        db.execSQL("DROP TABLE IF EXISTS tbl_datosTienda");
        db.execSQL("DROP TABLE IF EXISTS tbl_codigosRecarga");
        onCreate(db);
    }
}
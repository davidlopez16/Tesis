package com.miapp.proyectodegrado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hp 3115 on 29/11/2017.
 */

public class UsualSQLiteHelper extends SQLiteOpenHelper {
    //creamos una variable que contendra la sentencia SQL de creacion de la tabla
    String sql = "CREATE TABLE Presupuesto (Actividad String, Unidad String, Cantidad INTEGER, PrecioMO INTEGER, Subtotal INTEGER) ";

    public UsualSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Este metodo se ejecuta automaticamente cuando la base de datos no existe(es decir crea la bd en la primera vez que se ejecuta)
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Esta metodo se ejecuta cuando la version de la base de datos cambio por lo que se debe definir
        // todos los procesos de migracion de la estructura anterior a la estructura nueva. Por simplicidad
        //del ejemplo lo que haremos es eliminar la tabla existente y lo crearia nuevamente
        db.execSQL("DROP TABLE IF EXITS Presupuesto");
        db.execSQL(sql);
    }
}

package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maria on 29/4/17.
 */

public class Ayudante extends SQLiteOpenHelper{

    public Ayudante(Context ctx, String nombre){
        super(ctx,nombre,null,1);
    }
    //Este metodo se ejecuta cuando Android crea la BD

    @Override
    public void onCreate(SQLiteDatabase db) {

        //montamos la instrucción SQL de creacion de la tabla

        String sqlCreaTabla="create table historial_usuario (";
        sqlCreaTabla+="_id integer primary key autoincrement,";
        sqlCreaTabla+="fecha, servicio, activo,  puntuacion)";

        //ejecutamos la instruccion
        db.execSQL(sqlCreaTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

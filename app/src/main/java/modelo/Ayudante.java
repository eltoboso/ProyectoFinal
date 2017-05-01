package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Ayudante extends SQLiteOpenHelper{

    public Ayudante(Context ctx, String nombre){
        super(ctx,nombre,null,1);
    }

    //Este método se ejecuta cuando Android crea la BD
    @Override
    public void onCreate(SQLiteDatabase db) {

        //montamos la instrucción SQL de creación de la tabla

        String sqlCreaTabla="create table historial_usuario (";
        sqlCreaTabla+="_id integer primary key autoincrement,";
        sqlCreaTabla+="activo, boolean,";
        sqlCreaTabla+="servicio text,";
        sqlCreaTabla+="dni int,";
        sqlCreaTabla+="nombre text,";
        sqlCreaTabla+="fecha text,";
        sqlCreaTabla+="puntuacion int)";

        //ejecutamos la instrucción
        db.execSQL(sqlCreaTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

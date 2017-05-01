package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteAux extends SQLiteOpenHelper{

    public AyudanteAux(Context ctx, String nombre){
        super(ctx,nombre,null,1);
    }

    //Este método se ejecuta cuando Android crea la BD
    @Override
    public void onCreate(SQLiteDatabase db) {

        //montamos la instrucción SQL de creación de la tabla

        String sqlCreaTablaAux="create table config_perfil (";
        sqlCreaTablaAux+="_id integer primary key autoincrement,";
        sqlCreaTablaAux+="usuario boolean)";

        //ejecutamos la instrucción
        db.execSQL(sqlCreaTablaAux);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

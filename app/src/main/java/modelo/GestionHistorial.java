package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import javabean.DatosServicio;

/**
 * Created by Maria on 29/4/17.
 */

public class GestionHistorial {
    private Ayudante helper;
    private SQLiteDatabase bd;

    public GestionHistorial(Context ctx){
        helper=new Ayudante(ctx,"historiales");
        bd=helper.getWritableDatabase();
    }


    //Pasamos por parte del usuario tras las puntuaciones


    public void guardarHistorial(DatosServicio serv){

        ContentValues valores=new ContentValues();
        valores.put("fecha",serv.getFecha());
        valores.put("servicio",serv.getCategoria());
        valores.put("activo", serv.isActivo());
        valores.put("puntuacion", serv.getPuntuacion());

        bd.insert("historial_usuario",null,valores);

    }

    public Cursor obtenerHistorial(){

        //Llama al método query
        return bd.query("historial_usuario",null,null,null,null,null,null);
    }


    public void cerrar()

    {
        helper.close();
    }


}

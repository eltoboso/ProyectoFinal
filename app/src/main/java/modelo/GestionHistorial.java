package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import javabean.DatosServicio;

public class GestionHistorial {
    private Ayudante helper;
    private AyudanteAux helPerfil;
    private SQLiteDatabase bd;
    private SQLiteDatabase bdPerf;

    public GestionHistorial(Context ctx){
        helper = new Ayudante(ctx,"historiales");
        helPerfil = new AyudanteAux(ctx,"perfil");
        bd = helper.getWritableDatabase();
        bdPerf = helPerfil.getWritableDatabase();
    }

    //Pasamos por parte del usuario tras las puntuaciones

    public void guardarHistorial(DatosServicio serv){

        ContentValues valores=new ContentValues();
        valores.put("activo", serv.isActivo());
        valores.put("servicio",serv.getCategoria());
        valores.put("dni",serv.getDni());
        valores.put("nombre",serv.getNombre());
        valores.put("fecha",serv.getFecha());
        valores.put("puntuacion", serv.getPuntuacion());

        bd.insert("historial_usuario",null,valores);
    }

    public Cursor obtenerHistorial(){

        //Llama al método query
        return bd.query("historial_usuario",null,null,null,null,null,null);
    }

    public void guardarPerfil(boolean perfil){
        ContentValues valores = new ContentValues();
        valores.put("usuario",perfil);

        bdPerf.insert("config_perfil",null,valores);
    }

    public String obtenerPerfil(){

        String res = null;
        String sql = "select * from config_perfil";
        Cursor c = bdPerf.rawQuery(sql,null);
        if (c.moveToNext()) {
            if (c.equals(true)) {
                res = "usuario";
            } else {
                res = "profesional";
            }
        }
        return res;
    }

    public void cerrar()

    {
        helper.close();
    }

    public void cerrarPerf()

    {
        helPerfil.close();
    }

}

package com.example.maria.proyectofinal_v3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javabean.DatosProfesional;
import javabean.DatosServicio;
import modelo.GestionComs;

public class MenuProfesional extends AppCompatActivity {
    private GestionComs comunicacion;
    private ListView lvServicios;
    private DatosProfesional pro;
    private ArrayList<DatosServicio> alertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesional);
        lvServicios = (ListView)this.findViewById(R.id.lvServicios);
        comunicacion = new GestionComs();
    }

    public void verServicios(View v){
        pro = new DatosProfesional(666, "Paco", "sombrerete",
        12, "madrid", "madrid", 28012, "fontanero", 666777888,
        "pacofontanero@fon.es", true);

        ComunicacionTask com = new ComunicacionTask();
        com.execute(pro);

        //mostrar alertas
    }

    private class ComunicacionTask extends AsyncTask<Object, Void, ArrayList<DatosServicio>> {

        //onPostExecute recibe la lista de DatosServicio que se guarda en el ArrayList servicios
        //para pasarlo a la Actividad Mapa
        @Override
        protected void onPostExecute(ArrayList<DatosServicio> servs) {
            alertas =  servs;
        }

        //El método doInBackground recibe dos parámetros Objeto: DatosUsuario y DatosServicio
        @Override
        protected ArrayList<DatosServicio> doInBackground(Object... params) {
            return comunicacion.recibirDatosPro((DatosProfesional) params[0]);
        }
    }
}
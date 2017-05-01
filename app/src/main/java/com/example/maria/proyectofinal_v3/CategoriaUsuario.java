package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import javabean.DatosServicio;
import javabean.DatosUsuario;
import modelo.GestionComs;

public class CategoriaUsuario extends AppCompatActivity {

    private GestionComs comunicacion;
    private DatosUsuario usuario;
    private DatosServicio servicio;
    private Intent intent;
    private String categoria;
    private ArrayList<DatosServicio> servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_usuario);
        intent = this.getIntent();
        usuario = (DatosUsuario)intent.getSerializableExtra("user");
        categoria = intent.getStringExtra("categoria");
        servicios = new ArrayList<>();
    }

    public void solicitar (View v){
        comunicacion = new GestionComs();
        ComunicacionTask com = new ComunicacionTask();

        // los datos fecha y dirección se recogen de los campos que rellene el usuario  ->>> PENDIENTE DE HACER
        servicio = new DatosServicio(true,usuario.getTelefono(),categoria,"30/04/2017",0,"nombre","direccion",0);

        // pasamos a la tarea asíncrona los javabeans: usuario y servicio
        // que enviaremos al servidor con el objeto GestionComs
        com.execute(usuario, servicio);

        // lanzamos la actividad Mapa
        // le pasamos el javabean DatosUsuario y la lista de DatosServicio que se cargará en el mapa
        intent = new Intent(this,MapaActivity.class);
        intent.putExtra("usuario",usuario);
        intent.putExtra("servicios", servicios);
        this.startActivity(intent);
    }

    private class ComunicacionTask extends AsyncTask<Object, Void, ArrayList<DatosServicio>> {

        //onPostExecute recibe la lista de DatosServicio que se guarda en el ArrayList servicios
        //para pasarlo a la Actividad Mapa
        @Override
        protected void onPostExecute(ArrayList<DatosServicio> servs) {
/*          String recibido = "";
            for (DatosServicio d:servs) {
                recibido = d.toJSONObject(d).toString();
            }
            Toast.makeText(CategoriaUsuario.this, recibido, Toast.LENGTH_LONG).show();
            System.out.println(recibido);
*/          servicios = servs;
        }

        //El método doInBackground recibe dos parámetros Objeto: DatosUsuario y DatosServicio
        @Override
        protected ArrayList<DatosServicio> doInBackground(Object... params) {
            return comunicacion.buscarProfesionales((DatosUsuario) params[0], (DatosServicio) params[1]);
        }
    }
}

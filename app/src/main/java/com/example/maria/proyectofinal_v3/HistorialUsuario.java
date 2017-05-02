package com.example.maria.proyectofinal_v3;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import modelo.GestionHistorial;

public class HistorialUsuario extends AppCompatActivity {

    boolean res1;
    ListView lvHist;
    GestionHistorial adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_usuario);

        lvHist=(ListView)this.findViewById(R.id.lvHistorial);

        res1=mostrar();

        if(!res1)
        {
            Toast.makeText(this, "No hay servicios solicitados", Toast.LENGTH_LONG).show();
        } else {
            lvHist.setOnItemClickListener(
                    new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent,
                                                android.view.View view,
                                                int position, long id) {

                            //Seleccionamos casilla con puntuación
                        //    view.
                            //Si la puntuación está a cero: sacamos la barra de puntuación
                            //y desactivamos el servicio

                            //Si la puntuación es distinta de cero, sacamos un toast
                        }
                    }
            );
        }

    }


    //Hacemos un método a parte para acceder a la base de datos y mostrar el historial

    private boolean mostrar(){

        boolean res=false;


        //HAY QUE COMPROBAR QUE NO ESTÁ VACIA PARA CAMBIAR RES=TRUE
        //Accedemos a la bbdd del terminal

        adp = new GestionHistorial(this);
        Cursor c = adp.obtenerHistorial();

        //Creamos un adaptador para la recogida de los datos de la BBDD
        SimpleCursorAdapter sc = new SimpleCursorAdapter(this,
                R.layout.fila_usuario,
                c,
                new String [] {"Fecha", "Servicio", "Activo", "Puntuacion"},
                new int[] {R.id.tvFecha, R.id.tvServicio, R.id.tvActivo, R.id.tvPuntuacion},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //Volcamos el contenido en el listview
        //Obtenemos la referencia al listview

        if (sc.isEmpty()) {
            res=true;
        } else {
            //Vuelco los datos
            lvHist.setAdapter(sc);

            //Cerramos la gestión de la bbdd
            adp.cerrar();
        }
        return res;
    }



}

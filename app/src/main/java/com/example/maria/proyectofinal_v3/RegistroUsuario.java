package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javabean.DatosServicio;
import javabean.DatosUsuario;

public class RegistroUsuario extends AppCompatActivity {

    //private GestionComs comunicacion;
    private DatosUsuario usuario;
    private DatosServicio servicio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);


    }

    //Ejecutamos el botón registrar

    public void registrar(View v) {
/*
        //Recogemos los datos de los editText
        EditText edtNombre = (EditText) this.findViewById(R.id.edtNombre);
        EditText edtEmail = (EditText) this.findViewById(R.id.edtEmail);

        //Obtenemos el telefono del terminal
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        //Montamos un javabean para enviarlo a la tarea asíncrona
        usuario = new DatosUsuario(edtNombre.getText().toString(),
                (int) Long.parseLong(tm.getLine1Number()),
                edtEmail.getText().toString());

        //Creamos javabean DatosServicio
        //dni=0 y puntuacion=0 ->requisitos para registro o modificación en el servidor

        servicio = new DatosServicio(true, (int) Long.parseLong(tm.getLine1Number()), "categoria", "fecha", 0, "direccion", 0);

        //Creo un objeto de la subclase de AsyncTask

        /*
        ComunicacionTask com=new ComunicacionTask();

        //Pasamos a la tarea asincrona los javabeans: usuario y servicio
        //com.execute(usuario, servicio);
        com.execute();

*/
        //lanzamos la actividad MenuUsuario

        //Creo objeto intent
        Intent intent = new Intent(this, MenuUsuario.class);
/*
        //Guardo los javabeans en el intent
        intent.putExtra("user", usuario);
        intent.putExtra("serv", servicio);
*/
        startActivity(intent);


    }
}
/*
    //En la prueba el primer parámetro es void
    //Según el execute, CAMBIAR

    class ComunicacionTask extends AsyncTask <Void, Void, Void> {
        @Override

        protected Void doInBackground(Void... params) {
            return null;
        }

        //El primer parámetro va al doInBackground: recibo un objeto datos usuario


        /*
        @Override
        protected Void doInBackground(String... params) {

            /*
            usuario=(DatosUsuario) params[0];
            servicio=(DatosServicio)params[1];

            //Creo el objeto comunicación
            comunicacion= new GestionComs();

            //Enviamos el registro del usuario al servidor

            comunicacion.enviarDatosUsuario(usuario.getNombre(),
                    Integer.parseInt(String.valueOf(usuario.getTelefono())),
                    usuario.getEmail());

            //Enviamos el registro (inicial) del servicio al servidor

            comunicacion.enviarDatosServicio(servicio);


            return null;
        }
        */

/*

        //El tercer parámetro va al postExecute

        @Override
        protected void onPostExecute(Void Void) {

            //Sacamos un toast: Usuario registrado al realizar la tarea
            //Toast.makeText(RegistroUsuario.this, "Usuario registrado", Toast.LENGTH_LONG).show();
        }
    }

}*/
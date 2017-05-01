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
import modelo.GestionComs;

public class RegistroUsuario extends AppCompatActivity {

    private GestionComs comunicacion;
    private DatosUsuario usuario;
    private DatosServicio servicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        comunicacion = new GestionComs();
    }

    //Ejecutamos el botón registrar
    public void registrar(View v) {

        //recogemos los datos de los editText
        EditText edtNombre = (EditText) this.findViewById(R.id.edtNombre);
        EditText edtEmail = (EditText) this.findViewById(R.id.edtEmail);

        //obtenemos el número de teléfono del terminal
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        //montamos un javabean que pasaremos como primer parámetro a la tarea asíncrona
        usuario = new DatosUsuario(edtNombre.getText().toString(),
                (int) Long.parseLong(tm.getLine1Number()),
                edtEmail.getText().toString());

        // creamos javabean DatosServicio con dni=0 y puntuacion=0
        // para que el servidor lo reconozca como petición de registro o modificación
        servicio = new DatosServicio(true, (int) Long.parseLong(tm.getLine1Number()), "categoria", "fecha", 0, "nombre","direccion", 0);

        // creamos objeto AsyncTask para que la comunicación con el servidor no bloquee el hilo principal
        ComunicacionTask com = new ComunicacionTask();

        // pasamos a la tarea asíncrona los javabeans: usuario y servicio
        // que enviaremos al servidor con el objeto GestionComs
        com.execute(usuario, servicio);

        // mostramos mensaje
        Toast.makeText(this, "Datos registrados", Toast.LENGTH_LONG).show();

        /////////////// hay que meter en BBDD guardarPerfil

        // lanzamos la actividad MenuUsuario
        // creamos objeto intent
        Intent intent = new Intent(this, MenuUsuario.class);

        // guardamos los javabeans en el intent
        intent.putExtra("user", usuario);

        this.startActivity(intent);
    }

    private class ComunicacionTask extends AsyncTask <Object, Void, Void> {
        //El método doInBackground recibe dos parámetros Objeto: DatosUsuario y DatosServicio
        @Override
        protected Void doInBackground(Object... params) {
            comunicacion.enviarDatosUsServ((DatosUsuario) params[0], (DatosServicio) params[1]);
            return null;
        }
    }

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
*/
}
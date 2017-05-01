package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javabean.DatosProfesional;
import modelo.GestionComs;

public class RegistroProfesional extends AppCompatActivity {
    private DatosProfesional pro;
    private GestionComs comunicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesional);
        comunicacion = new GestionComs();
    }

    //Programamos la respuesta al botón
    //1. Se enviarán los datos al servidor para proceder a registrarlo en la base de datos
    //2. Se lanzará la actividad MenuProfesional
    public void registrar(View v) {
        // Recogemos los datos de los editText
        EditText edtDni = (EditText) this.findViewById(R.id.edtDni);
        EditText edtEmail = (EditText) this.findViewById(R.id.edtEmail);
        EditText edtNombre = (EditText) this.findViewById(R.id.edtNombre);
        EditText edtProvincia = (EditText) this.findViewById(R.id.edtProvincia);
        EditText edtCiudad = (EditText) this.findViewById(R.id.edtCiudad);
        EditText edtCalle = (EditText) this.findViewById(R.id.edtCalle);
        EditText edtNumeroC = (EditText) this.findViewById(R.id.edtNumCalle);
        EditText edtCp = (EditText) this.findViewById(R.id.edtCodPostal);
        EditText edtTel = (EditText) this.findViewById(R.id.edtTelefono);
        //Obtenemos el nº de teléfono del terminal
        //TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        //Montamos un javabean que pasaremos como parámetro a la tarea asíncrona
        pro = new DatosProfesional(Integer.parseInt(edtDni.getText().toString()),
                edtNombre.getText().toString(),
                edtCalle.getText().toString(),
                Integer.parseInt(edtNumeroC.getText().toString()),
                edtCiudad.getText().toString(),
                edtProvincia.getText().toString(),
                Integer.parseInt(edtCp.getText().toString()),
                "electricista",                 // ->>>> aquí va el dato seleccionado en el spinner
                Integer.parseInt(edtTel.getText().toString()),
                edtEmail.getText().toString(),
                true);                          // disponible puesto a "true" por defecto

        //Creamos objeto AsyncTask para que la comunicación con el servidor no bloquee el hilo principal

        ComunicacionTask com = new ComunicacionTask();

        //Pasamos a la tarea asíncrona el javabean DatosProfesional
        //que enviaremos al servidor con el objeto GestionComs
        com.execute(pro);

        //mostramos mensaje
        Toast.makeText(this, "Datos registrados", Toast.LENGTH_LONG).show();

        //creamos objeto intent
        Intent intent = new Intent(this, MenuProfesional.class);

        //guardamos el javabean en el intent
        intent.putExtra("profesional", pro);

        //lanzamos la Actividad MenuProfesional
        this.startActivity(intent);

    }


    private class ComunicacionTask extends AsyncTask<Object, Void, Void> {
        //El método doInBackground recibe un parámetro Objeto: DatosProfesional
        @Override
        protected Void doInBackground(Object... params) {
            comunicacion.enviarDatosPro((DatosProfesional) params[0]);
            return null;
        }
    }
}
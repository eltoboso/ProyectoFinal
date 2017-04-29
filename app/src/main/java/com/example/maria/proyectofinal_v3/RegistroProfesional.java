package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistroProfesional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesional);


    }

    //Programamos la respuesta al botón
    //1. Se enviarán los datos al servidor para proceder a registrarlo en la base de datos
    //2. Se lanzará la actividad menú profesional
    public void registrar (View v){

        //Lanzamos el menu profesional
        Intent intent=new Intent(this, MenuProfesional.class);

        startActivity(intent);

    }

    /*
    private ArrayList<DatosProfesional> listado=new ArrayList<>();
    private GestionComs coms = new GestionComs();




    public void guardar (View v){
        EditText edtDni=(EditText)this.findViewById(R.id.edtDni);
        EditText edtEmail=(EditText)this.findViewById(R.id.edtEmail);
        EditText edtNombre=(EditText)this.findViewById(R.id.edtNombre);
        EditText edtProvincia=(EditText)this.findViewById(R.id.edtProvincia);
        EditText edtCiudad=(EditText)this.findViewById(R.id.edtCiudad);
        EditText edtCalle=(EditText)this.findViewById(R.id.edtCalle);
        EditText edtNumeroC=(EditText)this.findViewById(R.id.edtNumeroc);
        EditText edtCp=(EditText)this.findViewById(R.id.edtCp);
        EditText edtTel=(EditText)this.findViewById(R.id.edtTelefono);

        DatosProfesional pro =new DatosProfesional(Integer.parseInt(edtDni.getText().toString()),
                edtNombre.getText().toString(),
                edtCalle.getText().toString(),
                Integer.parseInt(edtNumeroC.getText().toString()),
                edtCiudad.getText().toString(),
                edtProvincia.getText().toString(),
                Integer.parseInt(edtCp.getText().toString()),
                "electricista",
                Integer.parseInt(edtTel.getText().toString()),
                edtEmail.getText().toString(),
                true); // disponible puesto a "true" por defecto
        listado.add(pro);
        coms.enviarDatosPro(pro);
    }
    */

}

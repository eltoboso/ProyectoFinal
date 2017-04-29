package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //ACTIVIDAD INICIAL: elegimos perfil: Usuario o Profesional

    public void usuario (View v)
    {
        //lanzamos la actividad registro usuario

        //Creo objeto intent
        Intent intent=new Intent(this, RegistroUsuario.class);

        //Lanzo la actividad
        this.startActivity(intent);



    }


    public void profesional (View v)
    {
        //lanzamos la actividad registro profesional

        //Creo objeto intent
        Intent intent=new Intent (this, RegistroProfesional.class);

        //Lanza actividad
        this.startActivity(intent);

    }

}

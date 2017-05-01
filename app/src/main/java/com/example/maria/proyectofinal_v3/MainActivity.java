package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import modelo.GestionHistorial;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private GestionHistorial hist;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox)this.findViewById(R.id.checkBox);
        hist = new GestionHistorial(this);

        // comprobación inicial -> si el usuario/pro ya está registrado
        // lanzamos la actividad correspondiente
        if (hist.obtenerPerfil()!=null) {
            if (hist.obtenerPerfil()=="usuario") {
                intent = new Intent(this,MenuUsuario.class);
                this.startActivity(intent);
            } else {
                intent = new Intent(this,MenuProfesional.class);
                this.startActivity(intent);
            }
        }
    }

    //ACTIVIDAD INICIAL: elegimos perfil: Usuario o Profesional

    public void usuario (View v) {
        if (checkBox.isChecked()) {
            hist.guardarPerfil(true);
        }

        //creamos objeto intent
        intent = new Intent(this, RegistroUsuario.class);

        //lanzamos la actividad RegistroUsuario
        this.startActivity(intent);
    }


    public void profesional (View v) {
        if (checkBox.isChecked()) {
            hist.guardarPerfil(false);
        }

        //creamos objeto intent
        intent = new Intent (this, RegistroProfesional.class);

        //lanzamos la actividad RegistroProfesional
        this.startActivity(intent);
    }

}

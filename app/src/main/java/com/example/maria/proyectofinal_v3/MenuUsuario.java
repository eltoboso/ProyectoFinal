package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
    }

    public void misServicios(View v){

        //Lanza la actividad HistorialUsuario
        Intent intent=new Intent(this, HistorialUsuario.class);
        startActivity(intent);

    }


    //cada uno de estos métodos (imagebutton) lanza la actividad CategoriaUsuario

    public void fontanero(View v){
        Intent intent=new Intent(this, CategoriaUsuario.class);
        intent.putExtra("categoria","fontanero");
        startActivity(intent);
    }
}

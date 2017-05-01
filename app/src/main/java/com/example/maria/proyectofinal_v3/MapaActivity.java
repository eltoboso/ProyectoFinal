package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import javabean.DatosServicio;
import javabean.DatosUsuario;
import modelo.GestionComs;

public class MapaActivity extends AppCompatActivity {
    private GestionComs comunicacion;
    private DatosUsuario usuario;
    private DatosServicio servicio;
    private Intent intent;
    private ArrayList<DatosServicio> servicios;
    TextView tvNom;
    TextView tvDni;
    TextView tvCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        tvNom = (TextView)this.findViewById(R.id.tvNom);
        tvDni = (TextView)this.findViewById(R.id.tvDni);
        tvCat = (TextView)this.findViewById(R.id.tvCat);
        intent = new Intent();
        servicios = (ArrayList<DatosServicio>)intent.getSerializableExtra("servicios");
        usuario = (DatosUsuario)intent.getSerializableExtra("usuario");
        for (DatosServicio d:servicios) {
            servicio = d;
            System.out.println(d.toString());
        }
    }

    public void cargar(View v) {
        tvNom.setText(servicio.getNombre());
        tvDni.setText(servicio.getDni());
        tvCat.setText(servicio.getCategoria());
    }
}

package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.location.Address;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javabean.DatosServicio;
import javabean.DatosUsuario;
import modelo.GestionComs;
import modelo.GestionDirecciones;

public class MapaActivity extends AppCompatActivity {
    private GoogleMap gm;
    private SupportMapFragment smf;
    private ArrayList<DatosServicio> servicios;
    private DatosUsuario usuario;
    private List<Address> dirLatLong;
    private GestionDirecciones gDirecc;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        intent = new Intent();
        servicios = (ArrayList<DatosServicio>)intent.getSerializableExtra("servicios");
        usuario = (DatosUsuario)intent.getSerializableExtra("usuario");
        //Recorremos el array list de servicios disponibles
        //De cada elemento saco direccion separada por comas:calle,numero,ciudad
        //Paso a latitud y longitud para ponerlos en un marcador

        gDirecc = new GestionDirecciones(this);

        //Ponemos el mapa
        FragmentManager fm=this.getSupportFragmentManager();
        smf=(SupportMapFragment) fm.findFragmentById(R.id.map);
        smf.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gm= googleMap;
                // tipo de mapa
                gm.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                // controles
                gm.getUiSettings().setZoomControlsEnabled(true);
                gm.getUiSettings().setMapToolbarEnabled(true);
                gm.getUiSettings().setMyLocationButtonEnabled(true);

                for(DatosServicio c: servicios)
                {
                    final double punt=c.getPuntuacion();
                    dirLatLong=gDirecc.coorDesdeDir(c.getDireccion());

                    //gm.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion?,18));

                    MarkerOptions mk = new MarkerOptions();

                    LatLng pos = new LatLng(dirLatLong.get(0).getLatitude(),
                            dirLatLong.get(0).getLongitude());


                    mk.position(pos);
                    mk.title(c.getNombre());
                    gm.addMarker(mk);

                    //Respuesta al click en el marcador
                    gm.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {

                            //Salga la puntuacion
                            Toast.makeText(MapaActivity.this,String.valueOf(punt),
                                    Toast.LENGTH_LONG).show();
                            return false;
                        }
                    });
                }
            }
        });
        /*        for (DatosServicio d:servicios) {
            servicio = d;
            System.out.println(d.toString());
        }   */
    }

    public void cargar(View v) {    }
}

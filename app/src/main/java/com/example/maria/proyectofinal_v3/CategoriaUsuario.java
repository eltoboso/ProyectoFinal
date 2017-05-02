package com.example.maria.proyectofinal_v3;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javabean.DatosServicio;
import javabean.DatosUsuario;
import modelo.GestionComs;
import modelo.GestionDirecciones;

public class CategoriaUsuario extends AppCompatActivity {

    private EditText edtCalle, edtNum, edtCiudad;
    private CheckBox cbDireccion;
    private TextView fecha;

    private DatosUsuario usuario;
    private DatosServicio servicio;

    private GestionComs comunicacion;

    private Intent intent;
    private String categoria;
    private ArrayList<DatosServicio> servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_usuario);
    //  obtenemos referencias a los widgets
        edtCalle = (EditText)findViewById(R.id.edtCalle);
        edtNum = (EditText)findViewById(R.id.edtNum);
        edtCiudad = (EditText)findViewById(R.id.edtCiudad);
        cbDireccion=(CheckBox)findViewById(R.id.cbDireccion);
        fecha = (TextView)findViewById(R.id.tvFecha);
        //  obtenemos el intent
        intent = this.getIntent();
    //  recogemos la categoría y los datos del usuario del intent
        usuario = (DatosUsuario)intent.getSerializableExtra("user");
        categoria = intent.getStringExtra("categoria");
        servicios = new ArrayList<>();
    }

//  Configuramos la solicitud del servicio
    public void solicitar (View v){
        GestionDirecciones gDir = new GestionDirecciones(this);
        comunicacion = new GestionComs();
        ComunicacionTask com = new ComunicacionTask();

    //  la dirección la recogemos o bien del checkBox o bien del editext
        if (cbDireccion.isChecked()) {
            try {
            //  creamos el objeto que gestiona el servicio de localización
                LocationManager lm = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
            //  obtenemos la localización del terminal
                Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //  geocodificamos coordenadas a dirección
                List<Address> direccion = gDir.dirDesdeCoor(loc.getLatitude(), loc.getLongitude());
            //  pasamos dirección a javabean DatosServicio
                servicio.setDireccion(direccion.get(0).getAddressLine(0));
            } catch (SecurityException ex) {
                ex.printStackTrace();
            }
        } else {
            servicio.setDireccion(edtCalle.getText().toString()+","+edtNum.getText().toString()+","+edtCiudad.getText().toString());
        }

        // los datos fecha y dirección se recogen de los campos que rellene el usuario  ->>> PENDIENTE DE HACER
        servicio = new DatosServicio(true,usuario.getTelefono(),categoria,"30/04/2017",0,"nombre",servicio.getDireccion(),0);

        // pasamos a la tarea asíncrona los javabeans: usuario y servicio
        // que enviaremos al servidor con el objeto GestionComs
        com.execute(usuario, servicio);
    }

//  método que saca un cuadro calendario para elegir la fecha y lo vuelca en el textView
    @SuppressLint("NewApi")
    public void fecha (View v){

        //Creamos el cuadro de diálogo y lo mostramos
        new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    //  mostramos la fecha en el text view
                        String fecha = dayOfMonth + "-" + (month + 1) + "-" + year;
                    /*  ponemos la fecha en el editText     ->> de momento lo quito porque me da problema(?)
                        fecha.setText(fecha);       */
                    //  metemos la fecha en el javabean
                        servicio.setFecha(fecha);
                    }
                },
                2017,
                4,
                1).show();
    }
    private class ComunicacionTask extends AsyncTask<Object, Void, ArrayList<DatosServicio>> {

        //El método doInBackground recibe dos parámetros Objeto: DatosUsuario y DatosServicio
        @Override
        protected ArrayList<DatosServicio> doInBackground(Object... params) {
            return comunicacion.buscarProfesionales((DatosUsuario) params[0], (DatosServicio) params[1]);
        }

        //onPostExecute recibe la lista de DatosServicio que se guarda en el ArrayList servicios
        //para pasarlo a la Actividad Mapa
        @Override
        protected void onPostExecute(ArrayList<DatosServicio> servs) {
            if (servs.isEmpty()) {
                Toast.makeText(CategoriaUsuario.this,
                        "Servicio no disponible en este momento",
                        Toast.LENGTH_LONG).show();
            } else {
                CategoriaUsuario.this.startActivity(intent);
            //  lanzamos la actividad Mapa
            //  le pasamos el javabean DatosUsuario y la lista de DatosServicio que se cargará en el mapa
                servicios = servs;
                intent = new Intent(CategoriaUsuario.this, MapaActivity.class);
                intent.putExtra("usuario", usuario);
                intent.putExtra("servicios", servicios);
                CategoriaUsuario.this.startActivity(intent);
            }
        }
    }
}

package modelo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GestionDirecciones {
    private Context ctx;

    public GestionDirecciones (Context ctx) {
        this.ctx = ctx;
    }

    public List<Address> coorDesdeDir(String direccion) {
        String[] dir = direccion.split(",");
        // formateamos el parámetro String dirección para que siempre se adapte
        // a los requerimientos del método geo.getFromLocationName
        direccion = dir[0]+","+Integer.parseInt(dir[1])+","+dir[2];

        // creamos objeto Geocoder
        Geocoder geo = new Geocoder(ctx, new Locale("ES"));
        List<Address> lista = null;
        try {
            // pasamos parámetro formateado al método para que nos devuelva geolocalización
            lista = geo.getFromLocationName(direccion, 2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<Address> dirDesdeCoor(double latitude, double longitude) {
        Geocoder geo = new Geocoder(ctx, new Locale("ES"));
        List<Address> lista = null;
        try {
            lista = geo.getFromLocation(latitude, longitude, 1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
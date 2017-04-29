package javabean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Maria on 28/4/17.
 */

public class DatosServicio implements Serializable {


    private boolean activo;
    private int telefono;
    private String categoria, fecha;
    private int dni;
    private String direccion;
    private double puntuacion;

    public DatosServicio(boolean activo, int telefono,
                         String categoria, String fecha, int dni,
                         String direccion, double puntuacion) {
        super();
        this.activo = activo;
        this.telefono = telefono;
        this.categoria = categoria;
        this.fecha = fecha;
        this.dni = dni;
        this.direccion = direccion;
        this.puntuacion = puntuacion;
    }
    public boolean isActivo() {
        return activo;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public int getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPuntuacion (double puntuacion) {
        this.puntuacion = puntuacion;
    }


    public JSONObject toJSONObject (DatosServicio serv) {
        JSONObject job = new JSONObject();
        try {
            job.put("activo", serv.isActivo());
            job.put("telefono", serv.getTelefono());
            job.put("categoria", serv.getCategoria());
            job.put("fecha", serv.getFecha());
            job.put("dni", serv.getDni());
            job.put("direccion", serv.getDireccion());
            job.put("puntuacion", serv.getPuntuacion());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return job;
    }

    public DatosServicio fromJSONObject (JSONObject job) {
        DatosServicio serv = null;
        try {
            serv = new DatosServicio(job.getBoolean("activo"),
                    job.getInt("telefono"),
                    job.getString("categoria"),
                    job.getString("fecha"),
                    job.getInt("dni"),
                    job.getString("direccion"),
                    job.getDouble("puntuacion"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return serv;
    }

    public String[] toString(DatosServicio serv){
        String[] servicio = {""+serv.isActivo(),""+serv.getTelefono(),""+serv.getCategoria(),""+
                serv.getFecha(),""+serv.getDni(),""+serv.getDireccion(),""+serv.getPuntuacion()};
        return servicio;
    }

    public DatosServicio fromString (String[] serv){
        DatosServicio servicio = new DatosServicio(Boolean.parseBoolean(serv[0]),Integer.parseInt(serv[1]),
                serv[2],serv[3],Integer.parseInt(serv[4]),serv[5],Double.parseDouble(serv[6]));
        return servicio;
    }
}


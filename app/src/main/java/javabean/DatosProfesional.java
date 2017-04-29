package javabean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class DatosProfesional implements Serializable{
    private int dni;
    private String nombre, calle;
    private int numero_calle;
    private String ciudad, provincia;
    private int cod_post;
    private String servicio;
    private int telefono;
    private String email;
    private boolean disponible;
    private double puntuacion;
    private int cont_punt;

    public DatosProfesional(int dni, String nombre, String calle,
                            int numero_calle, String ciudad, String provincia,
                            int cod_post, String servicio, int telefono,
                            String email, boolean disponible) {
        this.dni = dni;
        this.nombre = nombre;
        this.calle = calle;
        this.numero_calle = numero_calle;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.cod_post = cod_post;
        this.servicio = servicio;
        this.telefono = telefono;
        this.email = email;
        this.disponible = disponible;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero_calle() {
        return numero_calle;
    }

    public void setNumero_calle(int numero_calle) {
        this.numero_calle = numero_calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCod_post() {
        return cod_post;
    }

    public void setCod_post(int cod_post) {
        this.cod_post = cod_post;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getCont_punt() {
        return cont_punt;
    }

    public void setCont_punt(int cont_punt) {
        this.cont_punt = cont_punt;
    }

    public JSONObject toJSONObject (DatosProfesional pro) {
        JSONObject job = new JSONObject();
        try {
            job.put("dni", pro.getDni());
            job.put("nombre", pro.getNombre());
            job.put("calle", pro.getCalle());
            job.put("numeroCalle", pro.getNumero_calle());
            job.put("ciudad", pro.getCiudad());
            job.put("provincia", pro.getProvincia());
            job.put("codPos", pro.getCod_post());
            job.put("servicio", pro.getServicio());
            job.put("telefono", pro.getTelefono());
            job.put("email", pro.getEmail());
            job.put("activo", pro.isDisponible());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return job;
    }

    public DatosProfesional fromJSONObject (JSONObject job) {
        DatosProfesional pro = null;
        try {
            pro = new DatosProfesional(job.getInt("dni"),
                    job.getString("nombre"),
                    job.getString("calle"),
                    job.getInt("numeroCalle"),
                    job.getString("ciudad"),
                    job.getString("provincia"),
                    job.getInt("codPos"),
                    job.getString("servicio"),
                    job.getInt("telefono"),
                    job.getString("email"),
                    job.getBoolean("activo"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return pro;
    }
}
package modelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import javabean.DatosProfesional;
import javabean.DatosServicio;
import javabean.DatosUsuario;

public class GestionComs {
    private DatosServicio serv;
    private DatosUsuario user;

    // envío de javabeans DatosUsuario y DatosServicio al servidor -> sin respuesta
    public void enviarDatosUsServ(DatosUsuario user, DatosServicio serv) {
        try {
            Socket sc = new Socket("192.168.1.129", 8000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            JSONObject job = toJSONObject(user,serv);
            salida.println(job.toString());
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//  envío de javabeans DatosUsuario y DatosServicio al servidor
//  recepción de lista de servicios (filtrados por disponible y categoría) que se cargará en el mapa
//  también lo usamos al solicitar un profesional específico
    //   -> en DatosServicio enviamos el dni del profesional
    public ArrayList<DatosServicio> buscarProfesionales(DatosUsuario user, DatosServicio serv) {
        ArrayList<DatosServicio> profesionales = new ArrayList<>();
        try {
            //  enviamos javabeans al servidor como JSON
            Socket sc = new Socket("192.168.1.129", 8000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            JSONObject solicita = toJSONObject(user, serv);
            salida.println(solicita);

            BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            if (bf.readLine() != null) {
            ////  recibimos arrayJSON, lo recorremos creando un javabean DatosServicio por cada objetoJSON
                JSONArray jsonArray = new JSONArray(bf.readLine());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject job = jsonArray.getJSONObject(i);
                    DatosServicio servicio = null;
                    servicio.fromJSONObject(job);
                ////  añadimos a la lista de profesionales los servicios de la categoría seleccionada
                    profesionales.add(servicio);
                }
            } else {
                //  devolvemos la lista al usuario o le decimos que está vacía
                profesionales = null;
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return profesionales;
    }


//  método auxiliar para hacer un objeto JSON a partir de los javabeans DatosUsuario y DatosServicio
    private JSONObject toJSONObject (DatosUsuario user, DatosServicio serv) {
        JSONObject job = new JSONObject();
        try {
            job.put("nombre", user.getNombre());
            job.put("telefono", user.getTelefono());
            job.put("email", user.getEmail());
            //  los dos javabeans van en el mismo JSONObject
            job.put("activo", serv.isActivo());
            job.put("telf", serv.getTelf());
            job.put("categoria", serv.getCategoria());
            job.put("fecha", serv.getFecha());
            job.put("dni", serv.getDni());
            job.put("nombrePro", serv.getNombre());
            job.put("direccion", serv.getDireccion());
            job.put("puntuacion", serv.getPuntuacion());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return job;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


//  envío de DatosProfesional al servidor para registro o modificación -> sin respuesta
    public void enviarDatosPro(DatosProfesional pro) {
        try {
            JSONObject job = pro.toJSONObject(pro);
            Socket sc = new Socket("192.168.1.129", 9000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            salida.println(job.toString());
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//  envío de DatosProfesional al servidor para recibir alerta(lista) de servicios solicitados
    public ArrayList<DatosServicio> recibirDatosPro(DatosProfesional pro){
        ArrayList<DatosServicio> servicios = new ArrayList<>();
        try {
            //  enviamos javabean al servidor como JSON
            JSONObject job = pro.toJSONObject(pro);
            Socket sc = new Socket("192.168.1.129", 7000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            salida.println(job.toString());

            //  si en DatosProfesional decimos que el profesional no está disponible
            //  no recibimos respuesta -> el servidor borra las solicitudes de la lista de servicios
            if (!pro.isDisponible()) {
                sc.close();
            } else {
                BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                if (bf.readLine() != null) {
                    //  recibimos arrayJSON, lo recorremos creando un javabean DatosServicio por cada objetoJSON
                    JSONArray jsonArray = new JSONArray(bf.readLine());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsOb = jsonArray.getJSONObject(i);
                        serv = serv.fromJSONObject(jsOb);
                        //  añadimos a la lista de profesionales los servicios de la categoría seleccionada
                        servicios.add(serv);
                    }
                } else {
                    // si no recibimos nada, le decimos al profesional que no tiene solicitudes
                    servicios = null;
                }
                sc.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  devolvemos la lista al profesional
        return servicios;
    }

}





/*
//  UsuarioActivity y MenuUsuarioActivity (cuando se ejecuta, desde onClick del botón/menú, saca un Toast: "Datos registrados")
//  recibe nombre de EditText, telefono del propio terminal, email de EditText
//  envía datos al servidor para registro o modificación
    public void enviarDatosUsuario(String nombre, int telefono, String email) {
        user = new DatosUsuario(nombre,telefono,email);
        try {
            Socket sc = new Socket("192.168.0.192", 8000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            JSONObject job = new JSONObject();
            job.put("nombre", nombre);
            job.put("telefono", telefono);
            job.put("email", email);
            salida.println(job.toString());
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//  MainActivity, MenuUsuarioActivity y SolicitaProfesionalActivity
//  envía datos de servicio: si es para registro o modificación "activo"-> true | "dni"-> 0 | "puntuacion"-> 0
    public void enviarDatosServicio(DatosServicio serv) {
        this.serv = serv;
        try {
            Socket sc = new Socket("192.168.0.192", 8000);
            PrintStream salida = new PrintStream(sc.getOutputStream());
            JSONObject job = serv.toJSONObject(serv);
            salida.println(job.toString());
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

 */
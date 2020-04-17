/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.lista;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Pardo
 */
public class ListaReproduccion {

    private int id;
    private String nombre;
    private Date fecha;
    private int tema;
    private int usuario;
    private ArrayList<Integer> videos;

    public ListaReproduccion(int id, String nombre, Date fecha, int tema, int usuario, ArrayList<Integer> videos) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tema = tema;
        this.usuario = usuario;
        this.videos = videos;
    }

    public ListaReproduccion(String nombre, Date fecha, int tema, int usuario, ArrayList<Integer> videos) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tema = tema;
        this.usuario = usuario;
        this.videos = videos;
    }

    public ListaReproduccion(String nombre, Date fecha, int tema, int usuario) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tema = tema;
        this.usuario = usuario;
    }

    public ListaReproduccion(int id, String nombre, Date fecha, int tema, int usuario) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tema = tema;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTema() {
        return tema;
    }

    public void setTema(int tema) {
        this.tema = tema;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Integer> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Integer> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "ListaReproduccion{" + "id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", tema=" + tema + ", usuario=" + usuario + ", videos=" + videos + '}';
    }
    
  

}

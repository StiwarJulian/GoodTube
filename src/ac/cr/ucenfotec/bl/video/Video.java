/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.video;

import java.util.Date;

/**
 *
 * @author Pardo
 */
public class Video {

    private int id;
    private String nombre;
    private Date fecha;
    private String ruta;
    private int calificacion;
    private int categoria;
    private int usuario;
    private double valor;

    public static String[] CALIFICACIONES = {"Malo", "Bueno", "Muy Bueno"};

    public Video(int id, String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.ruta = ruta;
        this.calificacion = calificacion;
        this.categoria = categoria;
        this.usuario = usuario;
        this.valor = valor;
    }

    public Video(String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ruta = ruta;
        this.calificacion = calificacion;
        this.categoria = categoria;
        this.usuario = usuario;
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + "," + fecha + "," + calificacion + "," + categoria + "," + usuario + "," + valor;
    }

}

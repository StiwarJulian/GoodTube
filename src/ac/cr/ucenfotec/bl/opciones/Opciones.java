/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.opciones;

/**
 *
 * @author Pardo
 */
public class Opciones {

    private int id;
    private String color;
    private String vista;
    private int usuario;
    public static String OPCION_VIDEO = "Video";
    public static String OPCION_LISTA = "Lista";
    
    
    public Opciones(String color, String vista, int usuario) {
        this.color = color;
        this.vista = vista;
        this.usuario = usuario;
    }

    public Opciones(int id, String color, String vista, int usuario) {
        this.id = id;
        this.color = color;
        this.vista = vista;
        this.usuario = usuario;
    }

   
  
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final Opciones other = (Opciones) obj;
        if (this.usuario != other.usuario) {
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

  

}

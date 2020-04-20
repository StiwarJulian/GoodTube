/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.reproduccion;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Pardo
 */
public class Reproduccion {

    private int tiempo;
    private int usuario;
    private int video;

    public Reproduccion(int tiempo, int usuario, int video) {
        this.tiempo = tiempo;
        this.usuario = usuario;
        this.video = video;
    }
    
    public int getTiempo() {
        return tiempo;
    }


    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Reproduccion{" + "tiempo=" + tiempo + ", usuario=" + usuario + ", video=" + video + '}';
    }

    
    
  

}

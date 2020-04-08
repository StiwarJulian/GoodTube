/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.video.IVideoDAO;
import ac.cr.ucenfotec.bl.video.Video;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class ControllerVideo {

    public static void registrar(String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IVideoDAO dao = factory.getVideosDao();
        dao.insertar(nombre, fecha, ruta, calificacion, categoria, usuario, valor);
    }

    public static void modificar(int id,String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IVideoDAO dao = factory.getVideosDao();
        dao.modificar(id, nombre, fecha, ruta, calificacion, categoria, usuario, valor);
    }
    
    public static HashMap<Integer,Video> listar(){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IVideoDAO dao = factory.getVideosDao();
        return dao.listar();
    }
}

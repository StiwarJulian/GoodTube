/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.reproduccion.IReproduccionDAO;
import ac.cr.ucenfotec.bl.reproduccion.Reproduccion;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class ControllerReproduccion {

    public static void registrar(int tiempo, int usuario, int video) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IReproduccionDAO dao = factory.getReproduccionDAO();
        dao.insertar(tiempo, usuario, video);
    }

    public static void eliminar(int id) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IReproduccionDAO dao = factory.getReproduccionDAO();
        dao.eliminar(id);
    }

    public static HashMap<Integer, Reproduccion> listar() {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IReproduccionDAO dao = factory.getReproduccionDAO();
        return dao.listar();
    }

}

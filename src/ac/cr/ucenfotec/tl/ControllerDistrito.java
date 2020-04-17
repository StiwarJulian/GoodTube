/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.distrito.Distrito;
import ac.cr.ucenfotec.bl.distrito.IDistritoDAO;
import ac.cr.ucenfotec.bl.factory.DaoFactory;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class ControllerDistrito {

    public static void registrar(String nombre, int canton) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IDistritoDAO dao = factory.getDistritoDAO();
        dao.insertar(nombre, canton);
    }

    public static void modificar(int id, String nombre, int canton) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IDistritoDAO dao = factory.getDistritoDAO();
        dao.modificar(id, nombre, canton);
    }

    public static HashMap<Integer, Distrito> listar() {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IDistritoDAO dao = factory.getDistritoDAO();
        return dao.listar();
    }

    public static void eliminar(int id) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IDistritoDAO dao = factory.getDistritoDAO();
        dao.eliminar(id);
    }
}

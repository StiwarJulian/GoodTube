/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.canton.Canton;
import ac.cr.ucenfotec.bl.canton.ICantonDAO;
import ac.cr.ucenfotec.bl.factory.DaoFactory;
import java.util.HashMap;
/**
 *
 * @author Pardo
 */
public class ControllerCanton {

    public static void registrar(String nombre, int provincia) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ICantonDAO dao = factory.getCantonDAO();
        dao.insertar(nombre, provincia);
    }

    public static void modificar(int id, String nombre, int provincia) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ICantonDAO dao = factory.getCantonDAO();
        dao.modificar(id, nombre, provincia);
    }

    public static HashMap<Integer, Canton> listar(){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ICantonDAO dao = factory.getCantonDAO();
        return dao.listar();
    }
}

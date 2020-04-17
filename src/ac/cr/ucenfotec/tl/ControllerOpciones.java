/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.opciones.IOpcionesDAO;
import ac.cr.ucenfotec.bl.opciones.Opciones;
import java.util.HashMap;
/**
 *
 * @author Pardo
 */
public class ControllerOpciones {

    public static void registrar(String color, String vista, int usuario) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IOpcionesDAO dao = factory.getOpcionesDAO();
        dao.insertar(color, vista, usuario);
    }

    public static void eliminar(int id) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IOpcionesDAO dao = factory.getOpcionesDAO();
        dao.eliminar(id);
    }
    
    public static HashMap<Integer, Opciones> listar(){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IOpcionesDAO dao = factory.getOpcionesDAO();
        return dao.listar();
    }
}

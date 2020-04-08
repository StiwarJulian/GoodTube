/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;


import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.tema.ITemaDAO;
import ac.cr.ucenfotec.bl.tema.Tema;
import java.util.HashMap;
/**
 *
 * @author Pardo
 */
public class ControllerTema {

    public static void registrar(String nombre, String descripcion) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ITemaDAO dao = factory.getTemaDAO();
        dao.insertar(nombre, descripcion);
    }

    public static void modificar(int id, String nombre, String descripcion) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ITemaDAO dao = factory.getTemaDAO();
        dao.modificar(id, nombre, descripcion);
    }

    public static HashMap<Integer, Tema> listar(){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ITemaDAO dao = factory.getTemaDAO();
        return dao.listar();
    }
}

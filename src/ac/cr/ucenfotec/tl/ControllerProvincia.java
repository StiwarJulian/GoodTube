/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.provincia.IProvinciaDAO;
import ac.cr.ucenfotec.bl.provincia.Provincia;
import java.util.HashMap;
/**
 *
 * @author Pardo
 */
public class ControllerProvincia {

    public static void registrar(String nombre) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IProvinciaDAO dao = factory.getProvinciaDAO();
        dao.insertar(nombre);
    }

    public static void modificar(int id, String nombre) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IProvinciaDAO dao = factory.getProvinciaDAO();
        dao.modificar(id, nombre);
    }

    public static HashMap<Integer, Provincia> listar(){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IProvinciaDAO dao = factory.getProvinciaDAO();
        return dao.listar();
    }
}

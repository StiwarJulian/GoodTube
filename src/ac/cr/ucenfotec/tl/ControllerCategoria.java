/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.categoria.Categoria;
import ac.cr.ucenfotec.bl.categoria.ICategoriaDAO;
import ac.cr.ucenfotec.bl.factory.DaoFactory;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class ControllerCategoria {

    public static void registrar(String nombre, String descripcion) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ICategoriaDAO dao = factory.getCategoriaDAO();
        dao.insertar(nombre, descripcion);
    }

    public static void modificar(int id, String nombre, String descripcion) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ICategoriaDAO dao = factory.getCategoriaDAO();
        dao.modificar(id, nombre, descripcion);
    }

    public static HashMap<Integer, Categoria> listar() {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ICategoriaDAO dao = factory.getCategoriaDAO();
        return dao.listar();
    }

    public static void eliminar(int id) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        ICategoriaDAO dao = factory.getCategoriaDAO();
        dao.eliminar(id);
    }
}

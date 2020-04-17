/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.lista.IListaReproduccionDAO;
import ac.cr.ucenfotec.bl.lista.ListaReproduccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class ControllerListaReproduccion {

    public static void registrar(String nombre, Date fecha, int tema, int usuario) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        dao.insertar(nombre, fecha, usuario, tema);
    }

    public static void agregarVideo(int lista, int video) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        dao.agregarVideo(lista, video);
    }

    public static void eliminarVideo(int lista, int video) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        dao.eliminarVideo(lista, video);
    }

    public static void modificar(int id, String nombre, Date fecha, int tema, int usuario) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        dao.modificar(id, nombre, fecha, usuario, tema);
    }

    public static void eliminar(int id) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        dao.eliminar(id);
    }

    public static HashMap<Integer, ListaReproduccion> listar() {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        return dao.listar();
    }
    public static ArrayList<Integer> listarVideos(int lista) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        return dao.listarVideos(lista);
    }    

    public static void comprar(int usuario, int video, int tema) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IListaReproduccionDAO dao = factory.getListaReproduccionDao();
        dao.comprar(usuario,video,tema);
    }
}

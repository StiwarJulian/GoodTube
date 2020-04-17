/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.lista;

import ac.cr.ucenfotec.bl.video.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface IListaReproduccionDAO {

    public void insertar(String nombre, Date fecha, int usuario, int tema);

    public void agregarVideo(int listaReproduccion,int video);
    
    public void modificar(int id, String nombre, Date fecha, int usuario, int tema);

    public void eliminar(int id);
    
    public void eliminarVideo(int listaReproduccion, int video);

    public HashMap<Integer, ListaReproduccion> listar();

    public ArrayList<Integer> listarVideos(int lista);

    public void comprar(int usuario, int video, int tema);
}

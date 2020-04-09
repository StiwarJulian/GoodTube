/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.video;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface IVideoDAO {

    public void insertar(String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor);

    public void modificar(int id, String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor);

    public void eliminar(int id);

    public HashMap<Integer, Video> listar();
}

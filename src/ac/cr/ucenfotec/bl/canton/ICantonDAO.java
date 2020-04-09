/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.canton;

import ac.cr.ucenfotec.bl.provincia.*;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface ICantonDAO {

    public void insertar(String nombre, int provincia);

    public void modificar(int id, String nombre, int provincia);

    public void eliminar(int id);

    public HashMap<Integer, Canton> listar();
}

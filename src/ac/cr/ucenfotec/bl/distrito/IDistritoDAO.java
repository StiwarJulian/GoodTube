/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.distrito;

import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface IDistritoDAO {

    public void insertar(String nombre, int canton);

    public void modificar(int id, String nombre, int canton);

    public void eliminar(int id);

    public HashMap<Integer, Distrito> listar();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.provincia;

import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface IProvinciaDAO {

    public void insertar(String nombre);

    public void modificar(int id, String nombre);

    public void eliminar(int id);

    public HashMap<Integer, Provincia> listar();
}

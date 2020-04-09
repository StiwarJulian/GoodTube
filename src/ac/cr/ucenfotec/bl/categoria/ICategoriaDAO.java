/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.categoria;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface ICategoriaDAO {
    public void insertar(String nombre, String descripcion);

    public void modificar(int id, String nombre, String descripcion);

    public void eliminar(int id);

    public HashMap<Integer,Categoria> listar();
}

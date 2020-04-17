/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.reproduccion;

import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface IReproduccionDAO {

    public void insertar(int tiempo, int usuario, int video);
    
    public void eliminar(int usuario);
    
    public HashMap<Integer, Reproduccion> listar();

}

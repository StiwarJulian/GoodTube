/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.opciones;

import ac.cr.ucenfotec.bl.distrito.*;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public interface IOpcionesDAO {

    public void insertar(String color, String vista, int usuario);

    public void eliminar(int usuario);

    public HashMap<Integer, Opciones> listar();
}

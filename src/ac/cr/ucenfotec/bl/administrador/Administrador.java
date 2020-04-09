/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.administrador;

import ac.cr.ucenfotec.bl.usuarios.Usuarios;

/**
 *
 * @author Miguel
 */
public class Administrador extends Usuarios{

    public Administrador(int id_usuario, Long identificacion, String nombres, String apellidos, int edad, String correo, String usuario, String clave, String avatar, int codigo_verificacion, int id_distrito, int id_rol) {
    }


    public Administrador() {
    }

    private Administrador(int id_usuario, Long identificacion, String nombres, String apellidos, int edad, String correo, String usuario, String clave, String avatar, String codigo_verificacion, int id_distrito, int id_rol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

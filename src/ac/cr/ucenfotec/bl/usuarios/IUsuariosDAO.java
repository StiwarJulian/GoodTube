/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.usuarios;

public interface IUsuariosDAO {
    
    public Usuarios comprobarUsuario(String usuario, String clave);

    public boolean actualizarCodigoVerificacion(int id_usuario, int codigo_verificacion);

    public void guardarUsuario(Usuarios usuario);

    public Usuarios comprobarExisteUsuario(String usuario);

    public boolean cambiarClave(int id_usuario, int clave);

    
}

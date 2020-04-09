/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.administrador;

/**
 *
 * @author Miguel
 */
public interface IAdministradorDAO {
    
    public void guardarUsuarioAdministrador(Administrador administrador);
    public boolean verificarExisteUsuarioAdministrador();
    public int ultimoIdUsuario();
    public boolean comprobarCorreoUnico(String correo);
    public boolean comprobarUsuarioUnico(String usuario);
}

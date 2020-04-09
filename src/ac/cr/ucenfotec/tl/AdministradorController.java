/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.tl;


import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.administrador.Administrador;
import ac.cr.ucenfotec.bl.administrador.IAdministradorDAO;

/**
 *
 * @author Miguel
 */
public class AdministradorController {
    
    public void guardarUsuarioAdministrador(Administrador administrador){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IAdministradorDAO admin = factory.getAdministradorDao();
        
        admin.guardarUsuarioAdministrador(administrador);
    }
    
    public static boolean verificarExisteUsuarioAdministrador(){
        
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IAdministradorDAO administrador = factory.getAdministradorDao();
        
        boolean administradorExiste = administrador.verificarExisteUsuarioAdministrador();

        return administradorExiste;
    }
    
    public int ultimoIdUsuario(){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IAdministradorDAO administrador = factory.getAdministradorDao();
        
        int ultimo_id_usuario = administrador.ultimoIdUsuario();

        return ultimo_id_usuario;
    }
    
    public boolean comprobarCorreoUnico(String correo){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IAdministradorDAO administrador = factory.getAdministradorDao();
        
        boolean validacion_correo = administrador.comprobarCorreoUnico(correo);

        return validacion_correo;
    }
    
    public boolean comprobarUsuarioUnico(String usuario){
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IAdministradorDAO administrador = factory.getAdministradorDao();
        
        boolean validacion_usuario = administrador.comprobarUsuarioUnico(usuario);

        return validacion_usuario;
    }

}

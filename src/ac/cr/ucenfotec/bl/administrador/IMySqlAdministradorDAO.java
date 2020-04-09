
package ac.cr.ucenfotec.bl.administrador;



import ac.cr.ucenfotec.AccesoBD;
import ac.cr.ucenfotec.bl.usuarios.IMySqlUsuariosDAO;
import java.sql.ResultSet;

import java.util.logging.Level;
import java.util.logging.Logger;


public class IMySqlAdministradorDAO implements IAdministradorDAO{
    
    String query;
    
    @Override
    public boolean verificarExisteUsuarioAdministrador() {
        
        try {
            query = "SELECT * FROM usuario WHERE id_rol=1 ORDER BY id_usuario ASC";
            
            ResultSet rs = AccesoBD.getConnection().ejecutarQuery(query);
            
            return rs.next();
        } catch (Exception ex) {
            Logger.getLogger(IMySqlUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public void guardarUsuarioAdministrador(Administrador administrador){
        try{
            query = "INSERT INTO usuario(id_usuario,identificacion,nombres,apellidos,edad,"
                    + "correo,usuario,clave,avatar,codigo_verificacion,id_distrito,id_rol)"
                    + " VALUES(null,"
                    + administrador.getIdentificacion() +   ",'"
                    + administrador.getNombres()        +   "','"
                    + administrador.getApellidos()      +   "',"
                    + administrador.getEdad()           +   ",'"
                    + administrador.getCorreo()         +   "','"
                    + administrador.getUsuario()        +   "','"
                    + administrador.getClave()          +   "','"
                    + administrador.getAvatar()         +   "',"
                    + null + ","
                    + null +   ","
                    + administrador.getId_rol()         +   ")";
            
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception ex) {
            Logger.getLogger(IMySqlAdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
    public int ultimoIdUsuario() {
        
        try {
            query = "SELECT id_usuario from usuario ORDER BY id_usuario DESC LIMIT 1";
            
            ResultSet rs = AccesoBD.getConnection().ejecutarQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (Exception ex) {
            //Logger.getLogger(IMySqlAdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public boolean comprobarCorreoUnico(String correo) {
        try {
            query = "SELECT * FROM usuario WHERE correo = '"+correo+"'";
            
            ResultSet rs = AccesoBD.getConnection().ejecutarQuery(query);
            return rs.next();
        } catch (Exception ex) {
            Logger.getLogger(IMySqlAdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public boolean comprobarUsuarioUnico(String usuario) {
        try {
            query = "SELECT * FROM usuario WHERE usuario.usuario = '"+usuario+"'";
            
            ResultSet rs = AccesoBD.getConnection().ejecutarQuery(query);
            return rs.next();
        } catch (Exception ex) {
            Logger.getLogger(IMySqlAdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
}

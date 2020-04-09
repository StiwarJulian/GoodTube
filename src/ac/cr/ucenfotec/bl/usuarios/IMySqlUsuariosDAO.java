
package ac.cr.ucenfotec.bl.usuarios;

import ac.cr.ucenfotec.AccesoBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.Clock.system;
import javax.swing.JOptionPane;


public class IMySqlUsuariosDAO implements IUsuariosDAO{
    
    String query;

    @Override
    public Usuarios comprobarUsuario(String usuario, String clave) {
        
        try {
            query = "SELECT * FROM usuario WHERE usuario= '"+usuario+"' AND clave='"+clave+"'";
            
            ResultSet rs = AccesoBD.getConnection().ejecutarQuery(query);
            
            if(rs.next()){
                Usuarios user = new Usuarios(
                        rs.getInt("id_usuario"),
                        rs.getLong("identificacion"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getInt("edad"),
                        rs.getString("correo"),
                        rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getString("avatar"),
                        rs.getInt("codigo_verificacion"),
                        rs.getInt("id_distrito"),
                        rs.getInt("id_rol")
                );
                
                return user;
            }else{
                return null;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(IMySqlUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean actualizarCodigoVerificacion(int id_usuario, int codigo_verificacion) {
        
        try {
            query = "UPDATE usuario SET codigo_verificacion="+codigo_verificacion+" WHERE id_usuario="+id_usuario;
            
            AccesoBD.getConnection().ejecutarActualizacion(query);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(IMySqlUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void guardarUsuario(Usuarios usuarios){
        try{
            query = "INSERT INTO usuario(id_usuario,identificacion,nombres,apellidos,edad,"
                    + "correo,usuario,clave,avatar,codigo_verificacion,id_distrito,id_rol)"
                    + " VALUES(null,"
                    + usuarios.getIdentificacion() +   ",'"
                    + usuarios.getNombres()        +   "','"
                    + usuarios.getApellidos()      +   "',"
                    + usuarios.getEdad()           +   ",'"
                    + usuarios.getCorreo()         +   "','"
                    + usuarios.getUsuario()        +   "','"
                    + usuarios.getClave()          +   "','"
                    + usuarios.getAvatar()         +   "',"
                    + null + ","
                    + usuarios.getId_distrito()    +   ","
                    + usuarios.getId_rol()         +   ")";
            
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception ex) {
            Logger.getLogger(IUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

    @Override
    public Usuarios comprobarExisteUsuario(String usuario) {
          
            try {
            query = "SELECT * FROM usuario WHERE usuario= '"+usuario+"'";
            
            ResultSet rs = AccesoBD.getConnection().ejecutarQuery(query);
            
            if(rs.next()){
                Usuarios user = new Usuarios(
                        rs.getInt("id_usuario"),
                        rs.getLong("identificacion"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getInt("edad"),
                        rs.getString("correo"),
                        rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getString("avatar"),
                        rs.getInt("codigo_verificacion"),
                        rs.getInt("id_distrito"),
                        rs.getInt("id_rol")
                );
                
                return user;
            }else{
                return null;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(IMySqlUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
                
        
    }
        


    @Override
    public boolean cambiarClave(int id_usuario, int clave) {
        
        try {
            query = "UPDATE usuario SET clave="+clave+" WHERE id_usuario="+id_usuario;
            
            AccesoBD.getConnection().ejecutarActualizacion(query);
            
            return true;
        } catch (Exception ex) {
            return false;
            //Logger.getLogger(IMySqlUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
    
    
}

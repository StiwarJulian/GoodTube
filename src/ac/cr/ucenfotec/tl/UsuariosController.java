package ac.cr.ucenfotec.tl;

import ac.cr.ucenfotec.bl.factory.DaoFactory;
import ac.cr.ucenfotec.bl.usuarios.IUsuariosDAO;
import ac.cr.ucenfotec.bl.usuarios.Usuarios;
import java.util.HashMap;

public class UsuariosController {

    public static void eliminar(int id) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IUsuariosDAO user = factory.getUsuariosDao();
        user.eliminar(id);
    }

    public Usuarios comprobarUsuario(String usuario, String clave) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IUsuariosDAO user = factory.getUsuariosDao();

        Usuarios comprobacion = user.comprobarUsuario(usuario, clave);

        return comprobacion;
    }

    public boolean actualizarCodigoVerificacion(int id_usuario, int codigo_verificacion) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IUsuariosDAO user = factory.getUsuariosDao();

        boolean actualizacion = user.actualizarCodigoVerificacion(id_usuario, codigo_verificacion);

        return actualizacion;
    }

    public void guardarUsuario(Usuarios usuario) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IUsuariosDAO user = factory.getUsuariosDao();
        user.guardarUsuario(usuario);
    }

    public static Usuarios comprobarExisteUsuario(String usuario) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IUsuariosDAO user = factory.getUsuariosDao();

        Usuarios comprobar = user.comprobarExisteUsuario(usuario);

        return comprobar;
    }

    public boolean cambiarClave(int id_usuario, int clave) {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IUsuariosDAO user = factory.getUsuariosDao();

        boolean cambiarClave = user.cambiarClave(id_usuario, clave);

        return cambiarClave;
    }

    public static HashMap<Integer, Usuarios> listar() {
        DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        IUsuariosDAO dao = factory.getUsuariosDao();
        return dao.listarUsuarios();
    }
}

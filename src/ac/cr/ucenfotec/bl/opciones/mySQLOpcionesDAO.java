/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.opciones;

import ac.cr.ucenfotec.bl.distrito.*;
import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLOpcionesDAO implements IOpcionesDAO {

    @Override
    public void insertar(String color, String vista, int usuario) {
        String query = "INSERT INTO `opciones`(`id_opciones`, `color`, `vista`, `id_usuario`)"
                + " VALUES (null,'" + color + "','" + vista + "'," + usuario + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `opciones` WHERE `id_usuario`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Opciones> listar() {
        HashMap<Integer, Opciones> opciones = new HashMap<>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `opciones`");
            while (rs.next()) {
                Opciones opcion = new Opciones(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getInt(4));
                opciones.put(opcion.getUsuario(), opcion);
            }
        } catch (Exception ex) {
        }
        return opciones;
    }

}

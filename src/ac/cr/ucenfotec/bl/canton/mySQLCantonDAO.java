/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.canton;

import ac.cr.ucenfotec.bl.provincia.*;
import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLCantonDAO implements ICantonDAO {

    @Override
    public void insertar(String nombre, int provincia) {
        String query = "INSERT INTO `canton`(`id_canton`, `nombre`, `id_provincia`)"
                + " VALUES (null,'" + nombre + "'," + provincia + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(int id, String nombre, int provincia) {
        String query = "UPDATE `canton` SET `nombre`=" + nombre + ","
                + "`id_provincia`=" + provincia + " WHERE `id_canton`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `canton` WHERE `id_canton`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Canton> listar() {
        HashMap<Integer, Canton> cantones = new HashMap<>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `canton`");
            while (rs.next()) {
                Canton canton = new Canton(rs.getInt(1), rs.getString(2), rs.getInt(3));
                cantones.put(canton.getId(), canton);
            }
        } catch (Exception ex) {
        }
        return cantones;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.distrito;

import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLDistritoDAO implements IDistritoDAO {

    @Override
    public void insertar(String nombre, int canton) {
        String query = "INSERT INTO `distrito`(`id_distrito`, `nombre`, `id_canton`)"
                + " VALUES (null,'" + nombre + "'," + canton + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(int id, String nombre, int canton) {
        String query = "UPDATE `canton` SET `nombre`=" + nombre + ","
                + "`id_canton`=" + canton + " WHERE `id_distrito`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `distrito` WHERE `id_distrito`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Distrito> listar() {
        HashMap<Integer, Distrito> distritos = new HashMap<>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `distrito`");
            while (rs.next()) {
                Distrito distrito = new Distrito(rs.getInt(1), rs.getString(2), rs.getInt(3));
                distritos.put(distrito.getId(), distrito);
            }
        } catch (Exception ex) {
        }
        return distritos;
    }

}

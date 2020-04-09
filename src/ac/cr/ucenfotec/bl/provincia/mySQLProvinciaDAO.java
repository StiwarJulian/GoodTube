/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.provincia;

import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLProvinciaDAO implements IProvinciaDAO {

    @Override
    public void insertar(String nombre) {
        String query = "INSERT INTO `provincia`(`id_provincia`, `nombre`) VALUES (null,'" + nombre + "')";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(int id, String nombre) {
        String query = "UPDATE `provincia` SET `nombre`=" + nombre
                + " WHERE `id_provincia` =" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `provincia` WHERE `id_provincia`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Provincia> listar() {
        HashMap<Integer, Provincia> provincias = new HashMap<>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM Video");
            while (rs.next()) {
                Provincia provincia = new Provincia(rs.getInt(1), rs.getString(2));
                provincias.put(provincia.getId(), provincia);
            }
        } catch (Exception ex) {
        }
        return provincias;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.tema;

import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLTemaDAO implements ITemaDAO {

    @Override
    public void insertar(String nombre, String descripcion) {
        String query = "INSERT INTO `tema`(`id_tema`, `nombre`, `descripcion`)"
                + " VALUES (null," + nombre + "," + descripcion + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(int id, String nombre, String descripcion) {
        String query = "UPDATE `tema` SET"
                + "`nombre`='" + nombre + "',`descripcion`='" + descripcion + "' WHERE  `id_tema`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `tema` WHERE `id_categoria`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Tema> listar() {
        HashMap<Integer, Tema> temas = new HashMap<Integer, Tema>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `tema`");
            while (rs.next()) {
                Tema tema = new Tema(rs.getInt(1), rs.getString(2), rs.getString(3));
                temas.put(tema.getId(), tema);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return temas;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.categoria;

import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLCategoriaDAO implements ICategoriaDAO {

    @Override
    public void insertar(String nombre, String descripcion) {
        String query = "INSERT INTO `categoria`(`id_categoria`, `nombre`, `descripcion`)"
                + " VALUES (null," + nombre + "," + descripcion + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(int id, String nombre, String descripcion) {
        String query = "UPDATE `categoria` SET"
                + "`nombre`='" + nombre + "',`descripcion`='" + descripcion + "' WHERE  `id_categoria`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `categoria` WHERE  `id_categoria`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Categoria> listar() {
        HashMap<Integer, Categoria> categorias = new HashMap<Integer, Categoria>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `categoria`");
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
                categorias.put(categoria.getId(), categoria);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return categorias;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.reproduccion;

import ac.cr.ucenfotec.bl.video.*;
import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLReproduccionDAO implements IReproduccionDAO {

    @Override
    public void insertar(int tiempo, int usuario, int video) {
        String query = "INSERT INTO `reproduccion_actual`"
                + "(`tiempo`, `id_usuario`, `id_video`) VALUES (" + tiempo + "," + usuario + "," + video + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int usuario) {
        String query = "DELETE FROM `reproduccion_actual` WHERE `id_usuario`=" + usuario;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Reproduccion> listar() {
        HashMap<Integer, Reproduccion> reproducciones = new HashMap<Integer, Reproduccion>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `reproduccion_actual`");
            while (rs.next()) {
                Reproduccion reproduccion = new Reproduccion(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3));
                reproducciones.put(Integer.parseInt(reproduccion.getVideo() + ""
                        + "" + reproduccion.getUsuario()), reproduccion);
            }
        } catch (Exception ex) {
        }
        return reproducciones;
    }
}

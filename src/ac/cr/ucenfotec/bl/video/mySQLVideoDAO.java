/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.video;

import ac.cr.ucenfotec.AccesoBD;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Pardo
 */
public class mySQLVideoDAO implements IVideoDAO {

    @Override
    public void insertar(String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        String query = "INSERT INTO `video`(`id_video`,`nombre`, `fecha`, `ruta`, `calificacion`, `id_categoria`, `id_usuario`, `valor`)"
                + " VALUES ('null','" + nombre + "'" + fecha + "','" + ruta + "'," + calificacion + "," + categoria + "," + usuario + "," + valor + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(int id, String nombre, Date fecha, String ruta, int calificacion, int categoria, int usuario, double valor) {
        String query = "UPDATE `video` SET `nombre`='" + nombre + ",`fecha`='" + fecha + "',`ruta`='" + ruta + "',"
                + "`calificacion`=" + calificacion + ",`id_categoria`=" + categoria
                + "`id_usuario`='" + usuario + ",`valor`='" + valor + " WHERE `id_video`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `video` WHERE `id_video`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, Video> listar() {
        HashMap<Integer, Video> videos = new HashMap<Integer, Video>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM Video");
            while (rs.next()) {
                Video video = new Video(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getDouble(8));
                videos.put(video.getId(), video);
            }
        } catch (Exception ex) {
        }
        return videos;
    }

}

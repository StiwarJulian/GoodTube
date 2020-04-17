/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.lista;

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
public class mySQLListaReproduccionDAO implements IListaReproduccionDAO {

    @Override
    public void insertar(String nombre, Date fecha, int usuario, int tema) {
        String query = "INSERT INTO `lista_reproduccion`(`id_reproduccion`, `nombre`, `fecha`, `id_usuario`, `id_tema`)"
                + " VALUES (null,'" + nombre + "','" + new java.sql.Date(fecha.getTime()) + "'," + usuario + "," + tema + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modificar(int id, String nombre, Date fecha, int usuario, int tema) {
        String query = "UPDATE `lista_reproduccion` SET `nombre`='" + nombre + "',"
                + "`fecha`='" + new java.sql.Date(fecha.getTime()) + "',`id_tema`=" + tema + " WHERE `id_reproduccion` =" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM `lista_reproduccion` WHERE `id_reproduccion`=" + id;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer, ListaReproduccion> listar() {
        HashMap<Integer, ListaReproduccion> listas = new HashMap<Integer, ListaReproduccion>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `lista_reproduccion`");
            while (rs.next()) {
                ListaReproduccion video = new ListaReproduccion(rs.getInt(1), rs.getString(2),
                        rs.getDate(3), rs.getInt(5), rs.getInt(4));
                listas.put(video.getId(), video);
            }
        } catch (Exception ex) {
        }
        for (ListaReproduccion lista : listas.values()) {
            lista.setVideos(listarVideos(lista.getId()));
        }
        return listas;
    }

    @Override
    public void agregarVideo(int listaReproduccion, int video) {
        String query = "INSERT INTO `video_reproduccion`(`id_video`, `id_reproduccion`)"
                + " VALUES (" + video + "," + listaReproduccion + ")";
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarVideo(int listaReproduccion, int video) {
        String query = "DELETE FROM `video_reproduccion` WHERE `id_reproduccion` =" + listaReproduccion + " AND `id_video`=" + video;
        try {
            AccesoBD.getConnection().ejecutarActualizacion(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Integer> listarVideos(int lista) {
        ArrayList<Integer> videos = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `video_reproduccion` WHERE `id_reproduccion` =" + lista);
            while (rs.next()) {
                videos.add(rs.getInt(1));
            }
        } catch (Exception ex) {
        }

        return videos;
    }

    @Override
    public void comprar(int usuario, int video, int tema) {
        HashMap<Integer, ListaReproduccion> listas = new HashMap<Integer, ListaReproduccion>();
        ResultSet rs = null;
        try {
            rs = AccesoBD.getConnection().ejecutarQuery("SELECT * FROM `lista_reproduccion`");
            while (rs.next()) {
                if (rs.getString(2).equals("compras") && rs.getInt(5) == usuario) {
                    ListaReproduccion lista = new ListaReproduccion(rs.getInt(1), rs.getString(2),
                            rs.getDate(3), rs.getInt(4), rs.getInt(5));
                    listas.put(lista.getId(), lista);
                    agregarVideo(rs.getInt(1),video);
                }

            }
        } catch (Exception ex) {
        }
        if(listas.isEmpty()){
            insertar("compras",new java.sql.Date(System.currentTimeMillis()), usuario, tema);
            comprar(usuario, video,tema);
        }        
    }
}

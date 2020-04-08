/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.factory;

import ac.cr.ucenfotec.bl.canton.ICantonDAO;
import ac.cr.ucenfotec.bl.categoria.ICategoriaDAO;
import ac.cr.ucenfotec.bl.distrito.IDistritoDAO;
import ac.cr.ucenfotec.bl.provincia.IProvinciaDAO;
import ac.cr.ucenfotec.bl.tema.ITemaDAO;
import ac.cr.ucenfotec.bl.video.IVideoDAO;

/**
 *
 * @author Miguel
 */
public abstract class DaoFactory {

    public static final int MYSQL = 1;
    public static final int SQLSERVER = 2;

    public static DaoFactory getDaoFactory(int factory) {
        switch (factory) {
            case MYSQL:
                return new MySqlDaoFactory();

            default:
                return null;
        }
    }

    public abstract IVideoDAO getVideosDao();

    public abstract ICategoriaDAO getCategoriaDAO();

    public abstract IProvinciaDAO getProvinciaDAO();

    public abstract ICantonDAO getCantonDAO();
    
    public abstract IDistritoDAO getDistritoDAO();

    public abstract ITemaDAO getTemaDAO();

}

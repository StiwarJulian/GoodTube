/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.factory;

import ac.cr.ucenfotec.bl.canton.ICantonDAO;
import ac.cr.ucenfotec.bl.canton.mySQLCantonDAO;
import ac.cr.ucenfotec.bl.categoria.ICategoriaDAO;
import ac.cr.ucenfotec.bl.categoria.mySQLCategoriaDAO;
import ac.cr.ucenfotec.bl.distrito.IDistritoDAO;
import ac.cr.ucenfotec.bl.distrito.mySQLDistritoDAO;
import ac.cr.ucenfotec.bl.provincia.IProvinciaDAO;
import ac.cr.ucenfotec.bl.provincia.mySQLProvinciaDAO;
import ac.cr.ucenfotec.bl.tema.ITemaDAO;
import ac.cr.ucenfotec.bl.tema.mySQLTemaDAO;
import ac.cr.ucenfotec.bl.video.IVideoDAO;
import ac.cr.ucenfotec.bl.video.mySQLVideoDAO;

/**
 *
 * @author Miguel
 */
public class MySqlDaoFactory extends DaoFactory{

    @Override
    public IVideoDAO getVideosDao() {
        return new mySQLVideoDAO();
    }
    
    @Override
    public ICategoriaDAO getCategoriaDAO() {
        return new mySQLCategoriaDAO();
    }

    @Override
    public IProvinciaDAO getProvinciaDAO() {
        return new mySQLProvinciaDAO();
    }

    @Override
    public ICantonDAO getCantonDAO() {
        return new mySQLCantonDAO();
    }

    @Override
    public IDistritoDAO getDistritoDAO() {
        return new mySQLDistritoDAO();
    }

    @Override
    public ITemaDAO getTemaDAO() {
        return new mySQLTemaDAO();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec.bl.factory;

/**
 *
 * @author Miguel
 */
public class DaoFactory {
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
}

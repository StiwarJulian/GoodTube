/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec;

/**
 *
 * @author Miguel
 */
public class AccesoBD {

    public static Conector con = null;

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://localhost/goodtube";

    public static Conector getConnection() throws Exception {
        if (con == null) {
            con = new Conector(DRIVER, URL, USER, PASSWORD);
        }
        return con;
    }

    public static Conector getNewConnection() throws Exception {
        con = new Conector(DRIVER, URL, USER, PASSWORD);
        return con;
    }

}

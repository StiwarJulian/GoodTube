/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.cr.ucenfotec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Miguel
 */
 
public class Conector{

    private Connection conn = null;
    private Statement state = null;
    
    public Conector(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        conn = DriverManager.getConnection(url,user,password);
        state = conn.createStatement();
    }
    
    
    public void ejecutarActualizacion(String query) throws Exception {
        state.execute(query);
    }
    
    public ResultSet ejecutarQuery(String query) throws Exception {
        ResultSet rs = null;
        rs = state.executeQuery(query);
        return rs;
    }
    
}

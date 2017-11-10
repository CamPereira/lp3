package com.br.lp2.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camila
 */
public class SingletonConnection {
    
    private static SingletonConnection instance;
    private Connection connection;
    
    private SingletonConnection(){
        ResourceBundle prop = ResourceBundle.getBundle("lp2db.dbproperties");
        try {
            Class.forName(prop.getString("DRIVER"));
            connection = DriverManager.getConnection(
                            prop.getString("PROTOCOL")+"://"
                            +prop.getString("HOST")
                            +"/"+prop.getString("DATABASE")
                            +";useUnicode=yes&characterEncoding=UTF-8",
                            prop.getString("USERNAME"),
                            prop.getString("PASSWORD")
            );
            //jdbc:derby//localhost:1527/lp2_db;useUnicode=yes&characterEncoding=UTF-8
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SingletonConnection getInstance(){
        if(instance == null){
            instance = new SingletonConnection();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}

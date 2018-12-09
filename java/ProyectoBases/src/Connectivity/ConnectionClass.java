/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Cesar
 */
public class ConnectionClass {
    public Connection connection;
    public Connection getConnection(){
        String dbName = "agencia";
        String userName = "root";
        String password = "1234";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fallo la conexion cojudo");
        }
        return connection;
    }
}

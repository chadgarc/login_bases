/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases.DBSClasses;

import Connectivity.ConnectionClass;
import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.SQLException ;
import java.sql.Statement ;
import java.sql.ResultSet ;

import java.util.List ;
import java.util.ArrayList ;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar
 */
public class EmpleadoDataAccessor {
    private Connection connection ;

    public EmpleadoDataAccessor(){
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Empleado> getEmpleadoList() throws SQLException {
        try (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from empleado");
        ){
            List<Empleado> empleadoList = new ArrayList<>();
            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cargo = rs.getString("cargo");
                String clave = rs.getString("clave");
                Empleado person = new Empleado(usuario, nombre, apellido, cargo, clave);
                empleadoList.add(person);
            }
            return empleadoList ;
        } 
    }

    public void addEmpleado(String usuario, String nombre, String apellido, String cargo, String clave){
        Statement stmnt=null;
        String values = "'"+usuario+"', '"+nombre+"', '"+apellido+"', '"+cargo+"', '"+clave+"'";
        try {
            stmnt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Fallo la conexion");
            Logger.getLogger(EmpleadoDataAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stmnt.executeUpdate("insert into empleado values("+values+")");
        } catch (SQLException ex) {
            System.out.println("Fallo el statement");
            Logger.getLogger(EmpleadoDataAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// other methods, eg. addEmpleado(...) etc
}

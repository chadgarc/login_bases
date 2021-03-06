/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases;

import Connectivity.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectobases.DBSClasses.EmpleadoDataAccessor;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class CrearClienteController implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textId;
    @FXML
    private TextField textFechaNacimiento;
    @FXML
    private Button buttonCrearCliente;
    @FXML
    private Button regresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crearCliente(ActionEvent event) throws IOException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement stmnt=null;
        String values = "'"+textId.getText().toString()+"', '"+textNombre.getText().toString()+"', '"+textApellido.getText().toString()+"', '"+textFechaNacimiento.getText().toString()+"'";
        try {
            stmnt = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Fallo la conexion");
            Logger.getLogger(EmpleadoDataAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stmnt.executeUpdate("insert into cliente values("+values+")");
        } catch (SQLException ex) {
            System.out.println("Fallo el statement");
            Logger.getLogger(EmpleadoDataAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaClientes.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaClientes.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
    
}

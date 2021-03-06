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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class ActualizarEmpleadoController implements Initializable {

    @FXML
    private Label LabelActualizacion;
    @FXML
    private Button BotonActualizar;
    @FXML
    private Button BotonRegresar;
    @FXML
    private ComboBox<String> comboboxEmpleado;
    @FXML
    private ComboBox<String> comboboxdato;
    @FXML
    private TextArea textoactualizacion;
    
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> nombreEmpleados = new ArrayList<String>();
        ArrayList<String> nombreDatos = new ArrayList<String>();
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("SELECT usuario,nombre,apellido FROM empleado");
            while (rs.next()){
                String nombrePaquete = rs.getString("usuario") + " " + rs.getString("nombre")+ " " + rs.getString("apellido");
                nombreEmpleados.add(nombrePaquete);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombreDatos.add("nombre");
        nombreDatos.add("apellido");
        nombreDatos.add("cargo");
        nombreDatos.add("clave");
        comboboxEmpleado.getItems().addAll(nombreEmpleados);
        comboboxdato.getItems().addAll(nombreDatos);
    }    

    @FXML
    private void ActualizarEmpleado(ActionEvent event) throws SQLException, IOException {
        String[] valorPaquete = comboboxEmpleado.getValue().split(" ");
        String IdUsuario = valorPaquete[0];
        String dato = comboboxdato.getValue();
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        String query = "";
        String datoActualizado = "";
        PreparedStatement stmnt;
        if (dato.equals("nombre")){
            datoActualizado = textoactualizacion.getText();
            query = "update empleado set nombre = ? where usuario = ?";
        }
        if (dato.equals("apellido")){
            datoActualizado = textoactualizacion.getText();
            query = "update empleado set apellido = ? where usuario = ?";           
        }
        if (dato.equals("cargo")){
            datoActualizado = textoactualizacion.getText();
            query = "update empleado set cargo = ? where usuario = ?";
        }
        if (dato.equals("clave")){
            datoActualizado = textoactualizacion.getText();
            query = "update empleado set clave = ? where usuario = ?";           
        }
        stmnt = connection.prepareStatement(query);
        stmnt.setString(2, IdUsuario);
        stmnt.setString(1,datoActualizado);
        stmnt.executeUpdate();
        
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaEmpleados.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void AccionRegresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaEmpleados.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
    
}

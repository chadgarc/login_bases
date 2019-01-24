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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class RegistrarNuevoController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private ComboBox<String> comboBoxPaquete;
    @FXML
    private Label labelPaquete;
    @FXML
    private Label labelCliente;
    @FXML
    private ComboBox<String> comboBoxCliente;
    @FXML
    private Button botonRegistrar;
    @FXML
    private Button botonRegresar;
    
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> nombrePaquetes = new ArrayList<String>();
        ArrayList<String> nombreClientes = new ArrayList<String>();
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt,stmnt2;
        ResultSet rs,rs2;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("SELECT idPaquete,nombre FROM paquetedeviaje");
            while (rs.next()){
                String nombrePaquete = rs.getString("idPaquete") + ".- " + rs.getString("nombre");
                nombrePaquetes.add(nombrePaquete);
            }
            stmnt2 = connection.createStatement();
            rs2 = stmnt2.executeQuery("SELECT idCliente,nombre,apellido FROM cliente ORDER BY nombre,apellido");
            while (rs2.next()){
                String nombreCliente = rs2.getString("nombre") + " " + rs2.getString("apellido")+ " " + rs2.getString("idCliente");
                nombreClientes.add(nombreCliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboBoxPaquete.getItems().addAll(nombrePaquetes);
        comboBoxCliente.getItems().addAll(nombreClientes);
    }    

    @FXML
    private void Registar(ActionEvent event) throws SQLException, IOException {
        String[] valorPaquete = comboBoxPaquete.getValue().split(".-");
        String[] valorCliente = comboBoxCliente.getValue().split(" ");
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        String IdCliente = valorCliente[2];
        String IdPaquete = valorPaquete[0];
        Statement stmnt=null;
        String values = "'"+IdCliente+"', "+IdPaquete+"";
        try {
            stmnt = connection.createStatement();
            stmnt.executeUpdate("insert into registro values("+values+")");
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("Registro.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
        
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("Registro.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
    
}

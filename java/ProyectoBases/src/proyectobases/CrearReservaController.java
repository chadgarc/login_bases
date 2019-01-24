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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class CrearReservaController implements Initializable {

    @FXML
    private ComboBox<String> cbcliente;
    @FXML
    private ComboBox<String> cbpaquete;
    @FXML
    private ComboBox<String> cbvuelo;
    @FXML
    private TextField tclase;
    @FXML
    private TextField tnum;
    @FXML
    private Button bcrear;
    @FXML
    private Button bregresar;
    
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> nombrePaquetes = new ArrayList<String>();
        ArrayList<String> nombreClientes = new ArrayList<String>();
        ArrayList<String> nombreVuelos = new ArrayList<String>();
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt,stmnt2,stmnt3;
        ResultSet rs,rs2,rs3;
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
            stmnt2 = connection.createStatement();
            rs3 = stmnt2.executeQuery("SELECT idVuelo,aerolinea,ciudadOrigen,ciudadDestino FROM vuelo");
            while (rs3.next()){
                String nombreVuelo = rs3.getString("idVuelo") + " " + rs3.getString("aerolinea")+ " " + rs3.getString("ciudadOrigen")+ " " + rs3.getString("ciudadDestino");
                nombreVuelos.add(nombreVuelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbpaquete.getItems().addAll(nombrePaquetes);
        cbcliente.getItems().addAll(nombreClientes);
        cbvuelo.getItems().addAll(nombreVuelos);
    }    

    @FXML
    private void crear(ActionEvent event) throws IOException {
        
        String[] valorPaquete = cbpaquete.getValue().split(".-");
        String[] valorCliente = cbcliente.getValue().split(" ");
        String[] valorVuelo = cbvuelo.getValue().split(" ");
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        String IdCliente = valorCliente[2];
        String IdPaquete = valorPaquete[0];
        String IdVuelo = valorVuelo[0];
        String numeroV = tnum.getText();
        String clase = tclase.getText();
        Statement stmnt=null;
        String values = "0, '"+IdCliente+"','"+numeroV+"','"+clase+"','"+IdPaquete+"','"+IdVuelo+"'";
        try {
            stmnt = connection.createStatement();
            stmnt.executeUpdate("insert into reservavuelo values("+values+")");
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaReservas.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();  
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaReservas.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }
    
}

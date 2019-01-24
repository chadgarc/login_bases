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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class ActualizarPaqueteController implements Initializable {

    @FXML
    private ComboBox<String> comboboxpaquete;
    @FXML
    private ComboBox<String> comboboxdato;
    @FXML
    private TextArea textoactualizacion;
    @FXML
    private Button botonActualizar;
    @FXML
    private Button botonRegresar;
    
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> nombrePaquetes = new ArrayList<String>();
        ArrayList<String> nombreDatos = new ArrayList<String>();
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("SELECT idPaquete,nombre FROM paquetedeviaje");
            while (rs.next()){
                String nombrePaquete = rs.getString("idPaquete") + ".- " + rs.getString("nombre");
                nombrePaquetes.add(nombrePaquete);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nombreDatos.add("nombre");
        nombreDatos.add("descripcion");
        comboboxpaquete.getItems().addAll(nombrePaquetes);
        comboboxdato.getItems().addAll(nombreDatos);
    }    

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaPaquetes.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void actualizarDatos(ActionEvent event) throws SQLException, IOException {
        String[] valorPaquete = comboboxpaquete.getValue().split(".-");
        String IdPaquete = valorPaquete[0];
        String dato = comboboxdato.getValue();
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        String query = "";
        String datoActualizado = "";
        PreparedStatement stmnt;
        ResultSet rs;
        if (dato.equals("nombre")){
            datoActualizado = textoactualizacion.getText();
            query = "update paquetedeviaje set nombre = ? where idPaquete = ?";
            
            
        }
        if (dato.equals("descripcion")){
            datoActualizado = textoactualizacion.getText();
            query = "update paquetedeviaje set descripcion = ? where idPaquete = ?";            
        }
        stmnt = connection.prepareStatement(query);
        stmnt.setInt(2, Integer.parseInt(IdPaquete));
        stmnt.setString(1,datoActualizado);
        stmnt.executeUpdate();
        
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaPaquetes.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
    
}

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TablaRegistrosController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxPaquete;
    @FXML
    private TableView<?> TBRegistroPorPaquete;
    @FXML
    private TableColumn<?, ?> ColumnaID;
    @FXML
    private TableColumn<?, ?> ColumnaNombre;
    @FXML
    private TableColumn<?, ?> ColumnaApellido;
    @FXML
    private Button regresar;
    @FXML
    private Button BorrarRegistro;
    
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
        comboBoxPaquete.getItems().addAll(nombrePaquetes);
    }    

    @FXML
    private void popularTabla(ActionEvent event) {
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("Registro.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void borrarRegistro(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases;

import Connectivity.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import proyectobases.DBSClasses.TBEmpleado;
import proyectobases.DBSClasses.TBRegistroPaquete;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TablaRegistrosController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxPaquete;
    @FXML
    private TableView<TBRegistroPaquete> TBRegistroPorPaquete;
    @FXML
    private TableColumn<TBRegistroPaquete, String> ColumnaID;
    @FXML
    private TableColumn<TBRegistroPaquete, String> ColumnaNombre;
    @FXML
    private TableColumn<TBRegistroPaquete, String> ColumnaApellido;
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
    private void popularTabla(ActionEvent event) throws SQLException {
        ObservableList<TBRegistroPaquete> oblist = FXCollections.observableArrayList();
        String[] valorPaquete = comboBoxPaquete.getValue().split(".-");
        String IdPaquete = valorPaquete[0];
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        String query = "select c.idCliente as idCliente, c.Nombre as Nombre, c.Apellido as Apellido\n" +
"from registro r join cliente c on r.idCliente = c.idCliente\n" +
"where r.idPaquete = "+IdPaquete;
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(query);
            
            while (rs.next()){
                oblist.add(new TBRegistroPaquete(rs.getString("idCliente"),rs.getString("nombre"),rs.getString("apellido")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ColumnaID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        ColumnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        TBRegistroPorPaquete.getItems().removeAll();
        TBRegistroPorPaquete.setItems(oblist);
        
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
        ObservableList<TBRegistroPaquete> oblist = FXCollections.observableArrayList();
        TBRegistroPaquete registroSeleccionado = TBRegistroPorPaquete.getSelectionModel().getSelectedItem();
        String ClienteID = registroSeleccionado.getIdCliente();
        String[] valorPaquete = comboBoxPaquete.getValue().split(".-");
        String IdPaquete = valorPaquete[0];
        String query = "delete from registro where idcliente = ? and idpaquete = ?";
        PreparedStatement pstmt = null;
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            pstmt = connection.prepareStatement(query); 
            pstmt.setString(1, ClienteID);
            pstmt.setString(2, IdPaquete);
            pstmt.executeUpdate();
            String query2 = "select c.idCliente as idCliente, c.Nombre as Nombre, c.Apellido as Apellido\n" +
"from registro r join cliente c on r.idCliente = c.idCliente\n" +
"where r.idPaquete = "+IdPaquete;
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(query2);
            
            while (rs.next()){
                oblist.add(new TBRegistroPaquete(rs.getString("idCliente"),rs.getString("nombre"),rs.getString("apellido")));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ColumnaID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        ColumnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        TBRegistroPorPaquete.getItems().removeAll();
        TBRegistroPorPaquete.setItems(oblist);
    }
    
}

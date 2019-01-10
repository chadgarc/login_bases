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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import proyectobases.DBSClasses.TBCliente;
import proyectobases.DBSClasses.TBPaquete;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TablaPaquetesController implements Initializable {

    @FXML
    private Label LabelPaquetes;
    @FXML
    private TableView<TBPaquete> TablaPaquetes;
    @FXML
    private TableColumn<TBPaquete, String> ColumnaIdPaquete;
    @FXML
    private TableColumn<TBPaquete, String> ColumnaNombre;
    @FXML
    private TableColumn<TBPaquete, String> ColumnaDescripcion;
    @FXML
    private Button BotonCrearPaquete;
    @FXML
    private Button BotonEliminarPaquete;
    @FXML
    private Button BotonActualizarPaquete;
    @FXML
    private Button BotonRegresar;
    
    private Connection connection;
    
    private ObservableList<TBPaquete> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("select * from paquetedeviaje");
            
            while (rs.next()){
                oblist.add(new TBPaquete(Integer.valueOf(rs.getString("idPaquete")),rs.getString("nombre"),rs.getString("descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ColumnaIdPaquete.setCellValueFactory(new PropertyValueFactory<>("idPaquete"));
        ColumnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        
        TablaPaquetes.setItems(oblist);
    }    

    @FXML
    private void CrearPaquete(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("CrearPaquete.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void EliminarPaquete(ActionEvent event) {
        TBPaquete clienteSeleccionado = TablaPaquetes.getSelectionModel().getSelectedItem();
        int usuarioSeleccionado = clienteSeleccionado.getIdPaquete();
        String query = "delete from paquetedeviaje where idPaquete = ?";
        PreparedStatement pstmt = null;
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            pstmt = connection.prepareStatement(query); 
            pstmt.setInt(1, usuarioSeleccionado);
            pstmt.executeUpdate();
            rs = stmnt.executeQuery("select * from paquetedeviaje");
            oblist.removeAll(oblist);
            TablaPaquetes.setItems(oblist);
            while (rs.next()){
                oblist.add(new TBPaquete(Integer.valueOf(rs.getString("idPaquete")),rs.getString("nombre"),rs.getString("descripcion")));
            }
            TablaPaquetes.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(TablaPaquetesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ActualizarPaquete(ActionEvent event) {
    }

    @FXML
    private void Regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
    
}

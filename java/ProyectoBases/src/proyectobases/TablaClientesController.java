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

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TablaClientesController implements Initializable {

    @FXML
    private Label LabelCliente;
    @FXML
    private TableView<TBCliente> TablaClientes;
    @FXML
    private TableColumn<TBCliente, String> ColumnaId;
    @FXML
    private TableColumn<TBCliente, String> ColumnaNombre;
    @FXML
    private TableColumn<TBCliente, String> ColumnaApellido;
    @FXML
    private TableColumn<TBCliente, String> ColumnaFecha;
    @FXML
    private Button BotonCrearCliente;
    @FXML
    private Button BotonEliminarCliente;
    @FXML
    private Button BotonActualizarCliente;
    @FXML
    private Button BotonRegresar;
    @FXML
    private Button BotonAgregarPaquete;
    
    private Connection connection;
    
    private ObservableList<TBCliente> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("select * from cliente");
            
            while (rs.next()){
                oblist.add(new TBCliente(rs.getString("idCliente"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("fechaNacimiento")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ColumnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColumnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        TablaClientes.setItems(oblist);
    }    

    @FXML
    private void CrearCliente(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("CrearCliente.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void EliminarCliente(ActionEvent event) {
        TBCliente clienteSeleccionado = TablaClientes.getSelectionModel().getSelectedItem();
        String usuarioSeleccionado = clienteSeleccionado.getId();
        String query = "delete from cliente where idCliente = ?";
        PreparedStatement pstmt = null;
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            pstmt = connection.prepareStatement(query); 
            pstmt.setString(1, usuarioSeleccionado);
            pstmt.executeUpdate();
            rs = stmnt.executeQuery("select * from cliente");
            oblist.removeAll(oblist);
            TablaClientes.setItems(oblist);
            while (rs.next()){
                oblist.add(new TBCliente(rs.getString("idCliente"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("fechaNacimiento")));
            }
            TablaClientes.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ActualizarCliente(ActionEvent event) {
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }
    
}

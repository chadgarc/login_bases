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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TablaEmpleadosController implements Initializable {

    @FXML
    private Button nuevoEmpleado;
    @FXML
    private Button deleteEmpleado;
    @FXML
    private Button updateEmpleado;
    @FXML
    private Button backButton;
    @FXML
    private TableView<TBEmpleado> TableEmpleados;
    @FXML
    private TableColumn<TBEmpleado,String> TableNombre;
    @FXML
    private TableColumn<TBEmpleado,String> TableApellido;
    @FXML
    private TableColumn<TBEmpleado,String> TableCargo;
    @FXML
    private TableColumn<TBEmpleado,String> TableClave;
    @FXML
    private TableColumn<TBEmpleado,String> TableUsuario;

    private Connection connection;
    
    ObservableList<TBEmpleado> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("select * from empleado");
            
            while (rs.next()){
                oblist.add(new TBEmpleado(rs.getString("nombre"),rs.getString("apellido"),rs.getString("cargo"),rs.getString("clave"),rs.getString("usuario")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        TableNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        TableCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        TableClave.setCellValueFactory(new PropertyValueFactory<>("clave"));
        TableUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        
        TableEmpleados.setItems(oblist);
    }    

    @FXML
    private void CrearNuevoEmpleado(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("CrearEmpleado.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }

    @FXML
    private void EliminarEmpleado(ActionEvent event) {
        TBEmpleado empleadoSeleccionado = TableEmpleados.getSelectionModel().getSelectedItem();
        String usuarioSeleccionado = empleadoSeleccionado.getUsuario();
        String query = "delete from empleado where usuario = ?";
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
            rs = stmnt.executeQuery("select * from empleado");
            oblist.removeAll(oblist);
            TableEmpleados.setItems(oblist);
            while (rs.next()){
                oblist.add(new TBEmpleado(rs.getString("nombre"),rs.getString("apellido"),rs.getString("cargo"),rs.getString("clave"),rs.getString("usuario")));
            }
            TableEmpleados.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ActualizarEmpleado(ActionEvent event) {
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

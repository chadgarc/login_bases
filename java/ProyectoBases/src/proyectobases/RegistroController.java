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
import proyectobases.DBSClasses.TBConteoRegistro;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class RegistroController implements Initializable {

    @FXML
    private Button botonRegistroNuevo;
    @FXML
    private TableView<TBConteoRegistro> TablaRegistros;
    @FXML
    private TableColumn<TBConteoRegistro,String> ColumnaPaqueteId;
    @FXML
    private TableColumn<TBConteoRegistro,String> ColumnaCantReg;
    @FXML
    private Button botonVerRegistro;
    @FXML
    private Label labelRegistro;
    @FXML
    private Button botonRegresar;
    
    private Connection connection;
    
    private ObservableList<TBConteoRegistro> oblist = FXCollections.observableArrayList();


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
            rs = stmnt.executeQuery("select * from conteoRegistros");
            
            while (rs.next()){
                oblist.add(new TBConteoRegistro(rs.getString("nombre"),Integer.parseInt(rs.getString("conteo"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ColumnaPaqueteId.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColumnaCantReg.setCellValueFactory(new PropertyValueFactory<>("conteo"));
        
        TablaRegistros.setItems(oblist);
    }    

    @FXML
    private void RegistrarNuevo(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("RegistrarNuevo.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void VerRegistros(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaRegistros.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
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

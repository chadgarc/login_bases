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
import proyectobases.DBSClasses.TBCliente;
import proyectobases.DBSClasses.TBReserva;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class TablaReservasController implements Initializable {

    @FXML
    private TableColumn<TBReserva, String> colIdReserva;
    @FXML
    private TableColumn<TBReserva, String> ColIdCliente;
    @FXML
    private TableColumn<TBReserva, String> ColNumVuelos;
    @FXML
    private TableColumn<TBReserva, String> ColClase;
    @FXML
    private TableColumn<TBReserva, String> ColIdPaquete;
    @FXML
    private TableColumn<TBReserva, String> ColIdVuelo;
    @FXML
    private Button botonNuevo;
    @FXML
    private Button botonActualizar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonRegresar;
    @FXML
    private TableView<TBReserva> TablaReserva;
    
    private Connection connection;
    
    private ObservableList<TBReserva> oblist = FXCollections.observableArrayList();

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
            rs = stmnt.executeQuery("select * from reservaVuelo");
            
            while (rs.next()){
                oblist.add(new TBReserva(rs.getString("idReserva"),rs.getString("idCliente"),rs.getString("numeroVuelos"),rs.getString("clase"),rs.getString("idPaquete"),rs.getString("idVuelo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("idReserva"));
        ColIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        ColNumVuelos.setCellValueFactory(new PropertyValueFactory<>("numeroVuelos"));
        ColClase.setCellValueFactory(new PropertyValueFactory<>("clase"));
        ColIdPaquete.setCellValueFactory(new PropertyValueFactory<>("idPaquete"));
        ColIdVuelo.setCellValueFactory(new PropertyValueFactory<>("idVuelo"));
        
        TablaReserva.setItems(oblist);
    }    

    @FXML
    private void nuevaReserva(ActionEvent event) {
    }

    @FXML
    private void actualizar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("ActualizarReserva.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }

    @FXML
    private void eliminar(ActionEvent event) {
        TBReserva clienteSeleccionado = TablaReserva.getSelectionModel().getSelectedItem();
        String usuarioSeleccionado = clienteSeleccionado.getIdReserva();
        String query = "delete from reservavuelo where idReserva = ?";
        PreparedStatement pstmt = null;
        ConnectionClass connectionClass = new ConnectionClass();
        connection = connectionClass.getConnection();
        Statement stmnt;
        ResultSet rs;
        try {
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery("select * from reservaVuelo");
            oblist.removeAll(oblist);
            TablaReserva.setItems(oblist);
            while (rs.next()){
                oblist.add(new TBReserva(rs.getString("idReserva"),rs.getString("idCliente"),rs.getString("numeroVuelos"),rs.getString("clase"),rs.getString("idPaquete"),rs.getString("idVuelo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        TablaReserva.setItems(oblist);
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaClientes.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }
    
}

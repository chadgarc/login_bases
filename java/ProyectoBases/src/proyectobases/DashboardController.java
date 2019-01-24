/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class DashboardController implements Initializable {

    @FXML
    private Label labelDashboard;
    @FXML
    private Button buttonEmpleado;
    @FXML
    private Button buttonPaquetes;
    @FXML
    private Button buttonClientes;
    @FXML
    private Button BotonRegistro;
    @FXML
    private ImageView imagenLogo;
/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void lanzarCreacion(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaEmpleados.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }

    @FXML
    private void lanzarEmpleado(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaEmpleados.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void lanzarPaquete(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaPaquetes.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    private void lanzarCliente(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("TablaClientes.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }

    @FXML
    private void lanzarRegistro(ActionEvent event) throws IOException {
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("Registro.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectobases.DBSClasses.EmpleadoDataAccessor;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class CrearEmpleadoController implements Initializable {

    @FXML
    private TextField textUsuario;
    @FXML
    private Button buttonCrearUsuario;
    @FXML
    private PasswordField textContraseña;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textCargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crearUsuario(ActionEvent event) throws IOException, SQLException {
        EmpleadoDataAccessor empleadoData = new EmpleadoDataAccessor();
        empleadoData.addEmpleado(textUsuario.getText().toString(), textNombre.getText().toString(), textApellido.getText().toString(), textCargo.getText().toString(), textContraseña.getText().toString());
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboardParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show(); 
    }
    
}

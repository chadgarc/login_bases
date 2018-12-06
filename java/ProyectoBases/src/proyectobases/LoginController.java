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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Cesar
 */
public class LoginController implements Initializable {
    
    private Label label;
    @FXML
    private TextField textUsuario;
    @FXML
    private PasswordField textClave;
    @FXML
    private Button botonLogin;
    @FXML
    private Label labelEstado;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (textUsuario.getText().toString().equals("admin") && textClave.getText().toString().equals("admin")){
            Parent dashboardParent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene dashboardScene = new Scene(dashboardParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();
        }
        else{
            labelEstado.setText("Usuario o clave equivocada");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

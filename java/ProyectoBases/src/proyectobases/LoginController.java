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
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectobases.DBSClasses.Empleado;
import proyectobases.DBSClasses.EmpleadoDataAccessor;

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
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        List<Empleado> listaEmpleados;
        EmpleadoDataAccessor empleadoData = new EmpleadoDataAccessor();
        listaEmpleados = empleadoData.getEmpleadoList();
        
        boolean correcto=false;
        int i=0;
        Parent dashboardParent = null;
        Scene dashboardScene = null;
        while ((i<listaEmpleados.size()) && (correcto==false)){
            String usuarioIngresado=textUsuario.getText().toString();
            String claveIngresada=textClave.getText().toString();
            String clave = listaEmpleados.get(i).getClave();
            String usuario = listaEmpleados.get(i).getUsuario();
            
            if (textUsuario.getText().toString().equals(listaEmpleados.get(i).getUsuario()) && textClave.getText().toString().equals(listaEmpleados.get(i).getClave())){
                dashboardParent = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                dashboardScene = new Scene(dashboardParent);
                correcto=true;
            }
            i++;
        }
        
        if (correcto==true){
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show(); 
        }else{
            labelEstado.setText("Usuario o clave equivocada o conexion fallida");
        }       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

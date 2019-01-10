/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobases;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class ActualizarEmpleadoController implements Initializable {

    @FXML
    private Label LabelActualizacion;
    @FXML
    private TextField TextNombre;
    @FXML
    private TextField TextApellido;
    @FXML
    private TextField TextCargo;
    @FXML
    private TextField TextClave;
    @FXML
    private Label LabelNombre;
    @FXML
    private Label LabelApellido;
    @FXML
    private Label LabelCargo;
    @FXML
    private Label LabelClave;
    @FXML
    private Button BotonActualizar;
    @FXML
    private Button BotonRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActualizarEmpleado(ActionEvent event) {
    }

    @FXML
    private void AccionRegresar(ActionEvent event) {
    }
    
}

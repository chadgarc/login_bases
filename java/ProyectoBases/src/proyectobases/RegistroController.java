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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class RegistroController implements Initializable {

    @FXML
    private Button botonRegistroNuevo;
    @FXML
    private TableView<?> TablaRegistros;
    @FXML
    private TableColumn<?, ?> ColumnaPaqueteId;
    @FXML
    private TableColumn<?, ?> ColumnaCantReg;
    @FXML
    private Button botonVerRegistro;
    @FXML
    private Label labelRegistro;
    @FXML
    private Button botonRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegistrarNuevo(ActionEvent event) {
    }

    @FXML
    private void VerRegistros(ActionEvent event) {
    }

    @FXML
    private void Regresar(ActionEvent event) {
    }
    
}

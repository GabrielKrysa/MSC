/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabri
 */
public class FXML_SobreMSCController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private JFXButton buttonVoltar;
    
    @FXML
    public void voltar(ActionEvent evt){       
        Stage stage = (Stage) buttonVoltar.getScene().getWindow();
        stage.close();    
    }
    
}

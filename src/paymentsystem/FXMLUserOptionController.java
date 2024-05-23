/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package paymentsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tony NDOSS
 */
public class FXMLUserOptionController implements Initializable {

    @FXML TextField newUserFirstNameTxt;
    @FXML TextField newUserLastNameTxt;
    @FXML TextField newUserUserNameTxt;
    @FXML TextField newUserCompanyNameTxt;
    @FXML TextField newUserPwdTxt;
    @FXML TextField newUserCnfrmPwdTxt;
    @FXML TextField userPwdTxt;
    @FXML Button userUpdateBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

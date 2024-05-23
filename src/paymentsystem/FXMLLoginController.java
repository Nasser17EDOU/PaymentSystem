/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package paymentsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony NDOSS
 */
public class FXMLLoginController implements Initializable {

    @FXML
    TextField loginUserNameTxt;
    @FXML
    TextField loginUserPwd;
    @FXML
    Button loginBtn;

    @FXML
    TextField signUpFirstName;
    @FXML
    TextField signUpLastName;
    @FXML
    TextField signUpUsername;
    @FXML
    TextField signUpCompanyName;
    @FXML
    TextField signUpPwd;
    @FXML
    TextField signUpCnfrmPwd;
    @FXML
    Button createAcntBtn;

    ConnectionToDb connectionToDb = new ConnectionToDb();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        loginMethod();
        signUpMethod();
    }

    void loginMethod() {
        loginBtn.setOnMouseClicked(m -> {
            if (connectionToDb.loginToDb(loginUserNameTxt.getText(), loginUserPwd.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPaymentSystem.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                Scene scene = new Scene(loader.getRoot());
                stage.setScene(scene);
                stage.show();
            } else {
                errorAlertMethod("Login", "Wong username or password", "try again");
            }
        });
    }

    void signUpMethod() {
        createAcntBtn.setOnMouseClicked(m -> {
            if ("".equals(signUpUsername.getText().trim()) || "".equals(signUpFirstName.getText().trim())
                    || "".equals(signUpLastName.getText().trim()) || "".equals(signUpCompanyName.getText().trim())
                    || "".equals(signUpPwd.getText().trim()) || "".equals(signUpCnfrmPwd.getText().trim())) {
                errorAlertMethod("Sign Up", "Some Fields are empty", "Fill the form correctly");
            } else if (!signUpCnfrmPwd.getText().trim().equals(signUpPwd.getText().trim())) {
                errorAlertMethod("Sign Up", "Password does not match", "Try again");
            } else {
                if (connectionToDb.createNewAccount(signUpUsername.getText().trim(), signUpFirstName.getText().trim(), signUpLastName.getText().trim(), signUpCompanyName.getText().trim(), signUpPwd.getText().trim())) {
                    errorAlertMethod("Sign Up", "Account Created Sucessfully", "");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPaymentSystem.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = (Stage) loginBtn.getScene().getWindow();
                    Scene scene = new Scene(loader.getRoot());
                    stage.setScene(scene);
                    stage.show();
                } else {
                    errorAlertMethod("Sign Up", "This Username exist Already", "Change username and try again");
                }
            }
        });
    }

    //Error Alert method
    void errorAlertMethod(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}

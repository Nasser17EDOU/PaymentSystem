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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony NDOSS
 */
public class FXMLPaymentSystemController implements Initializable {

    @FXML
    Label companyNameLabel;
    @FXML
    Label userFullNameLabel;

    @FXML
    Button employeesBtn;
    @FXML
    Button paySalaryBtn;
//    @FXML
//    Button userBtn;
    @FXML
    Button logoutBtn;

    @FXML
    Pane contentPane;

    Button optionSelectedBtn = new Button();

    void setOptionSelectedBtn(Button optionSelectedBtn) {
        this.optionSelectedBtn = optionSelectedBtn;
    }

    Button getOptionSelectedBtn() {
        return optionSelectedBtn;
    }

    static int dateReminderCount = 0;

    public void setDateReminderCount(int dateReminderCount) {
        FXMLPaymentSystemController.dateReminderCount = dateReminderCount;
    }

    public int getDateReminderCount() {
        return dateReminderCount;
    }

    User user = new User();
    FXMLEmployeesOptionController fxmlemployeesOptionController = new FXMLEmployeesOptionController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        companyNameLabel.setText(user.getCompanyname());
        userFullNameLabel.setText(user.getFirstname() + " " + user.getLastname());
        setPageContent(employeesBtn, "FXMLEmployeesOption.fxml");
        optionButtonHandler(employeesBtn, "FXMLEmployeesOption.fxml");
        optionButtonHandler(paySalaryBtn, "FXMLPaySalaryOption.fxml");
//        optionButtonHandler(userBtn, "FXMLUserOption.fxml");
        logoutBtnHandler();

    }

    //Set a method to add an fxml page to the main content paine according to option selected
    void setPageContent(Button optionSelectedBtn, String fxmlFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.optionSelectedBtn.setDisable(false);
//        this.optionSelectedBtn.setStyle("-fx-background-color: silver; -fx-text-fill: black;");
        contentPane.getChildren().clear();
        contentPane.getChildren().add(loader.getRoot());
        setOptionSelectedBtn(optionSelectedBtn);
        this.optionSelectedBtn.setDisable(true);
//        this.optionSelectedBtn.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
    }

    //set handler for options buttons
    void optionButtonHandler(Button optionBtn, String fxmlFile) {
        optionBtn.setOnAction(e -> {
            if (fxmlemployeesOptionController.getIsPaySalaryButtonPressed()) {
            } else {
                fxmlemployeesOptionController.selectedEmployeeReseter();
            }
            setPageContent(optionBtn, fxmlFile);
        });
    }

    //logout btn handler
    void logoutBtnHandler() {
        logoutBtn.setOnMouseClicked(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
            stage.show();
        });
    }
}

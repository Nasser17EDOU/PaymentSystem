/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package paymentsystem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Tony NDOSS
 */
public class FXMLPaySalaryOptionController implements Initializable {

//    @FXML
//    Spinner daySpinner;
//    @FXML
//    Spinner monthSpinner;
//    @FXML
//    Spinner yearSpinner;
    @FXML
    TextField employeeIdForPaymentText;
    @FXML
    Button clearEmployeeIdBtn;

    @FXML
    TableView<Payment> paymentTableTbVw;

    @FXML
    TableColumn dateTbColumn;
    @FXML
    TableColumn netSalaryTbColumn;
    @FXML
    TableColumn grossSalaryTbColumn;
    @FXML
    TableColumn taxSalaryTbColumn;
    @FXML
    TableColumn amountAddedSalaryTbColumn;
    @FXML
    TableColumn amountAddedSalaryReasonTbColumn;
    @FXML
    TableColumn amountDeductedSalaryTbColumn;
    @FXML
    TableColumn amountDeductedSalaryReasonTbColumn;

    @FXML
    ImageView employeePictureImgView;

    @FXML
    Pane paySalaryPane;
    @FXML
    TextField employeeTimeWorkedTxt;
    @FXML
    Label employeeNameLabel;
    @FXML
    Label employeeCategoryLabel;
    @FXML
    Label employeeExpectWorkingTimeLabel;
    @FXML
    Label employeePayRateLabel;
    @FXML
    Label employeeExtraTimePayRateLabel;
    @FXML
    Label employeeTaxRateLabel;
    @FXML
    TextField employeeSurplusTxt;
    @FXML
    TextArea employeeSurplusReasonTxt;
    @FXML
    TextField employeeDeductionTxt;
    @FXML
    TextArea employeeDeductionReasonTxt;
    @FXML
    Label employeeGrossSalaryLabel;
    @FXML
    Label employeeTaxAmountLabel;
    @FXML
    Label employeeNetSalary;
    @FXML
    Button paySalaryBtn;

    SelectedEmployee selectedEmployee = new SelectedEmployee();
    ConnectionToDb connectionToDb = new ConnectionToDb();
    FXMLPaymentSystemController fxmlpaymentSystemController = new FXMLPaymentSystemController();

    Image image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        System.out.println("\n\nDate " + new Date(System.currentTimeMillis()));
        clearEmployeeIdBtnHandler();
        employeeDetailsFieldsReseter();
        employeeIdForPaymentTextHandler();
        isPaySalaryButtonPressedHandler();
        employeeDetailsTextFieldsHandler(employeeTimeWorkedTxt);
        employeeDetailsTextFieldsHandler(employeeSurplusTxt);
        employeeDetailsTextFieldsHandler(employeeDeductionTxt);
        employeeDetailsFieldsChangeTxtHandler(employeeTimeWorkedTxt);
        employeeDetailsFieldsChangeTxtHandler(employeeSurplusTxt);
        employeeDetailsFieldsChangeTxtHandler(employeeDeductionTxt);
        tableColumnFactoryVal();
        paymentTableTbVw.setPlaceholder(new Label("No Payment"));
        paySalaryBtnHandler();
    }

    //employeeIdForPaymentText hamdler
    void employeeIdForPaymentTextHandler() {
        employeeIdForPaymentText.textProperty().addListener((Observer, OldValue, newValue) -> {
            //System.out.println("\nnChange ");
            if ("".equals(newValue)) {
                employeeDetailsFieldsReseter();
                setDefaultImage();
                paymentTableTbVw.getItems().clear();
            } else {
                selectedEmployee.setEmployeeId(newValue);
                if (!connectionToDb.isEmployeeIdExist()) {
                    employeeDetailsFieldsReseter();
                    setDefaultImage();
                    paymentTableTbVw.getItems().clear();
                } else {
                    paymentTableTbVw.getItems().clear();
                    paymentTableTbVw.getItems().addAll(connectionToDb.getPaymentList());
                    int n = 0;
                    for (int i = 0; i < connectionToDb.getListOfEmployeeDeteails().size(); i++) {
                        if (selectedEmployee.getEmployeeId().equals(connectionToDb.getListOfEmployeeDeteails().get(i).employeeId)) {
                            n = i;
                        } else {
                        }
                    }

                    setEmployeeDetailsForLabels(n);
                    enableDisabledFields();
                    if (connectionToDb.getEmployeePicture() == null) {
                        setDefaultImage();
                    } else {
                        try {
                            InputStream inputStream = new BufferedInputStream(connectionToDb.getEmployeePicture());
                            image = SwingFXUtils.toFXImage(ImageIO.read(inputStream), null);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLEmployeesOptionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }

            employeePictureImgView.setImage(image);
        });
    }

    //set handler on clearEmployeeIdBtn
    void clearEmployeeIdBtnHandler() {
        clearEmployeeIdBtn.setOnAction(e -> {
            employeeIdForPaymentText.setText("");
        });
    }

    //if there is already an employee selected handler
    void isPaySalaryButtonPressedHandler() {
        if (selectedEmployee.getEmployeeId() == null) {
        } else {
            employeeIdForPaymentText.setText(selectedEmployee.getEmployeeId());
        }
    }

    //set employee details for label
    void setEmployeeDetailsForLabels(int n) {
        employeeNameLabel.setText(connectionToDb.getListOfEmployeeDeteails().get(n).employeeFullName);
        employeeCategoryLabel.setText(connectionToDb.getListOfEmployeeDeteails().get(n).employeeCategory);
        employeeExpectWorkingTimeLabel.setText(String.valueOf(connectionToDb.getListOfEmployeeDeteails().get(n).employeeExpectedTimeToWork));
        employeePayRateLabel.setText(String.valueOf(connectionToDb.getListOfEmployeeDeteails().get(n).employeePayRatePerHour));
        employeeExtraTimePayRateLabel.setText(String.valueOf(connectionToDb.getListOfEmployeeDeteails().get(n).employeeExtraTimePayRate));
        employeeTaxRateLabel.setText(String.valueOf(connectionToDb.getListOfEmployeeDeteails().get(n).employeeTaxRate));
    }

    //set employeeTimeWorkedTxt handler
    void employeeDetailsFieldsChangeTxtHandler(TextField textField) {
        textField.textProperty().addListener((Observer, oldVal, newVal) -> {
            int valOfEmployeeTimeWorkedTxt;
            double valofEmployeeSurplusTxt;
            double valofEmployeeDeductionTxt;

            double extraTimePay = 0, GrossSalary, Tax, NetPay;

            if ("".equals(employeeTimeWorkedTxt.getText())) {
                valOfEmployeeTimeWorkedTxt = 0;
            } else {
                valOfEmployeeTimeWorkedTxt = Integer.valueOf(employeeTimeWorkedTxt.getText());
            }

            if ("".equals(employeeSurplusTxt.getText())) {
                valofEmployeeSurplusTxt = 0;
            } else {
                valofEmployeeSurplusTxt = Double.valueOf(employeeSurplusTxt.getText());
            }

            if ("".equals(employeeDeductionTxt.getText())) {
                valofEmployeeDeductionTxt = 0;
            } else {
                valofEmployeeDeductionTxt = Double.valueOf(employeeDeductionTxt.getText());
            }

            if (valOfEmployeeTimeWorkedTxt > Integer.valueOf(employeeExpectWorkingTimeLabel.getText())) {
                extraTimePay = (valOfEmployeeTimeWorkedTxt - Integer.valueOf(employeeExpectWorkingTimeLabel.getText())) * ((Double.valueOf(employeeExtraTimePayRateLabel.getText()) / 100) * Double.valueOf(employeePayRateLabel.getText()));
                valOfEmployeeTimeWorkedTxt = Integer.valueOf(employeeExpectWorkingTimeLabel.getText());
            } else {
            }

            GrossSalary = (valOfEmployeeTimeWorkedTxt * Double.valueOf(employeePayRateLabel.getText())) + extraTimePay;
            Tax = GrossSalary * (Double.valueOf(employeeTaxRateLabel.getText()) / 100);
            NetPay = GrossSalary - Tax + valofEmployeeSurplusTxt - valofEmployeeDeductionTxt;

            employeeGrossSalaryLabel.setText(String.valueOf(GrossSalary));
            employeeTaxAmountLabel.setText(String.valueOf(Tax));
            employeeNetSalary.setText(String.valueOf(NetPay));

        });
    }

    void employeeDetailsTextFieldsHandler(TextField textField) {
        textField.setOnKeyTyped(k -> {
            try {
                Double.parseDouble(textField.getText() + k.getCharacter());
            } catch (NumberFormatException e) {
                k.consume();
            }
        });
    }

    //employeeDetailsTextFields reseter
    void employeeDetailsFieldsReseter() {
        employeeTimeWorkedTxt.setDisable(true);
        employeeTimeWorkedTxt.setText("");

        employeeNameLabel.setText("-");
        employeeCategoryLabel.setText("-");
        employeeExpectWorkingTimeLabel.setText("0");
        employeePayRateLabel.setText("0");
        employeeExtraTimePayRateLabel.setText("0");
        employeeTaxRateLabel.setText("0");

        employeeSurplusTxt.setDisable(true);
        employeeSurplusTxt.setText("");
        employeeSurplusReasonTxt.setDisable(true);
        employeeSurplusReasonTxt.setText("");
        employeeDeductionTxt.setDisable(true);
        employeeDeductionTxt.setText("");
        employeeDeductionReasonTxt.setDisable(true);
        employeeDeductionReasonTxt.setText("");

        employeeGrossSalaryLabel.setText("0");
        employeeTaxAmountLabel.setText("0");
        employeeNetSalary.setText("0");
        paySalaryBtn.setDisable(true);
    }

    //enable textfields and button
    void enableDisabledFields() {
        employeeTimeWorkedTxt.setDisable(false);
        employeeSurplusTxt.setDisable(false);
        employeeSurplusReasonTxt.setDisable(false);
        employeeDeductionTxt.setDisable(false);
        employeeDeductionReasonTxt.setDisable(false);
        paySalaryBtn.setDisable(false);
    }

    //set default image picture
    void setDefaultImage() {
        File file = new File("src/paymentsystem/Images/male-user-icon.jpg");
        image = new Image(file.toURI().toString());
    }

    //set table column cell value factory
    void tableColumnFactoryVal() {
        dateTbColumn.setCellValueFactory(new PropertyValueFactory("paymentDate"));
        netSalaryTbColumn.setCellValueFactory(new PropertyValueFactory("paymentNetSalary"));
        grossSalaryTbColumn.setCellValueFactory(new PropertyValueFactory("paymentGrossSalary"));
        taxSalaryTbColumn.setCellValueFactory(new PropertyValueFactory("paymentTaxSalary"));
        amountAddedSalaryTbColumn.setCellValueFactory(new PropertyValueFactory("paymentAmountAddedSalary"));
        amountAddedSalaryReasonTbColumn.setCellValueFactory(new PropertyValueFactory("paymentAmountAddedSalaryReason"));
        amountDeductedSalaryTbColumn.setCellValueFactory(new PropertyValueFactory("paymentAmountDeductedSalary"));
        amountDeductedSalaryReasonTbColumn.setCellValueFactory(new PropertyValueFactory("paymentAmountDeductedSalaryReason"));
    }

    //set handler on paySalaryBtn
    void paySalaryBtnHandler() {
        paySalaryBtn.setOnMouseClicked(e -> {
            if ("".equals(employeeTimeWorkedTxt.getText())) {
                employeeTimeWorkedTxt.setText("0");
            } else {
            }

            if ("".equals(employeeSurplusTxt.getText())) {
                employeeSurplusTxt.setText("0.00");
            } else {
            }

            if ("".equals(employeeDeductionTxt.getText())) {
                employeeDeductionTxt.setText("0.00");
            } else {
            }
            if (fxmlpaymentSystemController.getDateReminderCount() % 10 == 0) {
                configureDateReminder();
            } else {
            }

            if (!confirmationDialog(employeeNetSalary.getText())) {
            } else {

                connectionToDb.updatePayment(Double.valueOf(employeeNetSalary.getText()), Double.valueOf(employeeGrossSalaryLabel.getText()), Double.valueOf(employeeTaxAmountLabel.getText()), Double.valueOf(employeeSurplusTxt.getText()), employeeSurplusReasonTxt.getText(), Double.valueOf(employeeDeductionTxt.getText()), employeeDeductionReasonTxt.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Payment Made Successfully!!");
                alert.setTitle("Payment");
                alert.show();
                paymentTableTbVw.getItems().clear();
                paymentTableTbVw.getItems().addAll(connectionToDb.getPaymentList());
                employeeTimeWorkedTxt.setText("");
                employeeSurplusTxt.setText("");
                employeeSurplusReasonTxt.setText("");
                employeeDeductionTxt.setText("");
                employeeDeductionReasonTxt.setText("");
            }

            fxmlpaymentSystemController.setDateReminderCount(fxmlpaymentSystemController.getDateReminderCount() + 1);
        });
    }

    //Set confirmation dialog
    boolean confirmationDialog(String netPay) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Pay Net Salary Of $" + netPay
                + "\nOn " + (new Date(System.currentTimeMillis())).toLocalDate().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy", Locale.getDefault()))
        );
        alert.setContentText("Do you want to proceed?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    //configure date reminder
    void configureDateReminder() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Make To Correctly Set Your System Date!!");
        alert.setTitle("Set Date");
        alert.showAndWait();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package paymentsystem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Tony NDOSS
 */
public class FXMLEmployeesOptionController implements Initializable {

    @FXML
    TextField employeeIdTxt;
    @FXML
    TextField employeeFullNameTxt;
    @FXML
    TextField employeeCategoryTxt;
    @FXML
    TextField employeePayRateTxt;
    @FXML
    TextField employeeExtraTimePayRateTxt;
    @FXML
    TextField employeeExpectedTimeWorkedTxt;
    @FXML
    TextField employeeTaxRateTxt;
    @FXML
    Button employeeAddPictureBtn;
    @FXML
    Button removePictureBtn;
    @FXML
    Button addNewEmployeeBtn;

    @FXML
    TableView<Employee> listOfEmployeeTableTbVw;

    @FXML
    TableColumn employeeIdTbColumn;
    @FXML
    TableColumn employeeFullNameTbColumn;
    @FXML
    TableColumn employeeCategoryTbColumn;
    @FXML
    TableColumn employeePayRateTbColumn;
    @FXML
    TableColumn employeeExtraTimePayRateTbColumn;
    @FXML
    TableColumn employeeExpectedTimeWorkedTbColumn;
    @FXML
    TableColumn employeeTaxRateTbColumn;

    @FXML
    ImageView employeePictureImgView;

//    @FXML
//    Button updateEmployeeRecordsBtn;
    @FXML
    Button payEmployeeSalaryBtn;
    @FXML
    Button removeEmployeeFromSystemBtn;

    Image image = null;
    InputStream inputStream = null;
    File file;
    File uploadFile = null;
    FileChooser fileChooser = new FileChooser();

    ConnectionToDb connectionToDb = new ConnectionToDb();
    SelectedEmployee selectedEmployee = new SelectedEmployee();

    static boolean isPaySalaryButtonPressed = false;

    public boolean getIsPaySalaryButtonPressed() {
        return isPaySalaryButtonPressed;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setColumnCellValueFactory();
        listOfEmployeeTableTbVw.setPlaceholder(new Label("No Employee"));
        addNewEmployeeTextFieldsHandler(employeePayRateTxt, "double");
        addNewEmployeeTextFieldsHandler(employeeExtraTimePayRateTxt, "double");
        addNewEmployeeTextFieldsHandler(employeeExpectedTimeWorkedTxt, "int");
        addNewEmployeeTextFieldsHandler(employeeTaxRateTxt, "double");
        setTableList();
        listOfEmployeeTableTbVwHandler();
        removeEmployeeFromSystemBtnHandler();
        employeeAddPictureBtnHandler();
        addNewEmployeeBtnHandler();
        removePictureBtnHandler();
        payEmployeeSalaryBtnHandler();
    }

    //set cell value factory
    void setColumnCellValueFactory() {
        employeeIdTbColumn.setCellValueFactory(new PropertyValueFactory("employeeId"));
        employeeFullNameTbColumn.setCellValueFactory(new PropertyValueFactory("employeeFullName"));
        employeeCategoryTbColumn.setCellValueFactory(new PropertyValueFactory("employeeCategory"));
        employeePayRateTbColumn.setCellValueFactory(new PropertyValueFactory("employeePayRatePerHour"));
        employeeExtraTimePayRateTbColumn.setCellValueFactory(new PropertyValueFactory("employeeExtraTimePayRate"));
        employeeExpectedTimeWorkedTbColumn.setCellValueFactory(new PropertyValueFactory("employeeExpectedTimeToWork"));
        employeeTaxRateTbColumn.setCellValueFactory(new PropertyValueFactory("employeeTaxRate"));
    }

    //set listOfEmployeeTableTbVw list
    void setTableList() {
        listOfEmployeeTableTbVw.getItems().clear();
        listOfEmployeeTableTbVw.getItems().addAll(connectionToDb.getListOfEmployeeDeteails());
    }

    //set handler on listOfEmployeeTableTbVw
    void listOfEmployeeTableTbVwHandler() {
        listOfEmployeeTableTbVw.getSelectionModel().selectedItemProperty().addListener(ch -> {
            selectedEmployeeReseter();
            if (listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem() == null) {
            } else {
                selectedEmployee.setEmployeeId(listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem().getEmployeeId());
                selectedEmployee.setEmployeeFullName(listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem().getEmployeeFullName());
                selectedEmployee.setEmployeeCategory(listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem().getEmployeeCategory());
                selectedEmployee.setEmployeePayRatePerHour(listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem().getEmployeePayRatePerHour());
                selectedEmployee.setEmployeeExtraTimePayRate(listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem().getEmployeeExtraTimePayRate());
                selectedEmployee.setEmployeeExpectedTimeToWork(listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem().getEmployeeExpectedTimeToWork());
                selectedEmployee.setEmployeeTaxRate(listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem().getEmployeeTaxRate());
            }

            file = new File("src/paymentsystem/Images/male-user-icon.jpg");
            if (connectionToDb.getEmployeePicture() == null) {
                image = new Image(file.toURI().toString());
            } else {
                try {
                    inputStream = new BufferedInputStream(connectionToDb.getEmployeePicture());
                    image = SwingFXUtils.toFXImage(ImageIO.read(inputStream), null);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLEmployeesOptionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            employeePictureImgView.setImage(image);
        });
    }

    //remove employee from system handler
    void removeEmployeeFromSystemBtnHandler() {
        removeEmployeeFromSystemBtn.setOnMouseClicked(e -> {
            if (selectedEmployee == null) {
            } else {
                if (!confirmationDialog("You are going to remove '" + selectedEmployee.getEmployeeId() + " " + selectedEmployee.getEmployeeFullName() + " from the system!!")) {
                } else {
                    connectionToDb.removeEmployee();
                    //employeePictureImgView.setImage(new Image(new File("src/paymentsystem/Images/male-user-icon.jpg").toURI().toString()));

                    setTableList();
                }
            }
        });
    }

    //Set confirmation dialog
    boolean confirmationDialog(String headerText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(headerText);
        alert.setContentText("Do you want to proceed?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    //Add a new employee button handler
    void addNewEmployeeBtnHandler() {
        addNewEmployeeBtn.setOnMouseClicked(e -> {
            if ("".equals(employeeIdTxt.getText().trim()) || "".equals(employeeFullNameTxt.getText().trim()) || "".equals(employeeCategoryTxt.getText().trim())
                    || "".equals(employeePayRateTxt.getText().trim()) || "".equals(employeeExtraTimePayRateTxt.getText().trim())
                    || "".equals(employeeExpectedTimeWorkedTxt.getText().trim()) || "".equals(employeeTaxRateTxt.getText().trim())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add New Employee");
                alert.setHeaderText("Some Fields are empty");
                alert.setContentText("Fill the form correctly");
                alert.show();
            } else {
                if (!confirmationDialog("You are going to Add a new Employee to system!")) {
                } else {
                    selectedEmployee.setEmployeeId(employeeIdTxt.getText().trim());
                    selectedEmployee.setEmployeeFullName(employeeFullNameTxt.getText().trim());
                    selectedEmployee.setEmployeeCategory(employeeCategoryTxt.getText().trim());
                    selectedEmployee.setEmployeePayRatePerHour(Double.parseDouble(employeePayRateTxt.getText()));
                    selectedEmployee.setEmployeeExtraTimePayRate(Double.parseDouble(employeeExtraTimePayRateTxt.getText()));
                    selectedEmployee.setEmployeeExpectedTimeToWork(Integer.parseInt(employeeExpectedTimeWorkedTxt.getText()));
                    selectedEmployee.setEmployeeTaxRate(Double.parseDouble(employeeTaxRateTxt.getText()));
                    selectedEmployee.setEmployeePicture(uploadFile);
                    if (connectionToDb.isEmployeeIdExist()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Add New Employee");
                        alert.setHeaderText("Employee Id exists already");
                        alert.setContentText("Kindly use another");
                        alert.show();
                    } else {
                        connectionToDb.addNewEmployee();
//                        selectedEmployeeReseter();
                        setTableList();
                        addNewEmployeeFieldsReseter();
                    }

                }
            }
        });
    }

    //add employeeAddPictureBtn handler
    void employeeAddPictureBtnHandler() {
        employeeAddPictureBtn.setOnMouseClicked(e -> {
            List<String> ls = new ArrayList<>();
            ls.add("*.png");
            ls.add("*.PNG");
            ls.add("*.jpg");
            ls.add("*.JPG");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", ls));
            uploadFile = fileChooser.showOpenDialog(addNewEmployeeBtn.getScene().getWindow());
            if (uploadFile == null) {
            } else {
                employeeAddPictureBtn.setText(uploadFile.getAbsolutePath());
            }
        });
    }

    //add removePictureBtn handler
    void removePictureBtnHandler() {
        removePictureBtn.setOnMouseClicked(e -> {
            uploadFile = null;
            employeeAddPictureBtn.setText("+Add A Picture");
        });
    }

    //reset selectedEmployee
    void selectedEmployeeReseter() {
        selectedEmployee.setEmployeeId(null);
        selectedEmployee.setEmployeeFullName(null);
        selectedEmployee.setEmployeeCategory(null);
        selectedEmployee.setEmployeePayRatePerHour(0);
        selectedEmployee.setEmployeeExtraTimePayRate(0);
        selectedEmployee.setEmployeeExpectedTimeToWork(0);
        selectedEmployee.setEmployeeTaxRate(0);
        selectedEmployee.setEmployeePicture(null);
    }

    //reset add new employee entrie fields
    public void addNewEmployeeFieldsReseter() {
        employeeIdTxt.setText("");
        employeeFullNameTxt.setText("");
        employeeCategoryTxt.setText("");
        employeePayRateTxt.setText("");
        employeeExtraTimePayRateTxt.setText("");
        employeeExpectedTimeWorkedTxt.setText("");
        employeeTaxRateTxt.setText("");
        uploadFile = null;
        employeeAddPictureBtn.setText("+Add A Picture");

    }

    //prevent user to enter wrong fugure in numeric entry fields
    void addNewEmployeeTextFieldsHandler(TextField textField, String string) {
        textField.setOnKeyTyped(k -> {
            try {
                if ("int".equals(string)) {
                    Integer.parseInt(textField.getText() + k.getCharacter());
                } else {
                    Double.parseDouble(textField.getText() + k.getCharacter());
                }
            } catch (NumberFormatException e) {
                k.consume();
            }
        });
    }

    //pay salary button handler
    void payEmployeeSalaryBtnHandler() {
        payEmployeeSalaryBtn.setOnMouseClicked(e -> {
            if (listOfEmployeeTableTbVw.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pay Salary");
                alert.setHeaderText("No Employee Selected");
                alert.setContentText("Select an employee");
                alert.show();
            } else {
                Button b = (Button) payEmployeeSalaryBtn.getScene().getRoot().lookup("#paySalaryBtn");
                isPaySalaryButtonPressed = true;
                b.fire();
                isPaySalaryButtonPressed = false;
//                System.out.println("\n\n Affffffteeeer");
            }
        });
    }

//    void setPageContent(Button optionSelectedBtn, String fxmlFile) {
//        Pane mainContent = (Pane) payEmployeeSalaryBtn.getScene().getRoot().lookup("#contentPane");
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//        try {
//            loader.load();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        optionSelectedBtn.setDisable(false);
////        this.optionSelectedBtn.setStyle("-fx-background-color: silver; -fx-text-fill: black;");
//        mainContent.getChildren().clear();
//        mainContent.getChildren().add(loader.getRoot());
//        setOptionSelectedBtn(optionSelectedBtn);
//        this.optionSelectedBtn.setDisable(true);
////        this.optionSelectedBtn.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
//    }
}

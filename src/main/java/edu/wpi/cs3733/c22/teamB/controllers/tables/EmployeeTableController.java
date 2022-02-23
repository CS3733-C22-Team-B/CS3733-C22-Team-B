package edu.wpi.cs3733.c22.teamB.controllers.tables;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.controllers.AbsPage;
import edu.wpi.cs3733.c22.teamB.controllers.AnchorHomeController;
import edu.wpi.cs3733.c22.teamB.entity.*;

import java.io.IOException;
import java.util.List;

import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class EmployeeTableController extends AbsPage {

    @FXML private GridPane gridPane;
    @FXML private JFXButton confirmButton;
    @FXML private TextField employeeIDField;
    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField positionField;
    @FXML private TextField accessLevel;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private JFXButton addButton;
    @FXML private JFXButton modifyButton;
    @FXML private JFXButton deleteButton;
    @FXML private TableView<Employee> table;
    @FXML private JFXButton loadButton;

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.setPageName("Employee Table");
    }


    private enum Function {ADD, MODIFY, DELETE, NOTHING, IDLOOKUP};
    Function func = Function.NOTHING;

    private boolean initTable = false;

    DatabaseWrapper db = DatabaseWrapper.getInstance();

    public EmployeeTableController() {}

    @FXML
    private void initialize() throws NullPointerException {
        modifyButton.setDisable(true);
        deleteButton.setDisable(true);

        gridPane.setVisible(false);
        gridPane.setDisable(true);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                modifyButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        });

        loadTable();
//        TODO fix initResize bug
//        initResize();
        resize();
        namePage();
        Bapp.getPrimaryStage().heightProperty().addListener((observable)-> {
            table.setPrefWidth(Bapp.getPrimaryStage().getWidth()-50 );
            table.setPrefHeight(Bapp.getPrimaryStage().getHeight()- 50);
        });

    }

    @FXML
    private void loadTable() throws NullPointerException {
        if (!initTable) {
            initTable = true;

            TableColumn<Employee, String> col1 = new TableColumn<>("employeeID"); // column names
            TableColumn<Employee, String> col2 = new TableColumn<>("lastName");
            TableColumn<Employee, String> col3 = new TableColumn<>("firstName");
            TableColumn<Employee, String> col4 = new TableColumn<>("position");
            TableColumn<Employee, String> col5 = new TableColumn<>("accessLevel"); // column names
            TableColumn<Employee, String> col6 = new TableColumn<>("username");
            TableColumn<Employee, String> col7 = new TableColumn<>("password");
            TableColumn<Employee, String> col8 = new TableColumn<>("email");
            TableColumn<Employee, String> col9 = new TableColumn<>("phoneNumber");

            col1.setCellValueFactory(new PropertyValueFactory<>("employeeID")); // MedicalEquipmentSR fields
            col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            col3.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            col4.setCellValueFactory(new PropertyValueFactory<>("position"));
            col5.setCellValueFactory(new PropertyValueFactory<>("accessLevel")); // MedicalEquipmentSR fields
            col6.setCellValueFactory(new PropertyValueFactory<>("username"));
            col7.setCellValueFactory(new PropertyValueFactory<>("password"));
            col8.setCellValueFactory(new PropertyValueFactory<>("email"));
            col9.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

            table.getColumns().add(col1); // adding columns to setup table
            table.getColumns().add(col2);
            table.getColumns().add(col3);
            table.getColumns().add(col4);
            table.getColumns().add(col5);
            table.getColumns().add(col6);
            table.getColumns().add(col7);
            table.getColumns().add(col8);
            table.getColumns().add(col9);

            table.setEditable(true);
        }
        table.getItems().clear();

        List<Employee> listOfEmployees = db.getAllEmployee();
        table.getItems().addAll(listOfEmployees); // create and add object
    }

    @FXML void goToHome(ActionEvent event) {
        // Try to go home
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void addEmployee(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        lastNameField.setVisible(true);
        firstNameField.setVisible(true);
        positionField.setVisible(true);
        accessLevel.setVisible(true);
        usernameField.setVisible(true);
        passwordField.setVisible(true);
        emailField.setVisible(true);
        phoneNumberField.setVisible(true);
        lastNameField.setDisable(false);
        firstNameField.setDisable(false);
        positionField.setDisable(false);
        accessLevel.setDisable(false);
        usernameField.setDisable(false);
        passwordField.setDisable(false);
        emailField.setDisable(false);
        phoneNumberField.setDisable(false);
        func = Function.ADD;
    }

    @FXML
    private void modifyEmployee(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        lastNameField.setVisible(true);
        firstNameField.setVisible(true);
        positionField.setVisible(true);
        accessLevel.setVisible(true);
        usernameField.setVisible(true);
        passwordField.setVisible(true);
        emailField.setVisible(true);
        phoneNumberField.setVisible(true);
        lastNameField.setDisable(false);
        firstNameField.setDisable(false);
        positionField.setDisable(false);
        accessLevel.setDisable(false);
        usernameField.setDisable(false);
        passwordField.setDisable(false);
        emailField.setDisable(false);
        phoneNumberField.setDisable(false);

        Employee loc = table.getSelectionModel().getSelectedItem();
        employeeIDField.setText(loc.getEmployeeID());
        lastNameField.setText(loc.getLastName());
        firstNameField.setText(loc.getFirstName());
        positionField.setText(loc.getPosition());
        accessLevel.setText(String.valueOf(loc.getAccessLevel()));
        usernameField.setText(loc.getUsername());
        passwordField.setText(loc.getPassword());
        emailField.setText(loc.getEmail());
        phoneNumberField.setText(loc.getPhoneNumber());

        func = Function.MODIFY;
    }

    @FXML
    private void deleteEmployee(ActionEvent actionEvent) {
        db.deleteEmployee(table.getSelectionModel().getSelectedItem().getEmployeeID());
        loadTable();
    }

    @FXML private void locationTableClick(MouseEvent mouseEvent) {
        modifyButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    @FXML private void confirm(ActionEvent actionEvent) {
        if(func == Function.ADD) {
            Employee e = new Employee(
                    employeeIDField.getText(),
                    lastNameField.getText(),
                    firstNameField.getText(),
                    positionField.getText(),
                    Integer.parseInt(accessLevel.getText()),
                    usernameField.getText(),
                    passwordField.getText(),
                    emailField.getText(),
                    phoneNumberField.getText());
            db.addEmployee(e);
            loadTable();
        } else if (func == Function.MODIFY) {
            Employee n = new Employee(
                    employeeIDField.getText(),
                    lastNameField.getText(),
                    firstNameField.getText(),
                    positionField.getText(),
                    Integer.parseInt(accessLevel.getText()),
                    usernameField.getText(),
                    passwordField.getText(),
                    emailField.getText(),
                    phoneNumberField.getText());
            db.updateEmployee(n);

            loadTable();
        } else if (func == Function.IDLOOKUP) {
            table.getItems().clear();
            table.getItems().add(db.getEmployee(employeeIDField.getText())); // create and add object
        }

        clearForm(actionEvent);
        func = Function.NOTHING;
    }

    @FXML private void clearForm(ActionEvent actionEvent) {
        employeeIDField.clear();
        lastNameField.clear();
        firstNameField.clear();
        positionField.clear();
        accessLevel.clear();
        usernameField.clear();
        passwordField.clear();
        emailField.clear();
        phoneNumberField.clear();
    }

    @FXML private void cancelForm(ActionEvent actionEvent) {
        gridPane.setDisable(true);
        gridPane.setVisible(false);
        clearForm(actionEvent);

        addButton.setVisible(true);
        addButton.setDisable(false);

        modifyButton.setVisible(true);
        modifyButton.setDisable(false);

        deleteButton.setVisible(true);
        deleteButton.setDisable(false);

        func = Function.NOTHING;
    }
    @FXML private void idLookup(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        lastNameField.setVisible(false);
        firstNameField.setVisible(false);
        positionField.setVisible(false);
        accessLevel.setVisible(false);
        usernameField.setVisible(false);
        passwordField.setVisible(false);
        emailField.setVisible(false);
        phoneNumberField.setVisible(false);

        func = Function.IDLOOKUP;
    }
}

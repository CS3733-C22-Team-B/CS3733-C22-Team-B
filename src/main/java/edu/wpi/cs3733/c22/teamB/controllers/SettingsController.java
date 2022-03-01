package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lombok.extern.java.Log;

import javax.swing.*;
import javax.swing.text.PasswordView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController extends AbsPage implements Initializable{
    @FXML
    JFXButton HomeB;
    @FXML
    JFXButton RestoreB;
    @FXML
    JFXButton BackupB;
    @FXML
    JFXToggleButton clientServerToggle;
    @FXML JFXToggleButton embeddedToggle;
    @FXML JFXToggleButton remoteToggle;
    @FXML
    Pane anchorPane;
    @FXML
    Pane contentPane;
    @FXML
    PasswordField changePassword;
    @FXML
    PasswordField confirmPassword;
    private static Employee loggedInEmployee;
    private String employeePass;
    private String employeeID;
    public Label passwordError;

    DatabaseWrapper db;

    public SettingsController() throws SQLException {
        this.db = DatabaseWrapper.getInstance();
    }

    public void Backup() throws IOException {
        db.backupAll();

    }

    public void Restore() throws IOException {
        //call DB restore here
        db.restoreAll();

    }

    // Go to the home fxml when the home button is pressed
    @FXML
    void goToHome(ActionEvent event) {
        // Try to go home
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onEmbeddedToggle(ActionEvent actionEvent) throws IOException {
        if (embeddedToggle.isSelected()) {
            clientServerToggle.setSelected(false);
            remoteToggle.setSelected(false);
            db.backupAll();
            db.engageEmbedded();
            db.restoreAll();
        }
    }

    public void onClientToggle(ActionEvent actionEvent) throws IOException {
        if (clientServerToggle.isSelected()) {
            embeddedToggle.setSelected(false);
            remoteToggle.setSelected(false);
            db.backupAll();
            db.engageClient();
            db.restoreAll();
        }
    }

    public void onRemoteToggle(ActionEvent actionEvent) throws IOException {
        if (remoteToggle.isSelected()) {
            clientServerToggle.setSelected(false);
            embeddedToggle.setSelected(false);
            db.backupAll();
            db.engageRemote();
            db.restoreAll();
            System.out.println(db.isRemote());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialize is" + db.isRemote());
        if (db.isRemote()) {
            remoteToggle.setSelected(true);
            clientServerToggle.setSelected(false);
            embeddedToggle.setSelected(false);
            System.out.println("Remote");
        } else if (db.isClient()) {
            remoteToggle.setSelected(false);
            clientServerToggle.setSelected(true);
            embeddedToggle.setSelected(false);
            System.out.println("Client");
        } else {
            remoteToggle.setSelected(false);
            clientServerToggle.setSelected(false);
            embeddedToggle.setSelected(true);
            System.out.println("Embedded");

            List<Employee> employeeList = db.getAllEmployee();
            for(Employee employee: employeeList){
                employeeID = employee.getEmployeeID();
                employeePass = employee.getPassword();
            }

        }

        initResize();
        resize();
        namePage();
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("Settings");
    }

    public void editProfile(ActionEvent actionEvent) {
        if(changePassword.getText().equals(confirmPassword.getText())){
          Employee employee = LoginController.getLoggedInEmployee();
          loggedInEmployee = new Employee(employee.getEmployeeID(), employee.getLastName(), employee.getFirstName(), employee.getPosition(), employee.getAccessLevel(), employee.getUsername(), confirmPassword.getText(), employee.getPassword(), employee.getPhoneNumber());
          db.updateEmployee(loggedInEmployee);
          changePassword.setText("");
          confirmPassword.setText("");
          passwordError.setTextFill(Color.RED);
          passwordError.setText("Password Change Confirmed");
        }
        else if(changePassword.getText().isEmpty() || confirmPassword.getText().isEmpty()){
          passwordError.setTextFill(Color.RED);
          passwordError.setText("A password field is empty, please fill in both");
        }
        else{
          passwordError.setTextFill(Color.RED);
          passwordError.setText("Passwords do not match, Try again");
        }
    }

}

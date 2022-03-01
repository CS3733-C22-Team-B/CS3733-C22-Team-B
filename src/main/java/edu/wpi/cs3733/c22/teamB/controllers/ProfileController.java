package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController extends AbsPage {
    @FXML PasswordField changePassword;
    @FXML PasswordField confirmPassword;
    private static Employee loggedInEmployee;
    public Label passwordError;
    DatabaseWrapper db;

    public ProfileController() {
        this.db = DatabaseWrapper.getInstance();
    }

    @FXML
    public void initialize() {
        initResize();
        resize();
        namePage();
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("Profile");
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

package edu.wpi.cs3733.c22.teamB.controllers;

//import edu.wpi.cs3733.c22.teamB.entity.objects.AbstractSR;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.SRIDGenerator;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML private TextField employeeID;
    @FXML private TextField lastName;
    @FXML private TextField firstName;
    @FXML private TextField position;
    @FXML private TextField accessLevel;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private Button submitButton;
    @FXML private Button backButton;

    public SignUpController(){};

    @FXML private void submit(ActionEvent actionEvent) {
        DatabaseWrapper db = new DatabaseWrapper();
        db.addEmployee(new Employee(employeeID.getText(), lastName.getText(), firstName.getText(), position.getText(), Integer.parseInt(accessLevel.getText()), username.getText(), password.getText(), email.getText(), phoneNumber.getText()));
        clear();
    }

    @FXML private void back(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Login.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void clear() {
        employeeID.clear();
        lastName.clear();
        firstName.clear();
        position.clear();
        accessLevel.clear();
        username.clear();
        password.clear();
        email.clear();
        phoneNumber.clear();
    }
    public void initialize(URL location, ResourceBundle resources) {
    }
}

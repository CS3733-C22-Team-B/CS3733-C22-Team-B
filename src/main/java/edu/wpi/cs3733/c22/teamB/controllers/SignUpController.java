package edu.wpi.cs3733.c22.teamB.controllers;

//import edu.wpi.cs3733.c22.teamB.entity.objects.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements IController, Initializable {

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

    public SignUpController(){};

    @Override
    public void submit() {
        DatabaseWrapper db = new DatabaseWrapper();
        db.addEmployee(new Employee(employeeID.getText(), lastName.getText(), firstName.getText(), position.getText(), Integer.parseInt(accessLevel.getText()), username.getText(), password.getText(), email.getText(), phoneNumber.getText()));

    }

    @Override
    public void submit(AbstractSR sr) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

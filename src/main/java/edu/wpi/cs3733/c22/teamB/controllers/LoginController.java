package edu.wpi.cs3733.c22.teamB.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField passField;

    @FXML
    private TextField userField;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize(){
    }

    @FXML
    void loginButton(ActionEvent event) {
        if(passField.getText().isEmpty() || userField.getText().isEmpty()){
            errorMessage.setText("Enter a username and password");
        } else if(!passField.getText().equals("admin") || !userField.getText().equals("admin")){
            errorMessage.setText("Incorrect username or password");
        } else{

        }
    }

}

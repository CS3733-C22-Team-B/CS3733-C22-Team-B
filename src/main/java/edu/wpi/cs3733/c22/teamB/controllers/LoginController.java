package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize(){
        Bapp.getPrimaryStage().setResizable(false);
    }

    @FXML
    void loginButton(ActionEvent event) {
        if(passField.getText().isEmpty() || userField.getText().isEmpty()){
            errorMessage.setText("Enter a username and password");
        } else if((!passField.getText().equals("admin") || !userField.getText().equals("admin")) && (!passField.getText().equals("staff") || !userField.getText().equals("staff"))){
            errorMessage.setText("Incorrect username or password");
        } else{
            String[] args = new String[2];
            args[0] = userField.getText();
            args[1] = passField.getText();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
                Bapp.getPrimaryStage().getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

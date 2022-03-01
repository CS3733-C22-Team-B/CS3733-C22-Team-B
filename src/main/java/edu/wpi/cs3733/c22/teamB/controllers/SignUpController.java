package edu.wpi.cs3733.c22.teamB.controllers;

//import edu.wpi.cs3733.c22.teamB.entity.objects.AbstractSR;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.SRIDGenerator;
import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML private TextField lastName;
    @FXML private TextField firstName;
    @FXML private TextField position;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField email;
    @FXML private TextField phoneNumber;
    @FXML private Button submitButton;
    @FXML private Button backButton;
    @FXML private Pane anchorPane;
    @FXML private Pane popup;

    public SignUpController(){};

    @FXML private void submit(ActionEvent actionEvent) {
        DatabaseWrapper db = DatabaseWrapper.getInstance();
        db.addEmployee(new Employee(lastName.getText(), firstName.getText(), position.getText(), 1, username.getText(), password.getText(), email.getText(), phoneNumber.getText()));

        // account confirmation popup
        clear();
        setVisible(false);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished(
                event -> setVisible(true)
        );
        visiblePause.play();
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
        lastName.clear();
        firstName.clear();
        position.clear();
        username.clear();
        password.clear();
        email.clear();
        phoneNumber.clear();
    }

    public void setVisible(boolean bool) {
        lastName.setVisible(bool);
        firstName.setVisible(bool);
        position.setVisible(bool);
        username.setVisible(bool);
        password.setVisible(bool);
        email.setVisible(bool);
        phoneNumber.setVisible(bool);
        popup.setVisible(!bool);
    }

    public void initialize(URL location, ResourceBundle resources) {
        setVisible(true);

        anchorPane.setLayoutX(Bapp.getPrimaryStage().getWidth()/4);
        anchorPane.setLayoutY(Bapp.getPrimaryStage().getHeight()/6);
    }




}

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController implements IPage {

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;

    @FXML
    private Label errorMessage;

    @FXML
    private ImageView picture;

    @FXML private Pane logInBox;

    @FXML
    public void initialize(){
        Bapp.getPrimaryStage().setFullScreen(true);
        resize();
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
                Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/AnchorHome.fxml"));
                Bapp.getPrimaryStage().getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void resize() {
        Bapp.getPrimaryStage().heightProperty().addListener((observable)-> {
            picture.setFitWidth(Bapp.getPrimaryStage().getWidth()+100);
            picture.setFitHeight(Bapp.getPrimaryStage().getHeight()+100);
        });
    }

    @Override
    public void namePage() {
    }
}

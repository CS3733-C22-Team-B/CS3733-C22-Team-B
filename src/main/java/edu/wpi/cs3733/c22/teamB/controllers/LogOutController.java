package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class LogOutController {

    @FXML
    void logOut() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Login.fxml"));
            AnchorHomeController.curAnchorHomeController.popup.hide();

            Bapp.getPrimaryStage().getScene().setRoot(root);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    void hide() {
        AnchorHomeController.curAnchorHomeController.popup.hide();
    }
}

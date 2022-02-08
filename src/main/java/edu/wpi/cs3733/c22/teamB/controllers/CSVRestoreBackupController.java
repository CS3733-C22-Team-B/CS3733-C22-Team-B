package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class CSVRestoreBackupController {
    @FXML JFXButton HomeB;
    @FXML JFXButton RestoreB;
    @FXML JFXButton BackupB;

    public void Backup() {
        // call DB backup here
    }

    public void Restore() {
        // call DB restore here
    }
    // Go to the home fxml when the home button is pressed
    @FXML
    void goToHome(ActionEvent event) {
        // Try to go home
        try {
            Parent root =
                    FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

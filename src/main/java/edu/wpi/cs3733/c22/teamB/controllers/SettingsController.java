package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SettingsController {
    @FXML
    JFXButton HomeB;
    @FXML
    JFXButton RestoreB;
    @FXML
    JFXButton BackupB;
    @FXML
    JFXToggleButton clientServerToggle;

    DatabaseWrapper db;

    public SettingsController() throws SQLException {
        this.db = new DatabaseWrapper();
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

    public void onClientToggle(ActionEvent actionEvent) throws IOException{
        if(clientServerToggle.isSelected()) {
            db.backupAll();
            db.initClient();
            db.restoreAll();
        }
        else {
            db.backupAll();
            db.initEmbedded();
            db.restoreAll();
        }
    }
}

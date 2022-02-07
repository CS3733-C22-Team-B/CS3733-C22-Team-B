package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CSVImportController {
    @FXML
    JFXButton HomeButton;
    @FXML
    JFXButton SubmitButton;
    @FXML
    JFXToggleButton ImportExportSlider;
    @FXML
    TextField RestoringTxt;
    @FXML
    TextField BackupTxt;
    @FXML
    Label FileNameLabel;
    @FXML
    Label TitleLabel;

    private boolean isRestoring;

    public boolean isRestoring() {
        return isRestoring;
    }

    public void setRestoring(boolean restoring) {
        isRestoring = restoring;
    }

    @FXML
    void initialize(){
        isRestoring = true;
    }

    @FXML
    void SwitchAction(ActionEvent event) {
        isRestoring = !isRestoring;
        this.update();
    }

    private void update(){
        if(isRestoring) {
            ImportExportSlider.setText("Backing up file");
            RestoringTxt.setDisable(true);
            RestoringTxt.setVisible(false);
            BackupTxt.setDisable(false);
            BackupTxt.setVisible(true);
            isRestoring=false;
        }
        else if(!isRestoring) {
            ImportExportSlider.setText("Restoring file");
            RestoringTxt.setDisable(false);
            RestoringTxt.setVisible(true);
            BackupTxt.setDisable(true);
            BackupTxt.setVisible(false);
            isRestoring = true;
        }
    }

    @FXML
    void Submit(ActionEvent event) {
        if(isRestoring) {

        }
        else if(isRestoring == false) {

        }
    }

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
}


package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CSVImportController_unused {
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
    @FXML
    Label FileNameLabel1;
    @FXML
    JFXComboBox TablePicker;

    boolean isRestoring = true;

    @FXML
    void initialize(){
        String st[] = {"Location", "Medical Equipment", "Employee"};
        TablePicker.setItems(FXCollections.observableArrayList(st));
    }
    @FXML
    void SwitchAction(ActionEvent event) {
        if(ImportExportSlider.getText().equals("Restoring file")) {
            ImportExportSlider.setText("Backing up file");
            FileNameLabel.setText("File name");
            FileNameLabel1.setText("Table to Backup");
            RestoringTxt.setDisable(true);
            RestoringTxt.setVisible(false);
            BackupTxt.setDisable(false);
            BackupTxt.setVisible(true);
        }
        else if(ImportExportSlider.getText().equals("Backing up file")) {
            ImportExportSlider.setText("Restoring file");
            FileNameLabel.setText("File from path");
            FileNameLabel1.setText("Table to Restore");
            RestoringTxt.setDisable(false);
            RestoringTxt.setVisible(true);
            BackupTxt.setDisable(true);
            BackupTxt.setVisible(false);
        }
    }

    @FXML
    void Submit(ActionEvent event) {
        if(ImportExportSlider.getText().equals("Restoring file")) {

        }
        else if(ImportExportSlider.getText().equals("Backing up file")) {

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


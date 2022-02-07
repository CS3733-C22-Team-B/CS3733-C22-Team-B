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
    TextField ImportingTxt;
    @FXML
    TextField ExportingTxt;
    @FXML
    Label FileNameLabel;
    @FXML
    Label TitleLabel;

    @FXML
    void SwitchAction(ActionEvent event) {
        if(ImportExportSlider.getText().equals("Importing file")) {
            ImportExportSlider.setText("Exporting file");
            ImportingTxt.setDisable(true);
            ImportingTxt.setVisible(false);
            ExportingTxt.setDisable(false);
            ExportingTxt.setVisible(true);
        }
        else if(ImportExportSlider.getText().equals("Exporting file")) {
            ImportExportSlider.setText("Importing file");
            ImportingTxt.setDisable(false);
            ImportingTxt.setVisible(true);
            ExportingTxt.setDisable(true);
            ExportingTxt.setVisible(false);
        }
    }

    @FXML
    void Submit(ActionEvent event) {
        if(ImportExportSlider.getText().equals("Importing file")) {

        }
        else if(ImportExportSlider.getText().equals("Exporting file")) {

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


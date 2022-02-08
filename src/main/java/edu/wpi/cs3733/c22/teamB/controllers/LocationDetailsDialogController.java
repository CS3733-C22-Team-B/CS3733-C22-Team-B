package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class LocationDetailsDialogController {

    @FXML
    void buttonPress(ActionEvent event) {
        //TODO make this window disappear
        Bapp.getPrimaryStage().show();
    }
}

package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LocationDetailsDialogController {

    @FXML
    void buttonPress(ActionEvent event) {
        // TODO make this window disappear
        Bapp.getPrimaryStage().show();
    }
}

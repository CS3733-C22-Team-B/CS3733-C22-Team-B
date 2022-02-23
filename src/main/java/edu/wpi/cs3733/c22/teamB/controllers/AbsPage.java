package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class AbsPage implements IPage {
    @FXML
    Pane anchorPane;

    @FXML
    Pane contentPane;

    public void initResize() {
        contentPane.setLayoutX(Bapp.getPrimaryStage().getWidth()/4);
        contentPane.setLayoutY(Bapp.getPrimaryStage().getHeight()/6);
        anchorPane.setPrefWidth(Bapp.getPrimaryStage().getWidth() - AnchorHomeController.curAnchorHomeController.sidebar.getWidth());
        anchorPane.setPrefHeight(Bapp.getPrimaryStage().getHeight() - AnchorHomeController.curAnchorHomeController.sidebar.getHeight());
    }

    public void resize() {
        Bapp.getPrimaryStage().heightProperty().addListener((observable)-> {
            anchorPane.setPrefWidth(Bapp.getPrimaryStage().getWidth() - AnchorHomeController.curAnchorHomeController.sidebar.getWidth());
            anchorPane.setPrefHeight(Bapp.getPrimaryStage().getHeight() - AnchorHomeController.curAnchorHomeController.sidebar.getHeight());
            contentPane.setLayoutX(Bapp.getPrimaryStage().getWidth()/4);
            contentPane.setLayoutY(Bapp.getPrimaryStage().getHeight()/6);
        });
    }

}

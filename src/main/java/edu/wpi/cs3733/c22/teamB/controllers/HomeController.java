package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;


public class HomeController implements IPage{

    @FXML private ImageView picture;
    @FXML private AnchorPane anchor;

    @FXML
    private void initialize(){
        resize();
        namePage();
    }

    @FXML
    private void shutDown() {
        Platform.exit();
    }

//    gets children of fxml page and resizes them accordingly
    @Override
    public void resize() {
        Bapp.getPrimaryStage().heightProperty().addListener((observable)-> {
            picture.setFitWidth(Bapp.getPrimaryStage().getWidth() - 50);
            picture.setFitHeight(Bapp.getPrimaryStage().getHeight()-50);
        });
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("Welcome, Dr. Cristobal Rincon Rogers");
    }
}

package edu.wpi.cs3733.c22.teamB.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class HelpPageController {
@FXML
    ImageView image;
List<Image> imagePaths;

    public void showPage (int index){
        image.setImage(imagePaths.get(index));
    }
}

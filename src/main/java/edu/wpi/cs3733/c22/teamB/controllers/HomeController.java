package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import java.io.IOException;

import edu.wpi.cs3733.c22.teamB.entity.objects.Employee;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import lombok.extern.java.Log;


public class HomeController extends AbsPage{

    @FXML private ImageView picture;
    @FXML private AnchorPane anchorPane;

    @FXML
    private void initialize(){
        picture.setFitWidth(Bapp.getPrimaryStage().getWidth()-30);
        picture.setFitHeight(Bapp.getPrimaryStage().getHeight()-30);
        resize();
        namePage();
    }

    @Override
    public void namePage() {
        Employee employee = LoginController.getLoggedInEmployee();
        String name = employee.getName();
        String position = employee.getPosition();
        AnchorHomeController.curAnchorHomeController.pageName.setText("Welcome, " + position + " " + name + "!");
    }
}

package edu.wpi.cs3733.c22.teamB.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnchorHomeController implements Initializable {
    public static AnchorHomeController curAnchorHomeController;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    Label pageName;

    private Pane childPane;

    public AnchorHomeController() {
        curAnchorHomeController = this;
    }

    private String pageToFXMLPath(String pageName) {
        try {
            return "/edu/wpi/cs3733/c22/teamB/views/" + pageName + ".fxml";
        } catch (Exception e) {
            throw new RuntimeException("Page Does not exist");
        }
    }

    void changeNode(FXMLLoader loader) throws IOException {
        try {
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public AnchorPane getAnchorPane() {
        return this.anchorPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

//            anchorPane.setStyle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToMap() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("MapEditor")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToServiceRequest() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("ServiceRequestMenu")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToTables() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Tables")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


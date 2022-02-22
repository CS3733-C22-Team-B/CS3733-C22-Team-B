package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
    @FXML
    JFXButton homeButton;
    @FXML JFXButton mapButton;
    @FXML JFXButton srButton;
    @FXML JFXButton tableButton;
    @FXML JFXButton dashButton;
    @FXML JFXButton settingsButton;
    @FXML JFXButton helpButton;



    private Pane childPane;

    public AnchorHomeController() {
        curAnchorHomeController = this;
    }

    public AnchorPane getAnchorPane() {
        return this.anchorPane;
    }

    private String pageToFXMLPath(String pageName) {
        try {
            return "/edu/wpi/cs3733/c22/teamB/views/" + pageName + ".fxml";
        } catch (Exception e) {
            throw new RuntimeException("Page Does not exist");
        }
    }

    public void setPageName(String name) {
        pageName.setText(name);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Tooltip tooltip1 = new Tooltip("Home");
            homeButton.setTooltip(tooltip1);

            Tooltip tooltip2 = new Tooltip("Map Editor");
            mapButton.setTooltip(tooltip2);

            Tooltip tooltip3 = new Tooltip("Service Requests");
            srButton.setTooltip(tooltip3);

            Tooltip tooltip4 = new Tooltip("Tables");
            tableButton.setTooltip(tooltip4);

            Tooltip tooltip5 = new Tooltip("Dashboard");
            dashButton.setTooltip(tooltip5);

            Tooltip tooltip6 = new Tooltip("Settings");
            settingsButton.setTooltip(tooltip6);

            Tooltip tooltip7 = new Tooltip("Help");
            helpButton.setTooltip(tooltip7);

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

    @FXML
    void goToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Dashboard")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Settings")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

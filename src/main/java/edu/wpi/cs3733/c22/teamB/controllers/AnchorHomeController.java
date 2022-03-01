package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.BedBrotherCV;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

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
    @FXML JFXButton apiButton;
    @FXML JFXButton profileButton;
    @FXML JFXButton settingsButton;
    @FXML JFXButton helpButton;
    @FXML JFXButton aboutButton;
    @FXML JFXButton creditsButton;
    @FXML JFXButton logOutButton;
    @FXML JFXButton exitButton;
    @FXML public VBox sidebar;
    @FXML JFXButton gameButton;
    FXMLLoader mapLoader = new FXMLLoader(getClass().getResource(pageToFXMLPath("MapEditor")));
    BedBrotherCV equipVision = new BedBrotherCV();
    Thread visionThread = new Thread(equipVision);

    Popup popup = new Popup();
    Pane popUpMessage = new FXMLLoader(getClass().getResource(pageToFXMLPath("LogOutPopUp"))).load();


    private Pane childPane;

    public AnchorHomeController() throws IOException {
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

    public void changeNode(FXMLLoader loader) throws IOException {
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

            Tooltip tooltip5 = new Tooltip("Workflow Simulator");
            dashButton.setTooltip(tooltip5);

            Tooltip tooltip12 = new Tooltip("APIs");
            apiButton.setTooltip(tooltip12);

            Tooltip tooltip6 = new Tooltip("Settings");
            settingsButton.setTooltip(tooltip6);

            Tooltip tooltip7 = new Tooltip("Help");
            helpButton.setTooltip(tooltip7);

            Tooltip tooltip8 = new Tooltip("About");
            aboutButton.setTooltip(tooltip8);

            Tooltip tooltip9 = new Tooltip("Log Out");
            logOutButton.setTooltip(tooltip9);

            Tooltip tooltip10 = new Tooltip("Exit");
            exitButton.setTooltip(tooltip10);

            Tooltip tooltip11 = new Tooltip("Games");
            gameButton.setTooltip(tooltip11);

            sidebar.setMinWidth(130);

            popup.getContent().add(popUpMessage);
//            anchorPane.setStyle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();

        } catch (IOException e) {
            e.printStackTrace();
        }
        visionThread.setPriority(Thread.MIN_PRIORITY);
        visionThread.start();
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
            mapLoader = new FXMLLoader(getClass().getResource(pageToFXMLPath("MapEditor")));
            childPane = mapLoader.load();
            equipVision.setMapController((MapEditorController) mapLoader.getController());
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.setLeftAnchor(childPane,0.0);
            anchorPane.setRightAnchor(childPane,0.0);
            anchorPane.setTopAnchor(childPane,0.0);
            anchorPane.setBottomAnchor(childPane,0.0);
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

    @FXML
    void goToSimulator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Simulator")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToAboutPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("aboutPage")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToCreditsPage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("creditPage2")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToHelpPage(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Help Page")));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logOut() {
        try {
            popup.setAutoHide(true);
            popup.setAutoFix(true);
            Label popupLabel = new Label("text");
            popupLabel.setStyle("-fx-background-color:white;"
                    + " -fx-text-fill: " + "black" + ";"
                    + " -fx-font-size: " + "100px" + ";"
                    + " -fx-padding: 10px;"
                    + " -fx-background-radius: 6;");


            popup.show(Bapp.getPrimaryStage());

        } catch (Exception e) {
            System.out.println("uh oh. pop up did not work");

        }
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void goToGames(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/GamesMenu.fxml"));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAPI(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/OtherTeamAPIs.fxml"));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToProfile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Profile.fxml"));
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


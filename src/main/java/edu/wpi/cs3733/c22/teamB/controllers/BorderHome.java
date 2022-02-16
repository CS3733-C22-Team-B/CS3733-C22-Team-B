package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class BorderHome implements Initializable{
    public static BorderHome curBorderHome;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox hamburger;

    @FXML
    private BorderPane borderPane;

    private Pane childPane;

    public BorderHome() {
        curBorderHome = this;
    }

    private String pageToFXMLPath(String pageName) {
        try {
            return "/edu/wpi/cs3733/c22/teamB/views/" + pageName +".fxml";
        } catch(Exception e) {
            throw new RuntimeException("Page Does not exist");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));
            childPane = loader.load();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();


            AnchorPane.setRightAnchor(childPane, 0.0);
            AnchorPane.setLeftAnchor(childPane, 0.0);
            AnchorPane.setTopAnchor(childPane, 0.0);
            AnchorPane.setBottomAnchor(childPane, 0.0);


            childPane.setPrefHeight(anchorPane.getHeight());
            childPane.setPrefWidth(anchorPane.getWidth());


        } catch (IOException e) {
            e.printStackTrace();
        }

        JFXButton button1 = new JFXButton("Home");
        JFXButton button2 = new JFXButton("Map");
        JFXButton button3 = new JFXButton("Request");
        JFXButton button4 = new JFXButton("Dashboard");
        JFXButton button5 = new JFXButton("Settings");




        VBox leftDrawerPane = new VBox();
        VBox DrawerPane = new VBox();
        leftDrawerPane.getChildren().add(button1);
        leftDrawerPane.getChildren().add(button2);
        leftDrawerPane.getChildren().add(button3);
        leftDrawerPane.getChildren().add(button4);
        leftDrawerPane.getChildren().add(button5);


        leftDrawerPane.setAlignment(Pos.CENTER);
        leftDrawerPane.prefHeightProperty().bind(anchorPane.heightProperty());

        drawer.setSidePane(leftDrawerPane);
        drawer.setDefaultDrawerSize(150);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(true);

        hamburger.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            if (drawer.isClosed()) {
                hamburger.setVisible(false);
                drawer.open();
            }
        });

        drawer.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
            drawer.close();
            hamburger.setVisible(true);

        });

        button1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));

            try {
                childPane = loader.load();
                AnchorPane.setRightAnchor(childPane, 0.0);
                AnchorPane.setLeftAnchor(childPane, 0.0);
                AnchorPane.setTopAnchor(childPane, 0.0);
                AnchorPane.setBottomAnchor(childPane, 0.0);


                childPane.setPrefHeight(anchorPane.getHeight());
                childPane.setPrefWidth(anchorPane.getWidth());


                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        button2.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("MapEditor")));

            try {
                childPane = loader.load();

                AnchorPane.setRightAnchor(childPane, 0.0);
                AnchorPane.setLeftAnchor(childPane, 0.0);
                AnchorPane.setTopAnchor(childPane, 0.0);
                AnchorPane.setBottomAnchor(childPane, 0.0);


                childPane.setPrefHeight(borderPane.getHeight());
                childPane.setPrefWidth(borderPane.getWidth());

                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();



            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button3.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("ServiceRequestMenu")));

            try {
                childPane = loader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button4.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Dashboard")));

            try {
                childPane = loader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        button5.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Settings")));

            try {
                childPane = loader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
                anchorPane.toBack();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


    }

    void changeNode(FXMLLoader loader) throws IOException {
        try {
            childPane = loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(childPane);
            anchorPane.toBack();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }
}


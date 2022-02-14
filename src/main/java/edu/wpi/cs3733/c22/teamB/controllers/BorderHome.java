package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class BorderHome implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox hamburger;

    @FXML
    private BorderPane borderPane;

    private Pane childPane;
    private IController childController;

    public BorderHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));
            childPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String pageToFXMLPath(String pageType) {
        try {
            return "/edu/wpi/cs3733/c22/teamB/views/" + pageType +".fxml";
        } catch(Exception e) {
            throw new RuntimeException("Page Does not exist");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        JFXButton button1 = new JFXButton("Button 1");
        JFXButton button2 = new JFXButton("Button 2");
        JFXButton button3 = new JFXButton("Button 3");
        JFXButton button4 = new JFXButton("Button 4");


        VBox leftDrawerPane = new VBox();
        leftDrawerPane.getChildren().add(button1);
        leftDrawerPane.getChildren().add(button2);
        leftDrawerPane.getChildren().add(button3);
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

        button2.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Home")));

            try {
                childPane = loader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        button1.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pageToFXMLPath("Login")));

            try {
                childPane = loader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(childPane);



            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}


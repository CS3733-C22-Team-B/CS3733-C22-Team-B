package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

//
//        try {
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("views/toolbar.fxml"));
//            Parent toolbar = loader.load();
//            drawer.setSidePane(toolbar);
//        } catch (IOException e) {
//            System.out.println("You fricked it");
//        }

        JFXButton button1 = new JFXButton("Button 1");
        JFXButton button2 = new JFXButton("Button 2");
        JFXButton button3 = new JFXButton("Button 3");
        JFXButton button4 = new JFXButton("Button 4");
//        ImageView image = new ImageView("src/main/resources/edu/wpi/cs3733/c22/teamB/images/logos/brigham-1.jpg");


        VBox leftDrawerPane = new VBox();
        leftDrawerPane.getStyleClass().add("red-400");
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
            if(drawer.isClosed()) {
                hamburger.setVisible(false);
                drawer.open();
            }
        });

        drawer.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
                drawer.close();
                hamburger.setVisible(true);

        });

        button2.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
//            ImageView image2 = new ImageView("src/main/resources/edu/wpi/cs3733/c22/teamB/images/2560px-Healthcare_Building.jpg")




            try {
                anchorPane = (AnchorPane) load(getClass().getResource("views/Home.fxml"));
//                anchorPane.getChildren().clear();
//                anchorPane.getChildren().setAll(node.getChildren());
                anchorPane.getChildren().addAll(anchorPane.getChildren());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}


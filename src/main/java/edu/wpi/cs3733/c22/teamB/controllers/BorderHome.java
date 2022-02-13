package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BorderHome implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("views/toolbar.fxml"));
//            Parent toolbar = loader.load();
//            drawer.setSidePane(toolbar);
//        } catch (IOException e) {
//            System.out.println("You fricked it");
//        }

        StackPane leftDrawerPane = new StackPane();
        leftDrawerPane.getStyleClass().add("red-400");
        leftDrawerPane.getChildren().add(new JFXButton("Left Content"));
        drawer.setSidePane(leftDrawerPane);
        drawer.setDefaultDrawerSize(150);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(true);

        HamburgerBasicCloseTransition hamburgerSlideCloseTransition = new HamburgerBasicCloseTransition(hamburger);
        hamburgerSlideCloseTransition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            hamburgerSlideCloseTransition.setRate(hamburgerSlideCloseTransition.getRate() * -1);
            hamburgerSlideCloseTransition.play();
            if(drawer.isClosed())
            drawer.open();
            else drawer.close();
        });

    }
}


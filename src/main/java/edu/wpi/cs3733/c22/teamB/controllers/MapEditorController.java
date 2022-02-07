package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MapEditorController {

    @FXML
    Circle testPoint0 = new Circle(0,0,15);

    @FXML
    Circle testPoint1 = new Circle(1920,1080,15);

    @FXML
    Circle testPoint2 = new Circle(0,1080,15);

    @FXML
    Circle testPoint3 = new Circle(1920,0,15);



    @FXML
    private AnchorPane anchorPane;


    @FXML
    private ImageView imageView;

    double getImageX(int desiredX){
        final int imageWidth = 5000;
        double x =imageView.fitWidthProperty().get();
    double addx = imageView.getX();
        System.out.println("x + " + addx);
        return ((x/imageWidth)*desiredX) + addx;
    }

    double getImageY(int desiredY){
        final int imageHeight = 3400;
        double y = imageView.fitHeightProperty().get();
        double addy = imageView.getY();
        System.out.println("x + " + addy);
        return ((y/imageHeight)*desiredY) + addy;

    }







    @FXML
    public void initialize(){
        Bapp.getPrimaryStage().setFullScreen(true);
        System.out.println(imageView.fitHeightProperty().get());
        Circle testPoint4 = new Circle(getImageX(1700),getImageY(912),1);
        anchorPane.getChildren().add(testPoint0);
        anchorPane.getChildren().add(testPoint1);
        anchorPane.getChildren().add(testPoint2);
        anchorPane.getChildren().add(testPoint3);
        anchorPane.getChildren().add(testPoint4);
        testPoint0.setFill(Color.BLUE);
        testPoint1.setFill(Color.RED);
        testPoint2.setFill(Color.GREEN);
        testPoint3.setFill(Color.BLACK);
        testPoint4.setFill(Color.PURPLE);
    }

}

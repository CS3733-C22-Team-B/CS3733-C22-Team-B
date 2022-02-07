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
        final int imageHeight = 5000;
        double x = imageView.fitWidthProperty().get();
        double addx = imageView.getLayoutX();
        System.out.println("x + " + addx);
        return ((x/imageHeight)*desiredX) + addx;
    }

    double getImageY(int desiredY){
        final int imageHeight = 3400;
        double y = imageView.fitHeightProperty().get();
        double addy = imageView.getLayoutY();
        System.out.println("x + " + addy);
        return ((y/imageHeight)*desiredY) + addy;

    }







    @FXML
    public void initialize(){
        //Bapp.getPrimaryStage().setFullScreen(false);
        System.out.println(imageView.fitHeightProperty().get());
        Circle testPoint4 = new Circle(getImageX(956),getImageY(3036),3);
        anchorPane.getChildren().add(testPoint4);
        testPoint4.setFill(Color.PURPLE);
    }

}

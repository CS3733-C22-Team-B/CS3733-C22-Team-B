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

    double getImageX(double desiredX){
        final int imageHeight = 5000;
        double x = imageView.getFitWidth();
        double addX = imageView.getLayoutX();
        System.out.println("x + " + addX);
        return ((x/imageHeight)*desiredX) + addX;
    }

    double getImageY(double desiredY){
        final int imageHeight = 5000;
        double x = imageView.getFitWidth();
        double addY = imageView.getLayoutY();
        System.out.println("y + " + addY);
        return ((x/imageHeight)*desiredY) + addY;

    }







    @FXML
    public void initialize(){
        //imageView.setFitHeight(Bapp.getPrimaryStage().getOutputScaleY()-50);
        //Bapp.getPrimaryStage().setFullScreen(false);
        System.out.println(imageView.fitHeightProperty().get());
        Circle testPoint4 = new Circle(getImageX(0),getImageY(0),3);
        anchorPane.getChildren().add(testPoint4);
        Circle testPoint5 = new Circle(getImageX(5000),getImageY(3400),3);
        anchorPane.getChildren().add(testPoint5);
        testPoint4.setFill(Color.PURPLE);
        testPoint5.setFill(Color.ORANGE);
    }

}

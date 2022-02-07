package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.LocationDBI;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class MapEditorController {


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;

    double getImageX(double desiredX){
        final int imageHeight = 5000;
        double x = imageView.getFitWidth();
        double addX = imageView.getLayoutX();
        return ((x/imageHeight)*desiredX) + addX;
    }

    double getImageY(double desiredY){
        final int imageHeight = 5000;
        double x = imageView.getFitWidth();
        double addY = imageView.getLayoutY();
        return ((x/imageHeight)*desiredY) + addY;

    }


    public void addPoint(double x, double y, Color color){
        Circle testPoint = new Circle(getImageX(x),getImageY(y),3);
        anchorPane.getChildren().add(testPoint);
        testPoint.setFill(color);
    }

    public void addPoints(){
        LocationDBI locationDBI = new LocationDBI();
        List<Location> locationList = locationDBI.getAllNodes();
        for(Location local: locationList){
            double x = local.getXcoord();
            double y = local.getYcoord();
            addPoint(x,y,Color.BLACK);
        }
    }




    @FXML
    public void initialize(){
        //Bapp.getPrimaryStage().setFullScreen(false);
        System.out.println(imageView.fitHeightProperty().get());
        addPoint(0,0,Color.ORANGE);
        addPoint(5000,3400, Color.RED);
        addPoints();

    }

}

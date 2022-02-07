package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.LocationDBI;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class MapEditorController {

    String selectedPoint;
    Circle selectedPnt;
    double sceneWidth;
    double sceneHeight;
    double imageHeight;
    double imageWidth;
    LocationDBI locationDBI = new LocationDBI();
    List<Location> locationList = locationDBI.getAllNodes();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;


    //Scene x coordinate to image x coordinate
    double getImageX(double desiredX){
        //The width of the map in image coordinates
        double mapWidth = imageWidth*(sceneHeight/imageHeight);
        System.out.println("mapWidth = " + mapWidth);
        //The offset from the side of the scene
        double xOffset = (sceneWidth-mapWidth)/2.0;
        //Return the new coordinate
        return desiredX*(mapWidth/imageWidth) + xOffset;
    }

    //Scene y coordinate to image y coordinate
    double getImageY(double desiredY){
        //The map is fit to the window's height
        return (desiredY/imageHeight)*sceneHeight;
    }


    //Add a point to the map using image coordinates. Set up onclick.
    public void addPoint(String ID, double x, double y, Color color){
        //Create the point
        Circle testPoint = new Circle(getImageX(x),getImageY(y),3);
        //Add the point to the anchorPane's children
        anchorPane.getChildren().add(testPoint);
        testPoint.setFill(color);
        //Set point ID
        testPoint.idProperty().set(ID);
        //Set up onclick events
        testPoint.setOnMouseClicked(event -> {
            //Deselect previous point, make black
            for(Location local: locationList){
                    if(local.getNodeID().equals(selectedPoint)){
                        //addPoint(local.getNodeID(),local.getXcoord(),local.getYcoord(),Color.BLACK);
                        selectedPnt.setFill(Color.BLACK);
                    }
            }
            //Select current point
            testPoint.setFill(Color.RED);
            System.out.println(testPoint.idProperty().get());
            selectedPoint = (testPoint.idProperty().get());
            selectedPnt = testPoint;
        });
    }

    //Add points from DB
    public void addPoints(String Floor){
        for(Location local: locationList){
            if(local.getFloor().equals(Floor)) {
                String ID = local.getNodeID();
                double x = local.getXcoord();
                double y = local.getYcoord();
                addPoint(ID, x, y, Color.BLACK);
            }
        }
    }

    void removeAllPoints(){
        anchorPane.getChildren().remove(1,anchorPane.getChildren().size());
    }

    void removePoint(){
        anchorPane.getChildren().remove(selectedPnt);
        //TODO remove from DB
    }

    @FXML public void refresh(){
        removeAllPoints();
        addPoints("3");
    }

    @FXML public void goTo(String floor){
        Image image;
        removeAllPoints();
        switch (floor) {
            case "1":
                    image = new Image("C:\\Users\\Owner\\Downloads\\SpikeB\\CS3733-C22-Team-B\\src\\main\\resources\\edu\\wpi\\cs3733\\c22\\teamB\\images\\thefirstfloor.png");
                break;
            case "2":
                    image = new Image("C:\\Users\\Owner\\Downloads\\SpikeB\\CS3733-C22-Team-B\\src\\main\\resources\\edu\\wpi\\cs3733\\c22\\teamB\\images\\thesecondfloor.png");
                break;
            case"L2":
                    image = new Image("C:\\Users\\Owner\\Downloads\\SpikeB\\CS3733-C22-Team-B\\src\\main\\resources\\edu\\wpi\\cs3733\\c22\\teamB\\images\\thelowerlevel2.png");
                break;
            case"L1":
                    image = new Image("C:\\Users\\Owner\\Downloads\\SpikeB\\CS3733-C22-Team-B\\src\\main\\resources\\edu\\wpi\\cs3733\\c22\\teamB\\images\\thelowerlevel1.png");
                break;
            default:
                image = new Image("C:\\Users\\Owner\\Downloads\\SpikeB\\CS3733-C22-Team-B\\src\\main\\resources\\edu\\wpi\\cs3733\\c22\\teamB\\images\\thirdFloorMap.png");
            break;
        }
        addPoints(floor);

        imageView.setImage(image);
        addPoints(floor);
    }

    @FXML public void delete(){
        removePoint();
    }

    @FXML
    public void initialize(){
        //Bapp.getPrimaryStage().setFullScreen(false);
        Bapp.getPrimaryStage().setMaximized(true);
        Bapp.getPrimaryStage().resizableProperty().set(false);
        sceneWidth = Bapp.getPrimaryStage().getScene().getWidth();
        sceneHeight = Bapp.getPrimaryStage().getScene().getHeight();
        System.out.println("Scene Width = " + sceneWidth);
        System.out.println("Scene Height = " + sceneHeight);
        imageView.setFitHeight(sceneHeight);
        imageHeight = imageView.getImage().getHeight();
        imageWidth = imageView.getImage().getWidth();



        addPoint("1",0,0,Color.ORANGE);
        addPoint("2",5000,3400, Color.RED);
        addPoints("3");

    }



}

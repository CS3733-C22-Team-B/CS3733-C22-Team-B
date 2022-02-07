package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.LocationDBI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
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
    String currentFloor = "3";

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;


    //Scene x coordinate to image x coordinate
    double getImageX(double desiredX){
        //The width of the map in image coordinates
        double mapWidth = imageWidth*(sceneHeight/imageHeight);
        //System.out.println("mapWidth = " + mapWidth);
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
            //System.out.println(testPoint.idProperty().get());
            selectedPoint = (testPoint.idProperty().get());
            selectedPnt = testPoint;
        });
    }

    //Add points from DB
    public void addPoints(){
        for(Location local: locationList){
            if(local.getFloor().equals(currentFloor)) {
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

    void deleteSelectedNode(){
        anchorPane.getChildren().remove(selectedPnt);
        locationDBI.deleteNode(selectedPoint);
    }

    @FXML public void refresh(){
        //TODO Load from CSV
        removeAllPoints();
        addPoints();
    }

    //TODO Backup Button (go to csv)

    @FXML public void goToL2(){
        currentFloor = "L2";
        goTo();
    }
    @FXML public void goToL1(){
        currentFloor = "L1";
        goTo();
    }
    @FXML public void goTo1(){
        currentFloor = "1";
        goTo();
    }
    @FXML public void goTo2(){
        currentFloor = "2";
        goTo();
    }
    @FXML public void goTo3(){
        currentFloor = "3";
        goTo();
    }

    @FXML public void goTo(){
        Image image;
        removeAllPoints();
        switch (currentFloor) {
            case "1":   //TODO Probably needs to be changed to work with jar file vvv
                    image = new Image("/edu/wpi/cs3733/c22/teamB/images/thefirstfloor.png");
                break;
            case "2":
                    image = new Image("/edu/wpi/cs3733/c22/teamB/images/thesecondfloor.png");
                break;
            case"L2":
                    image = new Image("/edu/wpi/cs3733/c22/teamB/images/thelowerlevel2.png");
                break;
            case"L1":
                    image = new Image("/edu/wpi/cs3733/c22/teamB/images/thelowerlevel1.png");
                break;
            default:
                image = new Image("/edu/wpi/cs3733/c22/teamB/images/thirdFloorMap.png");
            break;
        }
        removeAllPoints();
        addPoints();
        imageView.setImage(image);
    }

    @FXML public void delete(){
        deleteSelectedNode();
    }

    @FXML
    public void initialize(){
        //Bapp.getPrimaryStage().setFullScreen(false);
        Bapp.getPrimaryStage().setMaximized(true);
        Bapp.getPrimaryStage().resizableProperty().set(false);
        sceneWidth = Bapp.getPrimaryStage().getScene().getWidth();
        sceneHeight = Bapp.getPrimaryStage().getScene().getHeight();
        //System.out.println("Scene Width = " + sceneWidth);
        //System.out.println("Scene Height = " + sceneHeight);
        imageView.setFitHeight(sceneHeight);
        imageHeight = imageView.getImage().getHeight();
        imageWidth = imageView.getImage().getWidth();



        addPoint("1",0,0,Color.ORANGE);
        addPoint("2",5000,3400, Color.RED);
        addPoints();
    }

    @FXML
    void homeButton(ActionEvent event) {
        // Try to go home
        try {
            Bapp.getPrimaryStage().resizableProperty().set(true);
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

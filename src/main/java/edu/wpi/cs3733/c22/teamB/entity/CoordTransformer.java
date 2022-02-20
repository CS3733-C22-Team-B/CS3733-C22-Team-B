package edu.wpi.cs3733.c22.teamB.entity;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CoordTransformer {

    private ImageView imageView;
    //Multiply image coords by this coefficient to get the scale of the Node coordinate system
    //Divide to go the other way (Node -> image)
    private double nodeScaleCoeff;

    private void recalcScaleCoeff(){
        nodeScaleCoeff = imageView.getImage().getHeight()/Bapp.getPrimaryStage().getScene().getHeight();
    }

    public CoordTransformer(ImageView imageView){
        this.imageView = imageView;
        nodeScaleCoeff = imageView.getImage().getHeight()/Bapp.getPrimaryStage().getScene().getHeight();
    }

    //Image coordinate system:
    //Coordinates in terms of the .png map photos
    //The database stores Image coordinates

    //Node coordinate system:
    //Coordinates in terms of the Nodes in the pane on the user's screen
    //The screen does this that's why we need it y'know

    //Image to Node is good for getting points in the DB and making them appear
    //This top one exists because MouseEvent can do this for us
    public Point2D imageToNode(MouseEvent event){
        Point2D point = new Point2D(event.getSceneX(),event.getSceneY());
        //Idea: check if this matches the coordinates from using the nodeScaleCoeff (I think it should?), maybe print something if not
        return point;
    }
    //Formerly getImageX/Y
    public Point2D imageToNode(double imageX, double imageY){
        recalcScaleCoeff(); //In case the primary scene changes size
        //Apply the scale factor to the image coordinates to get the Node coordinates
        double nodeX = imageX/nodeScaleCoeff;
        double nodeY = imageY/nodeScaleCoeff;
        //Make a new point holding these values, return it
        Point2D point = new Point2D(nodeX,nodeY);
        return point;
    }

    //Node to image is good for getting click or Node coordinates and making them exist in the DB
    //Formerly getMapX/Y
    public Point2D nodeToImage(double nodeX, double nodeY){
        recalcScaleCoeff(); //In case the primary scene changes size
        //Apply the scale factor to the Node coordinates to get the image coordinates
        double imageX = nodeX*nodeScaleCoeff;
        double imageY = nodeY*nodeScaleCoeff;
        //Make a new point holding these values, return it
        Point2D point = new Point2D(imageX,imageY);
        return point;
    }
}
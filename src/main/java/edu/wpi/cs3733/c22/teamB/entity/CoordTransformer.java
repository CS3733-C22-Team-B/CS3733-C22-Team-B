package edu.wpi.cs3733.c22.teamB.entity;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.kurobako.gesturefx.GesturePane;

public class CoordTransformer {

    private ImageView imageView;
    //Multiply image coords by this coefficient to get the scale of the Node coordinate system
    //Divide to go the other way (Node -> image)
    private double nodeScaleCoeff;
    private StackPane stackPane;
    private GesturePane gesturePane;
    private AnchorPane anchorPane;

    private void recalcScaleCoeff(){
//        nodeScaleCoeff = imageView.getImage().getHeight()/stackPane.getHeight();
        nodeScaleCoeff = 1.0;
    }

    public void setStackPane(StackPane stackpane){
        this.stackPane = stackpane;
        recalcScaleCoeff();
    }

    public void setGesturePane(GesturePane gesturePane){
        this.gesturePane = gesturePane;
        recalcScaleCoeff();
    }

    public void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    public CoordTransformer(ImageView imageView, GesturePane gesturePane){
        this.imageView = imageView;
        this.gesturePane = gesturePane;
    }

    //Image coordinate system:
    //Coordinates in terms of the .png map photos
    //The database stores Image coordinates

    //Node coordinate system:
    //Coordinates in terms of the Nodes in the pane on the user's screen
    //The screen does this that's why we need it y'know

    //Image to Node is good for getting points in the DB and making them appear
    //This top one exists because MouseEvent can do this for us
    public Point2D eventToNode(MouseEvent event){
        recalcScaleCoeff();
        double nodeX = event.getSceneX()-(0.5*imageView.getImage().getWidth()/nodeScaleCoeff);
        double nodeY = event.getSceneY()-(0.5*imageView.getImage().getHeight()/nodeScaleCoeff);
        Point2D point = new Point2D(nodeX,nodeY);
        //Idea: check if this matches the coordinates from using the nodeScaleCoeff (I think it should?), maybe print something if not
        return point;
    }
    //Formerly getImageX/Y
    public Point2D imageToNode(double imageX, double imageY){
        recalcScaleCoeff(); //In case the primary scene changes size
        //Apply the scale factor to the image coordinates to get the Node coordinates
        double nodeX = (imageX - 0.5*imageView.getImage().getWidth())/nodeScaleCoeff;
        double nodeY = (imageY - 0.5*imageView.getImage().getHeight())/nodeScaleCoeff;
        //Make a new point holding these values, return it
        Point2D point = new Point2D(nodeX,nodeY);
        return point;
    }

    //Node to image is good for getting click or Node coordinates and making them exist in the DB
    //Formerly getMapX/Y
    public Point2D nodeToImage(double nodeX, double nodeY){
        recalcScaleCoeff(); //In case the primary scene changes size
        //Apply the scale factor to the Node coordinates to get the image coordinates
        double imageX = nodeX*nodeScaleCoeff + 0.5*imageView.getImage().getWidth();
        double imageY = nodeY*nodeScaleCoeff + 0.5*imageView.getImage().getHeight();
        //Make a new point holding these values, return it
        Point2D point = new Point2D(imageX,imageY);
        return point;
    }
    public Point2D eventToImage(MouseEvent event){
        recalcScaleCoeff();
        double scale = gesturePane.getCurrentScale();
        double yOffset = 75; //Height of top bar
        double imageX = (event.getSceneX()/scale) - gesturePane.getCurrentX();
        double imageY = (event.getSceneY()- yOffset)/scale - gesturePane.getCurrentY();
        return new Point2D(imageX,imageY);
    }

    //Gets node movement and scales it depending on zoom
    public double scaleNodeMovement(double nodeDelta){
        return nodeDelta/gesturePane.getCurrentScale();
    }
}

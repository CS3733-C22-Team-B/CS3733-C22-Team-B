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
    private StackPane stackPane;
    private GesturePane gesturePane;
    private AnchorPane anchorPane;
    private final double yOffset = 75; //Height of top bar

    public void setStackPane(StackPane stackpane){
        this.stackPane = stackpane;
    }

    public void setGesturePane(GesturePane gesturePane){
        this.gesturePane = gesturePane;
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
        double nodeX = event.getSceneX()-0.5*imageView.getImage().getWidth();
        double nodeY = event.getSceneY()-0.5*imageView.getImage().getHeight();
        Point2D point = new Point2D(nodeX,nodeY);
        //Idea: check if this matches the coordinates from using the nodeScaleCoeff (I think it should?), maybe print something if not
        return point;
    }
    //Formerly getImageX/Y
    public Point2D imageToNode(double imageX, double imageY){
        double nodeX = imageX - 0.5*imageView.getImage().getWidth();
        double nodeY = imageY - 0.5*imageView.getImage().getHeight();
        //Make a new point holding these values, return it
        Point2D point = new Point2D(nodeX,nodeY);
        return point;
    }

    //Node to image is good for getting click or Node coordinates and making them exist in the DB
    //Formerly getMapX/Y
    public Point2D nodeToImage(double nodeX, double nodeY){
        double imageX = nodeX + 0.5*imageView.getImage().getWidth();
        double imageY = nodeY + 0.5*imageView.getImage().getHeight();
        //Make a new point holding these values, return it
        Point2D point = new Point2D(imageX,imageY);
        return point;
    }
    public Point2D eventToImage(MouseEvent event){
        final double sidebarWidth = 130;
        double scale = gesturePane.getCurrentScale();
        double imageX = ((event.getSceneX()-130)/scale) - gesturePane.getCurrentX();
        double imageY = (event.getSceneY() - yOffset)/scale - gesturePane.getCurrentY();
        return new Point2D(imageX,imageY);
    }

    //Gets node movement and scales it depending on zoom
    public double scaleNodeMovement(double nodeDelta){
        return nodeDelta/gesturePane.getCurrentScale();
    }
}

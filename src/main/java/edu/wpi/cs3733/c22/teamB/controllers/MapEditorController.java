package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.*;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MapEditorController{

    public javafx.scene.control.TextField idField;
    public JFXComboBox floor;
    public JFXComboBox nodeType;
    public TextField shortName;
    public TextField longName;
    public Label header1;
    public Label header4;
    public Label header6;
    public Label header7;
    public Label header8;
    //public JFXButton deleteButton;
    public JFXToggleButton moveButton;
    public JFXCheckBox showLocations;
    public JFXCheckBox showMedical;
    public VBox modifyPopup;
    public JFXCheckBox showSR;
    public TextField nameField;
    public TextField typeField;
    public TextField manField;
    public Label header2;
    public Label header3;
    public Label header5;
    public Label header9;
    public JFXComboBox Locations;
    public Label header10;
    public JFXComboBox status;
    public Label header11;
    public TextField color;
    public Label header12;
    public TextField size;
    public ScrollPane scroll;

    String selectedPoint;
    Circle selectedPnt;
    ImageView selectedImg;
    double sceneWidth;
    double sceneHeight;
    double imageHeight;
    double imageWidth;
    double orgSceneX, orgSceneY;
    DatabaseWrapper dbWrapper = new DatabaseWrapper();
    List<Location> locationList = dbWrapper.getAllLocation();
    List<MedicalEquipment> medicalList = dbWrapper.getAllMedicalEquipment();
    List<AbstractSR> srList = dbWrapper.getAllSR();
    //CSVRestoreBackupController backupper = new CSVRestoreBackupController();
    String currentFloor = "03";
    boolean addState = false;
    boolean moveState = false;
    String clicked = "location";

    Image firstFloorImage = new Image("/edu/wpi/cs3733/c22/teamB/images/thefirstfloor.png");
    Image secondFloorImage = new Image("/edu/wpi/cs3733/c22/teamB/images/thesecondfloor.png");
    Image lowerLevel2Image = new Image("/edu/wpi/cs3733/c22/teamB/images/thelowerlevel2.png");
    Image lowerLevel1Image = new Image("/edu/wpi/cs3733/c22/teamB/images/thelowerlevel1.png");
    Image thirdFloorImage = new Image("/edu/wpi/cs3733/c22/teamB/images/thirdFloorMap.png");
    Image medical = new Image("/edu/wpi/cs3733/c22/teamB/images/medical.png");
    Image clipboard = new Image("/edu/wpi/cs3733/c22/teamB/images/clipboard.png");
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXButton addButton;

//    @FXML
//    private JFXButton modifyButton;



    @FXML
    private JFXButton submitModifyButton;
    @FXML private JFXButton goToL1Button;
    @FXML private JFXButton goToL2Button;
    @FXML private JFXButton goTo1Button;
    @FXML private JFXButton goTo2Button;
    @FXML private JFXButton goTo3Button;


    @FXML
    public void initialize(){
        Bapp.getPrimaryStage().setMaximized(true);
//        Bapp.getPrimaryStage().resizableProperty().set(false);
        sceneWidth = Bapp.getPrimaryStage().getScene().getWidth();
        sceneHeight = Bapp.getPrimaryStage().getScene().getHeight();
        imageView.setFitHeight(sceneHeight);
        scroll.setPrefViewportWidth(2000);
        scroll.setMinViewportWidth(1000);

        scroll.setPrefViewportHeight(2000);
        scroll.setMinViewportHeight(1000);

        imageHeight = imageView.getImage().getHeight();
        imageWidth = imageView.getImage().getWidth();
        showLocations.setSelected(true);
        showMedical.setSelected(true);
        showSR.setSelected(true);
        setEditFieldsVisible(false);
        Locations.getItems().addAll(dbWrapper.getAllLocation());
//        modifyButton.setOpacity(0.5);
//        modifyButton.setDisable(true);
//        deleteButton.setOpacity(0.5);
//        deleteButton.setDisable(true);
        goTo3Button.setStyle("-fx-background-color: #007fff");
        nodeType.getItems().addAll("PATI","STOR","DIRT","HALL","ELEV","REST","STAI","DEPT","LABS","INFO","CONF","EXIT","RETL","SERV");
        floor.getItems().addAll("L2","L1","01","02","03");
        status.getItems().addAll("DONE","CANCELED","WAITING","BLANK");
        floor.setValue(currentFloor);
        addPoint("1",0,0,Color.ORANGE);
        addPoint("2",imageWidth,imageHeight, Color.RED);
        addPoints();
        modifyPopup.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0,12,12,12,false), javafx.geometry.Insets.EMPTY)));
        modifyPopup.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, new CornerRadii(0,12,12,12,false),new BorderWidths(1), Insets.EMPTY)));
        modifyPopup.setVisible(false);
        modifyPopup.setStyle("-fx-padding: 1;");
    }

    //Add points from DB
    public void addPoints(){
        for (Location local : locationList) {
            if (local.getFloor().equals(currentFloor)) {
                String ID = local.getNodeID();
                double x = local.getXcoord();
                double y = local.getYcoord();
                addPoint(ID, x, y, Color.BLACK);
            }
        }


        for (MedicalEquipment local : medicalList) {
            if (local.getLocation().getFloor().equals(currentFloor)) {
                String ID = local.getEquipmentID();
                double x = local.getLocation().getXcoord(); //TODO fix for future iterations
                double y = local.getLocation().getYcoord();
                addPointMedical(ID, x, y, Color.BLUE);
            }
        }

        for (AbstractSR local : srList) {
            if (local.getLocation().getFloor().equals(currentFloor)) {
                String ID = local.getSrID();
                double x = local.getLocation().getXcoord() + 20; //TODO fix for future iterations
                double y = local.getLocation().getYcoord();
                addPointSR(ID, x, y, Color.LIME);
                //System.out.println("add point at: " + x + " , " + y);
            }
        }


    }

    //Add a point to the map using image coordinates. Set up onclick.
    public Circle addPoint(String ID, double x, double y, Color color){

        //Create the point
        Circle testPoint = new Circle(getImageX(x), getImageY(y), 3);
        //Add the point to the anchorPane's children
        if(showLocations.isSelected()) {
            anchorPane.getChildren().add(testPoint);
            testPoint.setFill(color);
            //Set point ID
            testPoint.idProperty().set(ID);
            //Set up onclick events
            testPoint.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {

                    orgSceneX = event.getSceneX();
                    orgSceneY = event.getSceneY();
//                    modifyButton.setOpacity(1);
//                    modifyButton.setDisable(false);
//                    deleteButton.setOpacity(1);
//                    deleteButton.setDisable(false);
                    clicked = "location";
                    onPointClick(testPoint);
                    event.setDragDetect(true);
                }
            });

            testPoint.setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    if (moveState) {
                        Location temp = dbWrapper.getLocation(selectedPoint);
                        dbWrapper.updateLocation(new Location(selectedPnt.getId(), (int) getMapX(event.getX()), (int) getMapY(event.getY()), temp.getFloor(), temp.getBuilding(), temp.getNodeType(), temp.getLongName(), temp.getShortName()));
                        testPoint.setCenterX((event.getX()));
                        testPoint.setCenterY((event.getY()));
                        refresh();
                    }
                }
            });

            testPoint.setOnMouseDragged((t) -> {
                if (moveState) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;

                    Circle c = (Circle) (t.getSource());

                    c.setCenterX(c.getCenterX() + offsetX);
                    c.setCenterY(c.getCenterY() + offsetY);

                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    updatePopup();
                }
            });

        }
        return testPoint;

    }


    //add a medical point
    public void addPointMedical(String ID, double x, double y, Color color){
        if(showMedical.isSelected()) {
            //Create the point
            //getImageX(x),getImageY(y)
            ImageView testImg = new ImageView(medical);
            //Add the point to the anchorPane's children
            anchorPane.getChildren().add(testImg);
            //Set point ID
            testImg.idProperty().set(ID);
            testImg.setX(getImageX(x));
            testImg.setY(getImageY(y));
            testImg.setPreserveRatio(true);
            testImg.setFitWidth(15);

            testImg.setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    System.out.println("moving medical");
                    if (moveState) {
                        Location tempLoc = getClosetLocation(event.getX(), event.getY());
                        //double dist = calculateDistanceBetweenPoints(tempLoc.getXcoord(), tempLoc.getYcoord(), event.getX(), event.getY());
                        //System.out.println(dist);
                        MedicalEquipment temp = dbWrapper.getMedicalEquipment(selectedImg.getId());
                        temp.setLocation(tempLoc);

                        dbWrapper.updateMedicalEquipment(temp);
                        testImg.setX(getImageX(tempLoc.getXcoord()));
                        testImg.setY(getImageY(tempLoc.getYcoord()));
                    }
                }
            });

            testImg.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    orgSceneX = event.getSceneX();
                    orgSceneY = event.getSceneY();
//                modifyButton.setOpacity(1);
//                modifyButton.setDisable(false);
//                deleteButton.setOpacity(1);
//                deleteButton.setDisable(false);
                    clicked = "equipment";
                    onImgClick(testImg);
                    event.setDragDetect(true);
                }
            });


            testImg.setOnMouseDragged((t) -> {
                if (moveState) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;

                    ImageView c = (ImageView) (t.getSource());

                    c.setX(c.getX() + offsetX);
                    c.setY(c.getY() + offsetY);

                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();


                }
            });

        }
    }

    public void addPointSR(String ID, double x, double y,Color color){
        if(showSR.isSelected()) {
            //Create the point
            //getImageX(x),getImageY(y)
            ImageView testImg = new ImageView(clipboard);
            //Add the point to the anchorPane's children
            anchorPane.getChildren().add(testImg);
            //Set point ID
            testImg.idProperty().set(ID);
            testImg.setX(getImageX(x));
            testImg.setY(getImageY(y));
            testImg.setPreserveRatio(true);
            testImg.setFitWidth(15);
        }
    }

//    public TextField nameField;
//    public TextField typeField;
//    public TextField manField;

    void setEditFieldsVisible(boolean isVisible) {
        modifyPopup.setVisible(isVisible);
        modifyPopup.getChildren().removeAll(new Node[]{header12,size,header11,color,header10,status,Locations,header9,header2,header3,header5,nameField,typeField,manField,header1,header4,header6,header7,header8,idField,floor,nodeType,shortName,longName,submitModifyButton});
        if (clicked == "location") {
            modifyPopup.getChildren().addAll(new Node[]{header1,idField,header4,floor,header6,nodeType,header7,shortName,header8,longName,submitModifyButton});
            header1.setText("Location ID:");
            header8.setText("Long Name:");
        } else if (clicked == "equipment"){
            modifyPopup.getChildren().addAll(new Node[]{header1,idField,header2,nameField,header3,typeField,header5,manField,header9,Locations,header10,status,header11,color,header12,size,header8,longName,submitModifyButton});
            header1.setText("Equipment ID:");
            header8.setText("Description:");
        }



    }

    //Scene x coordinate to image x coordinate
    double getImageX(double desiredX){
        //The width of the map in image coordinates
        double mapWidth = imageWidth*(sceneHeight/imageHeight);
        //System.out.println("mapWidth = " + mapWidth);
        //The offset from the side of the scene
        double xOffset = 0;//(sceneWidth-mapWidth)/2.0;
        //Return the new coordinate
        return desiredX*(mapWidth/imageWidth) + xOffset;
    }

    //Scene y coordinate to image y coordinate
    double getImageY(double desiredY){
        //The map is fit to the window's height
        return (desiredY/imageHeight)*sceneHeight;
    }

    //Scene x coordinate to image x coordinate
    double getMapX(double desiredX){
        //The width of the map in image coordinates
        double mapWidth = imageWidth*(sceneHeight/imageHeight);
        //System.out.println("mapWidth = " + mapWidth);
        //The offset from the side of the scene
        double xOffset = 0;//(sceneWidth-mapWidth)/2.0;
        //Return the new coordinate
        return (desiredX-xOffset)/(mapWidth/imageWidth);
    }

    //Scene y coordinate to image y coordinate
    double getMapY(double desiredY){
        //The map is fit to the window's height
        return desiredY/(sceneHeight/imageHeight);
    }

    public void onPointClick(Circle testPoint){
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
//        Location local = dbWrapper.getLocation(selectedPoint);
//        idField.setText(selectedPoint);
//        floor.setValue(local.getFloor());
//        nodeType.setValue(local.getNodeType());
//        shortName.setText(local.getShortName());
//        longName.setText(local.getLongName());
        modify();
        updatePopup();
    }

    public void onImgClick(ImageView testImg){
        selectedImg = testImg;
        MedicalEquipment local = dbWrapper.getMedicalEquipment(selectedImg.getId());
        idField.setText(local.getEquipmentID());
        typeField.setText(local.getEquipmentType());
        nameField.setText(local.getEquipmentName());
        manField.setText(local.getManufacturer());
        Locations.setValue(local.getLocation());
        longName.setText(local.getDescription());
        status.setValue(local.getStatus());
        color.setText(local.getColor());
        size.setText(local.getSize());
        modify();
        updatePopup();

    }





    void removeAllPoints(){
        anchorPane.getChildren().remove(1,anchorPane.getChildren().size());
    }

    void deleteSelectedNode(){
        anchorPane.getChildren().remove(selectedPnt);
        dbWrapper.deleteLocation(selectedPnt.getId());
    }

    @FXML public void refresh(){
        locationList = dbWrapper.getAllLocation();
        medicalList = dbWrapper.getAllMedicalEquipment();
        srList = dbWrapper.getAllSR();
        removeAllPoints();
        addPoints();
    }


    @FXML public void goToL2(){
        currentFloor = "L2";
        goTo();
    }
    @FXML public void goToL1(){
        currentFloor = "L1";
        goTo();
    }
    @FXML public void goTo1(){
        currentFloor = "01";
        goTo();
    }
    @FXML public void goTo2(){
        currentFloor = "02";
        goTo();
    }
    @FXML public void goTo3(){
        currentFloor = "03";
        goTo();
    }

    @FXML public void saveToCSV(){
//        try {
//            backupper.Backup();
//        } catch (IOException ex){
//            ex.printStackTrace();
//        }
        refresh();
    }

    @FXML public void goTo(){
        goTo1Button.setStyle("-fx-background-color: #eaeaea");
        goTo2Button.setStyle("-fx-background-color: #eaeaea");
        goTo3Button.setStyle("-fx-background-color: #eaeaea");
        goToL1Button.setStyle("-fx-background-color: #eaeaea");
        goToL2Button.setStyle("-fx-background-color: #eaeaea");

        switch (currentFloor) {
            case "01":
                imageView.setImage(firstFloorImage);
                goTo1Button.setStyle("-fx-background-color: #007fff");
                break;
            case "02":
                imageView.setImage(secondFloorImage);
                goTo2Button.setStyle("-fx-background-color: #007fff");
                break;
            case"L2":
                imageView.setImage(lowerLevel2Image);
                goToL2Button.setStyle("-fx-background-color: #007fff");
                break;
            case"L1":
                imageView.setImage(lowerLevel1Image);
                goToL1Button.setStyle("-fx-background-color: #007fff");
                break;
            default:
                imageView.setImage(thirdFloorImage);
                goTo3Button.setStyle("-fx-background-color: #007fff");
                break;
        }
        selectedPoint = null;
        selectedPnt = null;
//        modifyButton.setOpacity(0.5);
//        modifyButton.setDisable(true);
//        deleteButton.setOpacity(0.5);
//        deleteButton.setDisable(true);
        refresh();
    }

    @FXML public void delete(){
        deleteSelectedNode();
        setEditFieldsVisible(false);
    }

    @FXML public void close(){
        setEditFieldsVisible(false);
    }



    @FXML public void modify(){
        if(!moveState)
            setEditFieldsVisible(true);
        if(clicked == "location") {
            Location local = dbWrapper.getLocation(selectedPoint);
            idField.setText(selectedPoint);
            floor.setValue(local.getFloor());
            nodeType.setValue(local.getNodeType());
            shortName.setText(local.getShortName());
            longName.setText(local.getLongName());
        } else if (clicked == "equipment"){
            MedicalEquipment local = dbWrapper.getMedicalEquipment(selectedImg.getId());
            idField.setText(local.getEquipmentID());
            floor.setValue(" ");
            nodeType.setValue("local.getNodeType()");
            shortName.setText("local.getShortName()");
            longName.setText(local.getDescription());
        }
        updatePopup();
    }


    public void submitModify(ActionEvent actionEvent) {
        if(clicked == "location") {
            Location old = dbWrapper.getLocation(selectedPnt.getId());
            Location changedNode = new Location(idField.getText(), old.getXcoord(), old.getYcoord(), floor.getValue().toString(), "TOWER", nodeType.getValue().toString(), shortName.getText(), longName.getText());
            dbWrapper.updateLocation(changedNode);
        } else if (clicked == "equipment"){
            System.out.println(dbWrapper.getMedicalEquipment(selectedImg.getId()));
            MedicalEquipment old = dbWrapper.getMedicalEquipment(selectedImg.getId());
            old.setEquipmentName(nameField.getText());
            old.setEquipmentType(typeField.getText());
            old.setManufacturer(manField.getText());
            old.setLocation((Location) Locations.getValue());
            old.setStatus(status.getValue().toString());
            old.setColor(color.getText());
            old.setSize(size.getText());
            old.setDescription(longName.getText());

            dbWrapper.updateMedicalEquipment(old);//TODO
        }

        refresh();
        setEditFieldsVisible(false);
    }

    @FXML
    void imageClicked(MouseEvent event) {
        if(addState){
            //Set up ID, coordinates, etc
            int nextID = 0;
            //Generate next unique ID
            while(dbWrapper.isInTableLocation(String.valueOf(nextID))){
                nextID++;
            }
            //Get coordinates in the space of the original map
            double xCord = getMapX(event.getSceneX());
            double yCord = getMapY(event.getSceneY());
            //Adds point to the map
            selectedPnt = addPoint(String.valueOf(nextID),xCord,yCord,Color.YELLOW);
            //Create new location
            Location newLoc = new Location(String.valueOf(nextID),(int)xCord,(int)yCord,currentFloor,"Building","Node Type","Long Name","Short Name");
            //Add new location to the database
            dbWrapper.addLocation(newLoc);

            selectedPoint = newLoc.getNodeID();
            modify();

            //FEATURE ON HOLD
            //editNodeDetails(newLoc);


            //Set button back to add mode
            addButton.setOpacity(1);
            addButton.setText("Add");
            //No longer adding a node
            addState = false;
        }
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

    @FXML public void add(){
        if(addState){
            addState = false;
            addButton.setOpacity(1);
            addButton.setText("Add");
        } else{
            addState = true;
            addButton.setOpacity(0.5);
            addButton.setText("Cancel Add");
        }
    }

    @FXML public void move(){
        if(moveState){
            moveState = false;
            moveButton.setText("Move");
        } else{
            close();
            moveState = true;
            moveButton.setText("Cancel");
        }
    }

    @FXML
    void loadFromCSV(ActionEvent event) {
//        try {
//            backupper.Restore();
//            refresh();
//        } catch (IOException ex){
//            ex.printStackTrace();
//        }
    }


    //credit to https://www.baeldung.com/java-distance-between-two-points
    public double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public Location getClosetLocation(double x, double y){
        Circle closest = new Circle();
        double distance = 5000.0;
        for (Node child : anchorPane.getChildren()) {
            if (child instanceof Circle) {
                double dist = calculateDistanceBetweenPoints(((Circle) child).getCenterX(),((Circle) child).getCenterY(),x,y);
                if(dist < distance){
                    closest = (Circle)child;
                    distance = dist;
                }
            }
        }
        return(dbWrapper.getLocation(closest.getId()));
    }

    void updatePopup(){
        Translate trans = new Translate();
        Bounds bounds = modifyPopup.localToScreen(modifyPopup.getBoundsInLocal());
        if(clicked == "location") {
            trans.setX(selectedPnt.getCenterX() - bounds.getMinX());
            trans.setY(selectedPnt.getCenterY() - (bounds.getMinY() - 20));
        } else if (clicked == "equipment"){
            trans.setX(selectedImg.getX() - bounds.getMinX() + 10);
            trans.setY(selectedImg.getY() - (bounds.getMinY() - 30));
        }
        modifyPopup.getTransforms().add(trans);
    }





}

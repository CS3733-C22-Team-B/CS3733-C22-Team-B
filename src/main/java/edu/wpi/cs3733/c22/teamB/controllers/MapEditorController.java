package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.*;
import edu.wpi.cs3733.c22.teamB.entity.inheritance.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import edu.wpi.cs3733.c22.teamB.entity.objects.MedicalEquipment;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

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
    public Label summary1Location;
    public Label summary2Location;
    public Label summary3Location;
    public Label summaryL1Location;
    public Label summaryL2Location;
    public Label summary1Equipment;
    public Label summary2Equipment;
    public Label summary3Equipment;
    public Label summaryL2Equipment;
    public Label summaryL1Equipment;
    public Label summary1SR;
    public Label summary2SR;
    public Label summary3SR;
    public Label summaryL2SR;
    public Label summaryL1SR;

    String selectedPoint;
    Circle selectedPnt;
    ImageView selectedImg;
    double sceneWidth;
    double sceneHeight;
    double imageHeight;
    double imageWidth;
    Point2D orgNodePoint;
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
    Image sideViewImage = new Image("/edu/wpi/cs3733/c22/teamB/images/SideView.png");
    Image medical = new Image("/edu/wpi/cs3733/c22/teamB/images/medical.png");
    Image clipboard = new Image("/edu/wpi/cs3733/c22/teamB/images/clipboard.png");
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;
    CoordTransformer coordTrans;

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
    @FXML private JFXButton goToSideViewButton;


    @FXML
    public void initialize(){
        Bapp.getPrimaryStage().setMaximized(true);
//        Bapp.getPrimaryStage().resizableProperty().set(false);
        sceneWidth = Bapp.getPrimaryStage().getScene().getWidth();
        sceneHeight = Bapp.getPrimaryStage().getScene().getHeight();
        imageView.setFitHeight(sceneHeight);
        coordTrans = new CoordTransformer(imageView);
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
        status.getItems().addAll("DONE","CANCELLED","IN PROGRESS","WAITING");
        floor.setValue(currentFloor);
        addPoint("1",0,0,Color.ORANGE);
        addPoint("2",imageWidth,imageHeight, Color.RED);
        addPoints();
        modifyPopup.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0,12,12,12,false), javafx.geometry.Insets.EMPTY)));
        modifyPopup.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, new CornerRadii(0,12,12,12,false),new BorderWidths(1), Insets.EMPTY)));
        modifyPopup.setVisible(false);
        setTextVisible(false);
        //updateText(summary3Location,getImageX(1000),getImageX(877));
        setTextPos();
        modifyPopup.setStyle("-fx-padding: 1;");
        //1000
        //877
        double width = imageView.getImage().getWidth();
        double height = imageView.getImage().getHeight();
        imageView.setPreserveRatio(true);
        ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();

        imageView.setOnMousePressed(e -> {

            Point2D mousePress = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));
            mouseDown.set(mousePress);
        });

        imageView.setOnMouseDragged(e -> {
            Point2D dragPoint = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));
            shift(imageView, dragPoint.subtract(mouseDown.get()));
            mouseDown.set(imageViewToImage(imageView, new Point2D(e.getX(), e.getY())));
        });

        imageView.setOnScroll(e -> {
            double delta = e.getDeltaY();
            Rectangle2D viewport = imageView.getViewport();

            double scale = clamp(Math.pow(1.01, delta),

                    // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
                    Math.min(10 / viewport.getWidth(), 10 / viewport.getHeight()),

                    // don't scale so that we're bigger than image dimensions:
                    Math.max(width / viewport.getWidth(), height / viewport.getHeight())

            );

            Point2D mouse = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));

            double newWidth = viewport.getWidth() * scale;
            double newHeight = viewport.getHeight() * scale;

            // To keep the visual point under the mouse from moving, we need
            // (x - newViewportMinX) / (x - currentViewportMinX) = scale
            // where x is the mouse X coordinate in the image

            // solving this for newViewportMinX gives

            // newViewportMinX = x - (x - currentViewportMinX) * scale

            // we then clamp this value so the image never scrolls out
            // of the imageview:

            double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale,
                    0, width - newWidth);
            double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale,
                    0, height - newHeight);

            imageView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
        });
    }
    private double clamp(double value, double min, double max) {

        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }
    // shift the viewport of the imageView by the specified delta, clamping so
    // the viewport does not move off the actual image:
    private void shift(ImageView imageView, Point2D delta) {
        Rectangle2D viewport = imageView.getViewport();

        double width = imageView.getImage().getWidth() ;
        double height = imageView.getImage().getHeight() ;

        double maxX = width - viewport.getWidth();
        double maxY = height - viewport.getHeight();

        double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
        double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);

        imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
    }

    // convert mouse coordinates in the imageView to coordinates in the actual image:
    private Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();

        Rectangle2D viewport = imageView.getViewport();
        return new Point2D(
                viewport.getMinX() + xProportion * viewport.getWidth(),
                viewport.getMinY() + yProportion * viewport.getHeight());
    }

    public void setTextPos(){
        summaryL1Location.setTranslateX(getImageX(300));
        summaryL1Location.setTranslateY(getImageY(-175));
        summaryL2Location.setTranslateX(getImageX(300));
        summaryL2Location.setTranslateY(getImageY(-325));
        summary1Location.setTranslateX(getImageX(300));
        summary1Location.setTranslateY(getImageY(-25));
        summary2Location.setTranslateX(getImageX(300));
        summary2Location.setTranslateY(getImageY(125));
        summary3Location.setTranslateX(getImageX(300));
        summary3Location.setTranslateY(getImageY(275));

        summaryL2Equipment.setTranslateX(getImageX(400));
        summaryL2Equipment.setTranslateY(getImageY(-175));
        summaryL1Equipment.setTranslateX(getImageX(400));
        summaryL1Equipment.setTranslateY(getImageY(-325));
        summary1Equipment.setTranslateX(getImageX(400));
        summary1Equipment.setTranslateY(getImageY(-25));
        summary2Equipment.setTranslateX(getImageX(400));
        summary2Equipment.setTranslateY(getImageY(125));
        summary3Equipment.setTranslateX(getImageX(400));
        summary3Equipment.setTranslateY(getImageY(275));

        summaryL2SR.setTranslateX(getImageX(500));
        summaryL2SR.setTranslateY(getImageY(-175));
        summaryL1SR.setTranslateX(getImageX(500));
        summaryL1SR.setTranslateY(getImageY(-325));
        summary1SR.setTranslateX(getImageX(500));
        summary1SR.setTranslateY(getImageY(-25));
        summary2SR.setTranslateX(getImageX(500));
        summary2SR.setTranslateY(getImageY(125));
        summary3SR.setTranslateX(getImageX(500));
        summary3SR.setTranslateY(getImageY(275));


    }

    //Add points from DB
    public void addPoints(){
        for (Location local : locationList) {
            if (local.getFloor().equals(currentFloor)) {
                String ID = local.getNodeID();
                double imageX = local.getXcoord();
                double imageY = local.getYcoord();
                addPoint(ID, imageX, imageY, Color.BLACK);
            }
        }


        for (MedicalEquipment local : medicalList) {
            if (local.getLocation().getFloor().equals(currentFloor)) {
                String ID = local.getEquipmentID();
                double imageX = local.getLocation().getXcoord(); //TODO fix for future iterations
                double imageY = local.getLocation().getYcoord();
                addPointMedical(ID, imageX, imageY, Color.BLUE);
            }
        }

        for (AbstractSR local : srList) {
            if (local.getLocation().getFloor().equals(currentFloor)) {
                String ID = local.getSrID();
                double imageX = local.getLocation().getXcoord() + 20; //TODO fix for future iterations
                double imageY = local.getLocation().getYcoord();
                addPointSR(ID, imageX, imageY, Color.LIME);
            }
        }


    }

    //Add a point to the map using image coordinates. Set up onclick.
    public Circle addPoint(String ID, double imageX, double imageY, Color color){
        //Create the point
        Point2D nodeCoords = coordTrans.imageToNode(imageX,imageY);
        Circle testPoint = new Circle(nodeCoords.getX(),nodeCoords.getY(), 3);
        //Add the point to the anchorPane's children
        if(showLocations.isSelected()) {
            anchorPane.getChildren().add(testPoint);
            testPoint.setFill(color);
            //Set point ID
            testPoint.idProperty().set(ID);
            //Set up onclick events
            testPoint.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    orgNodePoint = coordTrans.imageToNode(event);
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
                    double offsetX = t.getSceneX() - orgNodePoint.getX();
                    double offsetY = t.getSceneY() - orgNodePoint.getY();
                    System.out.println("offsetX" + offsetX);
                    Circle c = (Circle) (t.getSource());

                    c.setCenterX(c.getCenterX() + offsetX);
                    c.setCenterY(c.getCenterY() + offsetY);

                    orgNodePoint = coordTrans.imageToNode(t);
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
                    orgNodePoint = coordTrans.imageToNode(event);
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
                    Point2D nodeCoords = coordTrans.imageToNode(t);
                    double nodeXOffset = nodeCoords.getX() - orgNodePoint.getX();
                    double nodeYOffset = nodeCoords.getY() - orgNodePoint.getY();

                    ImageView c = (ImageView) (t.getSource());

                    c.setX(c.getX() + nodeXOffset);
                    c.setY(c.getY() + nodeYOffset);

                    orgNodePoint = coordTrans.imageToNode(t);
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
        if(clicked == "location") {
            anchorPane.getChildren().remove(selectedPnt);
            dbWrapper.deleteLocation(selectedPnt.getId());
        } else if (clicked == "equipment"){
            anchorPane.getChildren().remove(selectedImg);
            dbWrapper.deleteMedicalEquipment(selectedImg.getId());
        }
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

    @FXML public void goToSideView() {
        currentFloor = "side";
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
        try {
            dbWrapper.backupAll();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        refresh();
    }

    @FXML public void goTo(){
        goTo1Button.setStyle("-fx-background-color: #eaeaea");
        goTo2Button.setStyle("-fx-background-color: #eaeaea");
        goTo3Button.setStyle("-fx-background-color: #eaeaea");
        goToL1Button.setStyle("-fx-background-color: #eaeaea");
        goToL2Button.setStyle("-fx-background-color: #eaeaea");
        goToSideViewButton.setStyle("-fx-background-color: #eaeaea");
        setTextVisible(false);

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
            case"side":
                setTextVisible(true);
                setText();
                imageView.setImage(sideViewImage);
                goToSideViewButton.setStyle("-fx-background-color: #007fff");
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
            Bapp.getPrimaryStage().setWidth(725);
            Bapp.getPrimaryStage().setHeight(450);
            Bapp.getPrimaryStage().resizableProperty().set(true);
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/borderHome.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
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
        try {
            dbWrapper.restoreAll();
            refresh();
        } catch (IOException ex){
            ex.printStackTrace();
        }
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

    int locationCount(String floor){
        locationList = dbWrapper.getAllLocation();
        int count = 0;
        for (Location local : locationList){
            if (local.getFloor().equals(floor)) count++;
        }
        return count;
    }

    int equipmentCount(String floor){
        medicalList = dbWrapper.getAllMedicalEquipment();
        int count = 0;
        for (MedicalEquipment equip : medicalList){
            if (equip.getLocation().getFloor().equals(floor)) count++;
        }
        return count;
    }

    int SRCount(String floor){
        srList = dbWrapper.getAllSR();
        int count = 0;
        for (AbstractSR sr : srList){
            if (sr.getLocation().getFloor().equals(floor)) count++;
        }
        return count;
    }

    public void setTextVisible(boolean isVisible){
        summary1Location.setVisible(isVisible);
        summary2Location.setVisible(isVisible);
        summary3Location.setVisible(isVisible);
        summaryL1Location.setVisible(isVisible);
        summaryL2Location.setVisible(isVisible);
        summary1Equipment.setVisible(isVisible);
        summary2Equipment.setVisible(isVisible);
        summary3Equipment.setVisible(isVisible);
        summaryL1Equipment.setVisible(isVisible);
        summaryL2Equipment.setVisible(isVisible);
        summary1SR.setVisible(isVisible);
        summary2SR.setVisible(isVisible);
        summary3SR.setVisible(isVisible);
        summaryL1SR.setVisible(isVisible);
        summaryL2SR.setVisible(isVisible);
    }

    public void setText(){
        summaryL2Location.setText(String.valueOf(locationCount("L2")));
        summaryL1Location.setText(String.valueOf(locationCount("L1")));
        summary1Location.setText(String.valueOf(locationCount("01")));
        summary2Location.setText(String.valueOf(locationCount("02")));
        summary3Location.setText(String.valueOf(locationCount("03")));
        summaryL2Equipment.setText(String.valueOf(equipmentCount("L2")));
        summaryL1Equipment.setText(String.valueOf(equipmentCount("L1")));
        summary1Equipment.setText(String.valueOf(equipmentCount("01")));
        summary2Equipment.setText(String.valueOf(equipmentCount("02")));
        summary3Equipment.setText(String.valueOf(equipmentCount("03")));
        summaryL2SR.setText(String.valueOf(SRCount("L2")));
        summaryL1SR.setText(String.valueOf(SRCount("L1")));
        summary1SR.setText(String.valueOf(SRCount("01")));
        summary2SR.setText(String.valueOf(SRCount("02")));
        summary3SR.setText(String.valueOf(SRCount("03")));
    }




}
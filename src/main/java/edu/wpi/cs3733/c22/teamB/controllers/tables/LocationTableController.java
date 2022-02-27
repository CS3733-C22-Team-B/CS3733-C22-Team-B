package edu.wpi.cs3733.c22.teamB.controllers.tables;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.controllers.AbsPage;
import edu.wpi.cs3733.c22.teamB.controllers.AnchorHomeController;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.objects.Location;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class LocationTableController extends AbsPage {

    public JFXButton filterSubmitButton;
    public TextField textFilterField;
    public ContextMenu filterMenu;
    @FXML private GridPane gridPane;
    @FXML private JFXButton confirmButton;
    @FXML private TextField ycoordField;
    @FXML private TextField xcoordField;
    @FXML private TextField floorField;
    @FXML private TextField nodeTypeField;
    @FXML private TextField longNameField;
    @FXML private TextField shortNameField;
    @FXML private JFXButton addButton;
    @FXML private JFXButton deleteButton;
    @FXML private TableView<Location> table;
    @FXML private JFXButton loadButton;
    @FXML private Pane popup;
    @FXML private Pane contentPane;
    @FXML private AnchorPane anchorPane;
    private Set<String> filterFields = new HashSet<>();

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.setPageName("Location Table");
    }


    private enum Function {ADD, MODIFY, DELETE, NOTHING};
    Function func = Function.NOTHING;

    private boolean initTable = false;
    List<Location> listOfLocations;

    public LocationTableController() {}

    DatabaseWrapper db = DatabaseWrapper.getInstance();

    @FXML
    private void initialize() throws NullPointerException {
        deleteButton.setDisable(true);
        popup.setVisible(false);

        gridPane.setVisible(false);
        gridPane.setDisable(true);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                deleteButton.setDisable(false);
            }
        });

        loadTable();
        initResize();
        resize();
        namePage();

        popup.setLayoutX(Bapp.getPrimaryStage().getWidth()/2.5);
        popup.setLayoutY(Bapp.getPrimaryStage().getHeight()/2.5);

        filterFields.addAll(List.of(new String[]{"nodeID", "xcoord", "ycoord", "floor", "building", "nodeType", "longName", "shortName"}));
        textFilterField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                filterSubmit(null);
            }
        });

    }

    @FXML
    private void loadTable() throws NullPointerException {
        if (!initTable) {
            initTable = true;

            TableColumn<Location, String> col1 = new TableColumn<>("nodeID"); // column names
            TableColumn<Location, String> col2 = new TableColumn<>("xcoord");
            TableColumn<Location, String> col3 = new TableColumn<>("ycoord");
            TableColumn<Location, String> col4 = new TableColumn<>("floor");
            TableColumn<Location, String> col5 = new TableColumn<>("building"); // column names
            TableColumn<Location, String> col6 = new TableColumn<>("nodeType");
            TableColumn<Location, String> col7 = new TableColumn<>("longName");
            TableColumn<Location, String> col8 = new TableColumn<>("shortName");

            col1.setCellValueFactory(new PropertyValueFactory<>("nodeID")); // MedicalEquipmentSR fields
            col2.setCellValueFactory(new PropertyValueFactory<>("xcoord"));
            col3.setCellValueFactory(new PropertyValueFactory<>("ycoord"));
            col4.setCellValueFactory(new PropertyValueFactory<>("floor"));
            col5.setCellValueFactory(new PropertyValueFactory<>("building")); // MedicalEquipmentSR fields
            col6.setCellValueFactory(new PropertyValueFactory<>("nodeType"));
            col7.setCellValueFactory(new PropertyValueFactory<>("longName"));
            col8.setCellValueFactory(new PropertyValueFactory<>("shortName"));

            table.getColumns().add(col1); // adding columns to setup table
            table.getColumns().add(col2);
            table.getColumns().add(col3);
            table.getColumns().add(col4);
            table.getColumns().add(col5);
            table.getColumns().add(col6);
            table.getColumns().add(col7);
            table.getColumns().add(col8);

            table.setEditable(true);
        }
        table.getItems().clear();
        listOfLocations = db.getAllLocation();
        table.getItems().addAll(listOfLocations); // create and add object
    }

    @FXML
    void goToHome(ActionEvent event) {
        // Try to go home
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/Home.fxml"));
            Bapp.getPrimaryStage().getScene().setRoot(root);
            // Print stack trace if unable to go home
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void addLocation(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        xcoordField.setVisible(true);
        ycoordField.setVisible(true);
        floorField.setVisible(true);
        nodeTypeField.setVisible(true);
        longNameField.setVisible(true);
        shortNameField.setVisible(true);
        xcoordField.setDisable(false);
        ycoordField.setDisable(false);
        floorField.setDisable(false);
        nodeTypeField.setDisable(false);
        longNameField.setDisable(false);
        shortNameField.setDisable(false);
        func = Function.ADD;
        clearForm(null);
    }

    @FXML
    private void modifyLocation(ActionEvent actionEvent) {
        gridPane.setVisible(true);
        gridPane.setDisable(false);
        xcoordField.setVisible(true);
        ycoordField.setVisible(true);
        floorField.setVisible(true);
        nodeTypeField.setVisible(true);
        longNameField.setVisible(true);
        shortNameField.setVisible(true);
        xcoordField.setDisable(false);
        ycoordField.setDisable(false);
        floorField.setDisable(false);
        nodeTypeField.setDisable(false);
        longNameField.setDisable(false);
        shortNameField.setDisable(false);

        Location loc = table.getSelectionModel().getSelectedItem();
        xcoordField.setText(Integer.toString(loc.getXcoord()));
        ycoordField.setText(Integer.toString(loc.getYcoord()));
        floorField.setText(loc.getFloor());
        nodeTypeField.setText(loc.getNodeType());
        longNameField.setText(loc.getLongName());
        shortNameField.setText(loc.getShortName());

        func = Function.MODIFY;
    }

    @FXML
    private void deleteLocation(ActionEvent actionEvent) {
        db.deleteLocation(table.getSelectionModel().getSelectedItem().getNodeID());
        loadTable();
        cancelForm(null);

        // submitted confirmation popup
        popup.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(1)
        );
        visiblePause.setOnFinished(
                event -> popup.setVisible(false)
        );
        visiblePause.play();
    }

    @FXML private void locationTableClick(MouseEvent mouseEvent) {
        deleteButton.setVisible(true);

        if (table.getSelectionModel().getSelectedItem().getNodeID() != null){
            modifyLocation(null);
        }
    }

    @FXML private void confirm(ActionEvent actionEvent) {
        if(func == Function.ADD) {
            Location w = new Location(
                    Integer.parseInt(xcoordField.getText()),
                    Integer.parseInt(ycoordField.getText()),
                    floorField.getText(),
                    "Tower",
                    nodeTypeField.getText(),
                    longNameField.getText(),
                    shortNameField.getText());
            db.addLocation((w));
            loadTable();

            // submitted confirmation popup
            popup.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(1)
            );
            visiblePause.setOnFinished(
                    event -> popup.setVisible(false)
            );
            visiblePause.play();

            clearForm(actionEvent);

        } else if (func == Function.MODIFY) {
            Location n = new Location(
                    table.getSelectionModel().getSelectedItem().getNodeID(),
                    Integer.parseInt(xcoordField.getText()),
                    Integer.parseInt(ycoordField.getText()),
                    floorField.getText(),
                    "Tower",
                    nodeTypeField.getText(),
                    longNameField.getText(),
                    shortNameField.getText());
            db.updateLocation(n);
            loadTable();

            // submitted confirmation popup
            popup.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(1)
            );
            visiblePause.setOnFinished(
                    event -> popup.setVisible(false)
            );
            visiblePause.play();

            cancelForm(actionEvent);
        }
    }

    @FXML private void clearForm(ActionEvent actionEvent) {
        xcoordField.clear();
        ycoordField.clear();
        floorField.clear();
        nodeTypeField.clear();
        longNameField.clear();
        shortNameField.clear();
    }

    @FXML private void cancelForm(ActionEvent actionEvent) {
        gridPane.setDisable(true);
        gridPane.setVisible(false);
        clearForm(actionEvent);

        addButton.setVisible(true);
        addButton.setDisable(false);

        deleteButton.setVisible(true);
        deleteButton.setDisable(false);

        func = Function.NOTHING;
    }
    @Override
    public void initResize() {
        contentPane.setLayoutX(Bapp.getPrimaryStage().getWidth()/8);
        contentPane.setLayoutY(Bapp.getPrimaryStage().getHeight()/12);
        anchorPane.setPrefWidth(Bapp.getPrimaryStage().getWidth() - AnchorHomeController.curAnchorHomeController.sidebar.getWidth());
        anchorPane.setPrefHeight(Bapp.getPrimaryStage().getHeight() - AnchorHomeController.curAnchorHomeController.sidebar.getHeight());
    }

    @FXML
    private void filterSubmit(ActionEvent actionEvent) {
        table.getItems().clear();
        table.getItems().removeAll();
        table.getItems().addAll(db.getAllLocation().stream().filter(sr -> {
            String input = textFilterField.getText().toLowerCase(Locale.ROOT);
            return  (filterFields.contains("nodeID") && sr.getNodeID().toLowerCase(Locale.ROOT).contains(input)) || //||
                    (filterFields.contains("xcoord") && String.valueOf(sr.getXcoord()).contains(input))||
                    (filterFields.contains("ycoord") && String.valueOf(sr.getYcoord()).contains(input)) ||
                    (filterFields.contains("floor") && sr.getFloor().toLowerCase(Locale.ROOT).contains(input)) ||
                    (filterFields.contains("building") && sr.getBuilding().toLowerCase(Locale.ROOT).contains(input)) ||
                    (filterFields.contains("nodeType") && sr.getNodeType().toLowerCase(Locale.ROOT).contains(input)) ||
                    (filterFields.contains("longName") && sr.getLongName().toLowerCase(Locale.ROOT).contains(input)) ||
                    (filterFields.contains("shortName") && sr.getShortName().toLowerCase(Locale.ROOT).contains(input));
        }).collect(Collectors.toList()));
    }
}

//    TableColumn<Location, String> col1 = new TableColumn<>("nodeID"); // column names
//    TableColumn<Location, String> col2 = new TableColumn<>("xcoord");
//    TableColumn<Location, String> col3 = new TableColumn<>("ycoord");
//    TableColumn<Location, String> col4 = new TableColumn<>("floor");
//    TableColumn<Location, String> col5 = new TableColumn<>("building"); // column names
//    TableColumn<Location, String> col6 = new TableColumn<>("nodeType");
//    TableColumn<Location, String> col7 = new TableColumn<>("longName");
//    TableColumn<Location, String> col8 = new TableColumn<>("shortName");
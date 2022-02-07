package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.LocationDBI;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class LocationTableController {

    @FXML private JFXButton addButton;
    @FXML private JFXButton modifyButton;
    @FXML private JFXButton deleteButton;
    @FXML private TableView table;
    @FXML private JFXButton loadButton;

    public LocationTableController() {}

    @FXML
    private void initialize() throws NullPointerException {
        addButton.setDisable(false);
        modifyButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    @FXML
    private void loadTable() throws NullPointerException {
        LocationDBI locationDBI = new LocationDBI();
        List<Location> listOfLocations = locationDBI.getAllNodes();

        table.setEditable(true);
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

    }

    @FXML
    private void modifyLocation(ActionEvent actionEvent) {
    }

    @FXML
    private void deleteLocation(ActionEvent actionEvent) {
    }

    public void locationTableClick(MouseEvent mouseEvent) {
        table.getSelectionModel().getSelectedItem();
    }
}

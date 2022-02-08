package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.Location;
import edu.wpi.cs3733.c22.teamB.entity.MedicalEquipment;
import edu.wpi.cs3733.c22.teamB.entity.MedicalEquipmentDBI;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MedicalEquipmentTableController {

    @FXML TableView table;
    @FXML JFXButton loadButton;

    public MedicalEquipmentTableController() {}

    @FXML
    private void initialize() throws NullPointerException {}

    public void loadTable() throws NullPointerException {
        MedicalEquipmentDBI medicalEquipmentDBI = new MedicalEquipmentDBI();
        List<MedicalEquipment> listMedicalEquipment = medicalEquipmentDBI.getAllNodes();

        table.setEditable(true);
        TableColumn<Location, String> col1 = new TableColumn<>("equipmentID"); // column names
        TableColumn<Location, String> col2 = new TableColumn<>("equipmentName");
        TableColumn<Location, String> col3 = new TableColumn<>("equipmentType");
        TableColumn<Location, String> col4 = new TableColumn<>("manufacturer");
        TableColumn<Location, Location> col5 = new TableColumn<>("location");
        TableColumn<Location, String> col6 = new TableColumn<>("status"); // column names
        TableColumn<Location, String> col7 = new TableColumn<>("color");
        TableColumn<Location, String> col8 = new TableColumn<>("size");
        TableColumn<Location, String> col9 = new TableColumn<>("description");

        col1.setCellValueFactory(
                new PropertyValueFactory<>("equipmentID")); // MedicalEquipmentSR fields
        col2.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        col3.setCellValueFactory(new PropertyValueFactory<>("equipmentType"));
        col4.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        col5.setCellValueFactory(new PropertyValueFactory<>("location"));
        col6.setCellValueFactory(new PropertyValueFactory<>("status"));
        col7.setCellValueFactory(new PropertyValueFactory<>("color"));
        col8.setCellValueFactory(new PropertyValueFactory<>("size"));
        col9.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().add(col1); // adding columns to setup table
        table.getColumns().add(col2);
        table.getColumns().add(col3);
        table.getColumns().add(col4);
        table.getColumns().add(col5);
        table.getColumns().add(col6);
        table.getColumns().add(col7);
        table.getColumns().add(col8);
        table.getColumns().add(col9);

        table.getItems().addAll(listMedicalEquipment);
        // table.getItems().addAll(new MedicalEquipment("15", "jeff", "jeffery", "jeff himself", new
        // Location("3", 3 , 4, "floor 3", "This building", " idkman", "longname", "shortname"),
        // "Done","blue","large", "funn"));

        // table.getItems().addAll(--list of objects here--); //

    }
    // Go to the home fxml when the home button is pressed
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
}

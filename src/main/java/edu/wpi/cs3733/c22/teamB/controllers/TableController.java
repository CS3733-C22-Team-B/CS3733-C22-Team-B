package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class TableController extends AbsPage {
    @FXML
    Pane anchorPane;

    @FXML
    Pane contentPane;

    @FXML
    private void initialize() {
    initResize();
    resize();
    namePage();
    }
    public void goToEquipmentTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/tables/MedicalEquipmentTable.fxml"));
            AnchorHomeController.curAnchorHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToLocationTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/tables/LocationTable.fxml"));
            AnchorHomeController.curAnchorHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToEmployeeTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/tables/EmployeeTable.fxml"));
            AnchorHomeController.curAnchorHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToSRTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/ServiceRequestManager.fxml"));
            AnchorHomeController.curAnchorHomeController.changeNode(loader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.setPageName("Tables");
    }
}

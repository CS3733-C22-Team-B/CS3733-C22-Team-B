package edu.wpi.cs3733.c22.teamB.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TableController {
    public void goToEquipmentTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/MedicalEquipmentTable.fxml"));
            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToLocationTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/LocationTable.fxml"));
            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToEmployeeTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/EmployeeTable.fxml"));
            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

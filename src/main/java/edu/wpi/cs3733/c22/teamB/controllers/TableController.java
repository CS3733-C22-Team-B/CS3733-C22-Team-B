package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.Bapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class TableController implements IPage {
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
            BorderHomeController.curBorderHomeController.changeNode(loader);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void goToLocationTable(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/wpi/cs3733/c22/teamB/views/tables/LocationTable.fxml"));
            BorderHomeController.curBorderHomeController.changeNode(loader);

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

    private void initResize() {
        contentPane.setLayoutX(Bapp.getPrimaryStage().getWidth()/4);
        contentPane.setLayoutY(Bapp.getPrimaryStage().getHeight()/6);
        anchorPane.setPrefWidth(Bapp.getPrimaryStage().getWidth() - 50);
        anchorPane.setPrefHeight(Bapp.getPrimaryStage().getHeight() - 50);
    }

    @Override
    public void resize() {
        Bapp.getPrimaryStage().heightProperty().addListener((observable)-> {
            anchorPane.setPrefWidth(Bapp.getPrimaryStage().getWidth()-50 );
            anchorPane.setPrefHeight(Bapp.getPrimaryStage().getHeight()- 50);
            contentPane.setLayoutX(Bapp.getPrimaryStage().getWidth()/4);
            contentPane.setLayoutY(Bapp.getPrimaryStage().getHeight()/6);
        });
    }

    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.setPageName("Tables");
    }
}

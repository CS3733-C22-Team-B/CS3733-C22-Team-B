package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable, IPage{
    @FXML
    JFXButton HomeB;
    @FXML
    JFXButton RestoreB;
    @FXML
    JFXButton BackupB;
    @FXML
    JFXToggleButton clientServerToggle;
    @FXML
    Pane anchorPane;
    @FXML
    Pane contentPane;



    DatabaseWrapper db;

    public SettingsController() throws SQLException {
        this.db = new DatabaseWrapper();
    }

    public void Backup() throws IOException {
        db.backupAll();

    }

    public void Restore() throws IOException {
        //call DB restore here
        db.restoreAll();

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

    public void onClientToggle(ActionEvent actionEvent) throws IOException {
        if (clientServerToggle.isSelected()) {
            db.backupAll();
            db.initClient();
            db.restoreAll();

        } else {
            db.backupAll();
            db.initEmbedded();
            db.restoreAll();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (db.getConnection() instanceof org.apache.derby.client.net.NetConnection) {
            clientServerToggle.setSelected(true);
        } else {
            clientServerToggle.setSelected(false);
        }

        initResize();
        resize();
        namePage();
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
        AnchorHomeController.curAnchorHomeController.pageName.setText("Settings");
    }
}

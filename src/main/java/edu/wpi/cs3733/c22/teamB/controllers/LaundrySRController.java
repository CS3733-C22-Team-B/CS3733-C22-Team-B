package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXListView;
import edu.wpi.cs3733.c22.teamB.entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LaundrySRController implements IController, Initializable {

    private LaundrySR sr = null;

    public LaundrySRController(){}

    public LaundrySRController(LaundrySR sr){
        this.sr = sr;
    }

    @FXML
    private void initialize() {
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void submit() {
    }

    @Override
    public void submit(AbstractSR sr) {
    DatabaseWrapper dw = new DatabaseWrapper();
    dw.addSR(new LaundrySR(sr));
    }

    @Override
    public void clear() {
    }
}

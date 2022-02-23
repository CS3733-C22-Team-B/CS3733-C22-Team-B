package edu.wpi.cs3733.c22.teamB.controllers;

import javafx.fxml.FXML;

public class AboutPageController extends AbsPage{
    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("About Page");
    }

    @FXML
    private void initialize() {
        initResize();
        resize();
        namePage();
    }

}

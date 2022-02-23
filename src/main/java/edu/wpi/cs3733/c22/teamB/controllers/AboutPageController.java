package edu.wpi.cs3733.c22.teamB.controllers;

public class AboutPageController extends AbsPage{
    @Override
    public void namePage() {
        AnchorHomeController.curAnchorHomeController.pageName.setText("About Page");
    }
}

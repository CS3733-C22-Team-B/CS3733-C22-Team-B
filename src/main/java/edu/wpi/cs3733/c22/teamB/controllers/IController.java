package edu.wpi.cs3733.c22.teamB.controllers;

import java.net.URL;
import java.util.ResourceBundle;

public interface IController {
    void initialize(URL location, ResourceBundle resources);

    public void submit();

    public void clear();
}

package edu.wpi.cs3733.c22.teamB.entity;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class SideviewTable extends TableView {

    TableColumn<Integer, Integer> locations;
    TableColumn<Integer, Integer> equipment;
    TableColumn<Integer, Integer> serviceRequests;

    public SideviewTable(){
        this.getColumns().addAll(locations,equipment,serviceRequests);
    }
}

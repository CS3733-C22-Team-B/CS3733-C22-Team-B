package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.Employee;
import edu.wpi.cs3733.c22.teamB.entity.ExternalTransportSR;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javax.xml.crypto.Data;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;
import java.util.PropertyPermission;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ServiceRequestManagerController {
    DatabaseWrapper dw = new DatabaseWrapper();

    @FXML private TableView<AbstractSR> srTable;
    @FXML private TableColumn<AbstractSR, String> idCol;
    @FXML private TableColumn<AbstractSR, String> typeCol;
    @FXML private TableColumn<AbstractSR, String> requestorCol;
    @FXML private TableColumn<AbstractSR, String> timeCol;
    @FXML private TableColumn<AbstractSR, String> employeeCol;
    @FXML private TableColumn<AbstractSR, String> statusCol;
    @FXML private TableColumn<AbstractSR, String> deleteCol;

    private List<Employee> employeeList;
    private Map<String, Employee> employeeMap;

    @FXML
    private void initialize() {
        employeeList = dw.getAllEmployee();
        employeeMap =
                IntStream.range(0, employeeList.size())
                        .boxed()
                        .collect(
                                Collectors.toMap(
                                        i ->
                                                (employeeList.get(i).getEmployeeID() + ' ' + employeeList.get(i).getName()),
                                        i -> employeeList.get(i)));

        idCol.setCellValueFactory(new PropertyValueFactory<>("srID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("srType"));
        requestorCol.setCellValueFactory(new PropertyValueFactory<>("requestor"));

        timeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AbstractSR, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AbstractSR, String> cd) {
                AbstractSR sr = cd.getValue();

                return Bindings.createStringBinding(() -> sr.getDateRequested().toString());
            }
        });

        employeeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AbstractSR, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<AbstractSR, String> cd) {
                AbstractSR sr = cd.getValue();

                return Bindings.createStringBinding(() -> sr.getAssignedEmployee().getEmployeeID() + ' ' + sr.getAssignedEmployee().getName());
            }
        });
        employeeCol.setCellFactory(tc -> {
            ComboBox<String> combo = new ComboBox<>();
            combo.getItems().addAll(employeeMap.keySet());
            TableCell<AbstractSR, String> cell = new TableCell<AbstractSR, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        combo.setValue(item);
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                AbstractSR sr = tc.getTableView().getItems().get(cell.getIndex());
                sr = dw.getSR(sr.getSrID());
                sr.setAssignedEmployee(employeeMap.get(combo.getValue()));
                dw.updateSR(sr);
            });
            return cell;
        });

        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setCellFactory(tc -> {
            ComboBox<String> combo = new ComboBox<>();
            combo.getItems().addAll(AbstractSR.SRstatus);
            TableCell<AbstractSR, String> cell = new TableCell<AbstractSR, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        combo.setValue(item);
                        setGraphic(combo);
                    }
                }
            };
            combo.setOnAction(e -> {
                AbstractSR sr = tc.getTableView().getItems().get(cell.getIndex()); //MainSR
                sr = dw.getSR(sr.getSrID()); //get actual SR object
                sr.setStatus(combo.getValue()); //update status
                dw.updateSR(sr); //??
            });
            return cell;
        });

        deleteCol.setCellFactory(new Callback<TableColumn<AbstractSR, String>, TableCell<AbstractSR, String>>() {
            @Override
            public TableCell<AbstractSR, String> call(TableColumn<AbstractSR, String> param) {
                return new TableCell<AbstractSR, String>() {
                    final Button btn = new Button("X");

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                AbstractSR sr = getTableView().getItems().get(getIndex());
                                getTableView().getItems().remove(getIndex());
                                (new DatabaseWrapper()).deleteSR(sr.getSrID());
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        });
        srTable.getItems().addAll((new DatabaseWrapper()).getAllSR());
    }
}

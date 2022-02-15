package edu.wpi.cs3733.c22.teamB.controllers;

import edu.wpi.cs3733.c22.teamB.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.Employee;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;
import java.util.Map;
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
    @FXML private MenuButton visibilityMenu;

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
        requestorCol.setCellValueFactory(cd -> {
            AbstractSR sr = cd.getValue();

            return Bindings.createStringBinding(() -> sr.getRequestor().getEmployeeID() + ' ' + sr.getRequestor().getName());
        });

        timeCol.setCellValueFactory(cd -> {
            AbstractSR sr = cd.getValue();

            return Bindings.createStringBinding(() -> sr.getDateRequested().toString());
        });

        employeeCol.setCellValueFactory(cd -> {
            AbstractSR sr = cd.getValue();

            return Bindings.createStringBinding(() -> sr.getAssignedEmployee().getEmployeeID() + ' ' + sr.getAssignedEmployee().getName());
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
                                dw.deleteSR(sr.getSrID());
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        });
        srTable.getItems().addAll(dw.getAllSR());

        // column visibility
        for (TableColumn<AbstractSR, ?> col : srTable.getColumns()) {
            if (!col.getId().equals("deleteCol")) {
                CheckMenuItem item = new CheckMenuItem(col.getText());
                item.setSelected(true);
                item.setOnAction(event -> col.setVisible(item.isSelected()));
                visibilityMenu.getItems().add(item);
            }
        }
    }

    @FXML
    private void filterSubmit(ActionEvent actionEvent) {
    }
}

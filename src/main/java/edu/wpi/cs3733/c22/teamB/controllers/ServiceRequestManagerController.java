package edu.wpi.cs3733.c22.teamB.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.sun.javafx.sg.prism.EffectFilter;
import edu.wpi.cs3733.c22.teamB.Bapp;
import edu.wpi.cs3733.c22.teamB.entity.AbstractSR;
import edu.wpi.cs3733.c22.teamB.entity.DatabaseWrapper;
import edu.wpi.cs3733.c22.teamB.entity.Employee;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.util.*;
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
    @FXML private ContextMenu filterMenu;
    @FXML private TextField textFilterField;
    @FXML private JFXDialog filterDialog;
    @FXML private JFXCheckBox idFilterCheckBox;
    @FXML private JFXCheckBox typeFilterCheckBox;
    @FXML private JFXCheckBox dateFilterCheckBox;
    @FXML private JFXCheckBox assignedToFilterCheckBox;
    @FXML private JFXCheckBox requestorFilterCheckBox;
    @FXML private JFXCheckBox statusFilterCheckBox;

    private Set<String> filterFields = new HashSet<>();

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
        // add data to table
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

        // filter dialog
        idFilterCheckBox.setOnAction(event -> {
            if (idFilterCheckBox.isSelected())
                filterFields.add("ID");
            else
                filterFields.remove("ID");
        });
        typeFilterCheckBox.setOnAction(event -> {
            if (typeFilterCheckBox.isSelected())
                filterFields.add("Type");
            else
                filterFields.remove("Type");
        });
        requestorFilterCheckBox.setOnAction(event -> {
            if (requestorFilterCheckBox.isSelected())
                filterFields.add("Requestor");
            else
                filterFields.remove("Requestor");
        });
        dateFilterCheckBox.setOnAction(event -> {
            if (dateFilterCheckBox.isSelected())
                filterFields.add("Date");
            else
                filterFields.remove("Date");
        });
        assignedToFilterCheckBox.setOnAction(event -> {
            if (assignedToFilterCheckBox.isSelected())
                filterFields.add("Assigned to");
            else
                filterFields.remove("Assigned to");
        });
        statusFilterCheckBox.setOnAction(event -> {
            if (statusFilterCheckBox.isSelected())
                filterFields.add("Status");
            else
                filterFields.remove("Status");
        });
        filterFields.addAll(List.of(new String[]{"ID", "Type", "Requestor", "Date", "Assigned to", "Status"}));

        // text filter
        for (TableColumn<AbstractSR, ?> col : srTable.getColumns()) {
            if (!col.getId().equals("deleteCol")) {
                CheckMenuItem item = new CheckMenuItem(col.getText());
                item.setSelected(true);
                item.setOnAction(event -> {
                    if (item.isSelected())
                        filterFields.add(item.getText());
                    else
                        filterFields.remove(item.getText());
                });
                filterMenu.getItems().add(item);
            }
        }
        textFilterField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println(textFilterField.getText());
            }
        });
    }

    @FXML
    private void filterSubmit(ActionEvent actionEvent) {
        srTable.getItems().clear();
        srTable.getItems().removeAll();
        srTable.getItems().addAll(dw.getAllSR().stream().filter(sr ->
            (filterFields.contains("ID") && sr.getSrID().contains(textFilterField.getText())) ||
            (filterFields.contains("Type") && sr.getSrType().contains(textFilterField.getText())) ||
            (filterFields.contains("Requestor") && sr.getRequestor().getName().contains(textFilterField.getText())) ||
            (filterFields.contains("Date") && sr.getDateRequested().toString().contains(textFilterField.getText())) ||
            (filterFields.contains("Assigned to") && sr.getAssignedEmployee().getName().contains(textFilterField.getText())) ||
            (filterFields.contains("Status") && sr.getStatus().contains(textFilterField.getText()))
        ).collect(Collectors.toList()));
    }

    @FXML
    private void onFilterByButton(ActionEvent actionEvent) {
        filterDialog.show((StackPane) Bapp._root);
    }

    public void onCloseFilterDialog(ActionEvent actionEvent) {
        filterDialog.close();
    }
}

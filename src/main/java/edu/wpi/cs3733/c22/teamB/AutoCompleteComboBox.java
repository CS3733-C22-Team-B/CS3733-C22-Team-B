package edu.wpi.cs3733.c22.teamB;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.skins.JFXComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.stream.Collectors;

public class AutoCompleteComboBox<T> {
    private JFXComboBox<T> box;
    private List<T> data;
    private int minCharsToTrigger = 3;
    private T prevSelection;


    public AutoCompleteComboBox(JFXComboBox<T> box, List<T> data) {
        this.box = box;
        this.data = data;
        init();
    }
    public AutoCompleteComboBox(JFXComboBox<T> box, List<T> data, int minCharsToTrigger) {
        this.box = box;
        this.data = data;
        this.minCharsToTrigger = minCharsToTrigger;
        init();
    }

    public void init() {
        box.setEditable(true);
        box.getItems().addAll(data);
        JFXComboBoxListViewSkin<T> boxSkin = new JFXComboBoxListViewSkin<T>(box);
        boxSkin.getPopupContent().addEventFilter(KeyEvent.ANY, (event) -> {
            if(event.getCode() == KeyCode.SPACE) {
                event.consume();
            } else if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                boxSkin.hide();
            }
        });
        box.setSkin(boxSkin);
        box.setOnAction(event -> {
            if (box.getValue() != prevSelection) {
                if (box.getValue() == null)
                    box.getSelectionModel().select(prevSelection);
                else
                    prevSelection = box.getValue();
            }
        });
        box.getEditor().setOnKeyTyped(event -> {
                if (box.getEditor().getText().length() > minCharsToTrigger) {
                    box.getItems().clear();
                    box.getItems().removeAll();
                    box.getItems().addAll(data.stream().filter(t -> {
                        String s = box.getEditor().getText().toLowerCase();
                        return t.toString().toLowerCase().contains(s);
                    }).collect(Collectors.toList()));
                    boxSkin.show();
                } else {
                    boxSkin.hide();
                }

        });
    }

}

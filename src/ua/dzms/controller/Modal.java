package ua.dzms.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

abstract class Modal implements Initializable {
    @FXML
    Button buttonOk;
    @FXML
    Button buttonCancel;
    @FXML
    Label label;
    @FXML
    TextField inputFirstName;
    @FXML
    TextField inputLastName;
    @FXML
    DatePicker inputDate;


    public void initialize(URL location, ResourceBundle resources) {
        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                onClickOk();
            }
        });
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onClickCancel();
            }
        });
    }


    abstract void onClickOk();
    void onClickCancel(){
        ((Stage) label.getScene().getWindow()).close();
    }
}

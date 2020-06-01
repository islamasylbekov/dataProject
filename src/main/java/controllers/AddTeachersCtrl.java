package controllers;

import db.DbServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import models.Teachers;

public class AddTeachersCtrl {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCencel;

    @FXML
    void onAction(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            btnSaveCliced();
        }else if (event.getSource().equals(btnCencel)){
            btnCencelCliced();
        }
    }

    private void btnCencelCliced() {
        txtAddress.clear();
        txtName.clear();
    }

    private void btnSaveCliced() {
        Teachers teachers=new Teachers();
        teachers.setName(txtName.getText());
        teachers.setAddres(txtAddress.getText());
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Вы уверены?", ButtonType.OK,ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {

            DbServices.INSTANCE.saveTeacher(teachers);
            btnCencelCliced();
        }
    }
}

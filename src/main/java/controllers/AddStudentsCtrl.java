package controllers;

import db.DbServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Course;
import models.Students;

import java.util.ArrayList;
import java.util.List;


public class AddStudentsCtrl {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;
    @FXML
    private ComboBox<Course> comboCours;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCencel;

    @FXML
    public void onAction(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            btnSaveCliced();
        }else if (event.getSource().equals(btnCencel)){
            btnCencelCliced();
        }

    }

    private void btnSaveCliced() {
        Students students=new Students();
        students.setName(txtName.getText());
        students.setAddress(txtAddress.getText());
        List<Course>coursesList=new ArrayList<>();
        coursesList.add(comboCours.getValue());
        students.setCoursesID(coursesList);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Вы уверены?", ButtonType.OK,ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {

           DbServices.INSTANCE.saveStudents(students);
           btnCencelCliced();
        }
    }

    private void btnCencelCliced() {
        txtAddress.clear();
        txtName.clear();
        comboCours.setValue(null);
    }
}

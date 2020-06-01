package controllers;

import db.DbServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Course;
import models.Teachers;

public class AddCoursesCtrl {
    int num=1;
    @FXML
    private TextField txtName;

    @FXML
    private DatePicker datePicStart;

    @FXML
    private ComboBox<Teachers> comboTeacher;

    @FXML
    private CheckBox chekBoxActive;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCencel;


    public void onAction(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            btnSaveCliced();
        }else if (event.getSource().equals(btnCencel)){
            btnCencelCliced();
        }

    }

    private void btnCencelCliced() {
        txtName.clear();
        datePicStart.setValue(null);
        System.out.println(comboTeacher.getValue());
        comboTeacher.setValue(null);
    }

    private void btnSaveCliced() {
        Course courses=new Course();
        courses.setName(txtName.getText());
        courses.setTeachersId(comboTeacher.getValue());
        int active = chekBoxActive.isSelected()? 1 : 0;
        courses.setActive(active);
        courses.setDayStart(datePicStart.getValue());
        courses.setNum(num++);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Вы уверены?", ButtonType.OK,ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            DbServices.INSTANCE.saveCourses(courses);
            btnCencelCliced();
        }
    }
    @FXML
    void initialize() {
        comboTeacher.setItems(FXCollections.observableList(DbServices.INSTANCE.getTeachers()));
    }
}

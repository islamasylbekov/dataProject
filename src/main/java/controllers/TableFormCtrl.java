package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import db.DbServices;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Course;
import models.Teachers;


public class TableFormCtrl {
    Stage stage=new Stage();
    Integer num=1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem mnItemAddCours;

    @FXML
    private MenuItem mnItemStudent;

    @FXML
    private MenuItem mnItemTeacher;

    @FXML
    private TableView<Course> tbCours;

    @FXML
    private TableColumn<Course, Integer> colmCoursNum;

    @FXML
    private TableColumn<Course, String> colmCoursName;

    @FXML
    private TableColumn<Course, Date> colmCoursDayStart;

    @FXML
    private TableColumn<Course, Teachers> colmCoursTeacher;

    @FXML
    void mnItemOnAtion(ActionEvent event) {
        if (event.getSource().equals(mnItemAddCours)){
            addCoursMnItemCliced();
        }else if (event.getSource().equals(mnItemStudent)){
            addStudentMnItemCliced();
        }else if (event.getSource().equals(mnItemTeacher)){
            addTeacherMnItemCliced();
        }
    }

    private void addTeacherMnItemCliced() {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("../addTeachers.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addStudentMnItemCliced() {
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("../addStudents.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addCoursMnItemCliced() {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("../addCourses.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        colmCoursDayStart.setCellValueFactory(new PropertyValueFactory<>("dayStart"));
        colmCoursName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmCoursNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        colmCoursTeacher.setCellValueFactory(new PropertyValueFactory<>("teachersId"));
        tbCours.setRowFactory(param -> new TableRow<Course>(){
            @Override
            protected void updateItem(Course item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null){
                    setStyle("-fx-control-inner-background: derive(-fx-base,80%);");
                }else{
                    if (item.getActive()== 0){
                        setStyle("-fx-control-inner-background: lightgrey");
                    }else{
                        setStyle("-fx-control-inner-background: derive(-fx-base,80%);");
                    }
                }
            }
        });
        refresh();
    }
    private void refresh(){
        List<Course>coursesList=DbServices.INSTANCE.getCourses();
        coursesList.stream().forEach(x->x.setNum(num++));
        tbCours.setItems(FXCollections.observableList(coursesList));
    }
}

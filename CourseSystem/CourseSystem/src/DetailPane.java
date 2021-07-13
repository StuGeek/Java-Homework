/*
DetailPane.java:学生登陆成功后展示的页面
(Controller)
1.学生登陆后可以获取自己的已选课表和未选课表;
2.通过课表页面可以进行选课退课操作;
3.可以查看自己的信息;
 */

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.TableView;
import javafx.collections.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class DetailPane extends TabPane {
    private Service service;
    private Stage thisStage;
    private Student student;
    private ArrayList<Course> courses;
    private ArrayList<SelectedCourse> selectedCourse;
    private ArrayList<Course> selectableCourse = new ArrayList<>();
    private ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
    private ObservableList<Course> selectedCourseObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView coursetable;
    @FXML
    private TableView selectedcoursetable;
    @FXML
    private Label id_show;
    @FXML
    private Label name_show;
    @FXML
    private Label department_show;



    public DetailPane(Student t_student){
        student = t_student;
        service = Service.getInstance();
        thisStage = new Stage();
        courses = service.getAllCourseByDepartment(student.getDepartment());
        selectedCourse = service.getSelectedCourseByStudentID(student.getId());
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailPane.fxml"));
            loader.setController(this);
            Scene s = new Scene(loader.load());
            thisStage.setScene(s);
            thisStage.setTitle("Course System--Detail");
            thisStage.show();
            init();
            update();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void init(){
        id_show.setText(Integer.toString(student.getId()));
        name_show.setText(student.getName());
        department_show.setText(student.getDepartment());
        //可选课程
        ObservableList<TableColumn> courseColumns = coursetable.getColumns();
        courseColumns.get(0).setCellValueFactory(new PropertyValueFactory<Course,Integer>("id"));
        courseColumns.get(1).setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        courseColumns.get(2).setCellValueFactory(new PropertyValueFactory<Course,String>("department"));
        courseColumns.get(3).setCellValueFactory(new PropertyValueFactory<Course,String>("teacherName"));
        coursetable.setItems(courseObservableList);
        //已选课程
        ObservableList<TableColumn> selectedCourseColumns = selectedcoursetable.getColumns();
        selectedCourseColumns.get(0).setCellValueFactory(new PropertyValueFactory<Course,Integer>("id"));
        selectedCourseColumns.get(1).setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        selectedCourseColumns.get(2).setCellValueFactory(new PropertyValueFactory<Course,String>("department"));
        selectedCourseColumns.get(3).setCellValueFactory(new PropertyValueFactory<Course,String>("teacherName"));
        selectedcoursetable.setItems(selectedCourseObservableList);
    }

    @FXML
    private void handleRemoveCourseButtonAction(){
        SelectedCourse course = null;
        course = (SelectedCourse) selectedcoursetable.getSelectionModel().getSelectedItem();
        if(course!=null){
            selectedCourse.remove(course);
            service.removeSelectedCourse(course);
            update();
        }
       // flush();
    }
    @FXML
    private void handleSignOutAction(){
        new SigninPane();
        thisStage.close();
    }
    @FXML
    private void handleSelectCourseButtonAction(){
        Course course = null;
        course = (Course) coursetable.getSelectionModel().getSelectedItem();
        if(course!=null){
            int id = course.getId();
            String name = course.getName();
            String department = course.getDepartment();
            int teacherID = course.getTeacherID();
            String teacherName = course.getTeacherName();
            int studentID = student.getId();
            SelectedCourse c = new SelectedCourse(id,name,department,teacherID,teacherName,studentID);
            selectedCourse.add(c);
            service.selectCourse(c);
            update();
        }
    }
    private void setSelectableCourse(){
        selectableCourse = new ArrayList<>();
        for(int i = 0; i < courses.size(); ++i){
            if(checkSelect(courses.get(i).getId())){
                selectableCourse.add(courses.get(i));
            }
        }
    }
    private boolean checkSelect(int id){
        boolean pass = true;
        for(int i = 0; i < selectedCourse.size(); ++i){
            if(selectedCourse.get(i).getId()==id){
                pass = false;
            }
        }
        return pass;
    }
    private void update(){
        flush();
        setSelectableCourse();
        for(int i=0;i<=selectedCourse.size()-1;i++){
            selectedCourseObservableList.add(selectedCourse.get(i));
        }
        for(int i = 0;i<=selectableCourse.size()-1;i++){
            courseObservableList.add(selectableCourse.get(i));
        }
        coursetable.setItems(courseObservableList);
        selectedcoursetable.setItems(selectedCourseObservableList);
    }
    private void flush(){
        courseObservableList = FXCollections.observableArrayList();
        selectedCourseObservableList = FXCollections.observableArrayList();
    }
}

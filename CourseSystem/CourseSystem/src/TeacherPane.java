/*
TeacherPane.java:教师身份登陆后的页面逻辑
(Controller)
1.能够查看自己的课程安排
2.能够查看自己的信息
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

public class TeacherPane extends TabPane {
    private Teacher teacher;
    private Service service;
    private Stage thisStage;
    private ArrayList<Course> courses;
    private ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView courseTable;
    @FXML
    private Label id_show;
    @FXML
    private Label name_show;
    @FXML
    private Label department_show;
    @FXML
    public void handleSignOutAction(){
        new SigninPane();
        thisStage.close();
    }
    public TeacherPane(Teacher t_teacher){
        teacher = t_teacher;
        service = Service.getInstance();
        thisStage = new Stage();
        courses = service.getAllCourseByTeacherID(t_teacher.getId());
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherPane.fxml"));
            loader.setController(this);
            Scene s = new Scene(loader.load());
            thisStage.setScene(s);
            thisStage.setTitle("Course System--Teacher");
            thisStage.show();
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void init(){
        id_show.setText(Integer.toString(teacher.getId()));
        name_show.setText(teacher.getName());
        department_show.setText(teacher.getDepartment());

        ObservableList<TableColumn> courseColumns = courseTable.getColumns();
        courseColumns.get(0).setCellValueFactory(new PropertyValueFactory<Course,Integer>("id"));
        courseColumns.get(1).setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        courseColumns.get(2).setCellValueFactory(new PropertyValueFactory<Course,String>("department"));
        courseTable.setItems(courseObservableList);
        update();
    }
    private void update(){
        for(int i = 0;i<=courses.size()-1;i++){
            courseObservableList.add(courses.get(i));
        }
    }
}

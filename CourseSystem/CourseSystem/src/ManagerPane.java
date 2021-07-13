/*
ManagerPane.java:院系教务员登陆成功后的页面;
(Controller)
1.教务员可以看到本学院所有学生,老师和课表信息;
2.教务员可以通过课程ID,学生ID，老师ID查找相关信息；
3.教务员可以添加课程,删除课程;
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ManagerPane extends TabPane {
    private Manager manager;
    private Service service;
    private Stage thisStage;
    private ArrayList<Course> courses;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
    private ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();
    private ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView course_table;
    @FXML
    private TextField course_id_input;
    @FXML
    private TextField course_name_input;
    @FXML
    private TextField course_teacherID_input;
    @FXML
    private TextField course_query_id_input;
    @FXML
    private TableView student_table;
    @FXML
    private TextField student_query_id_input;
    @FXML
    private TableView teacher_table;
    @FXML
    private TextField teacher_query_id_input;




    public ManagerPane(Manager t_manager){
        manager = t_manager;
        service =Service.getInstance();
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerPane.fxml"));
            loader.setController(this);
            Scene s = new Scene(loader.load());
            thisStage.setScene(s);
            thisStage.setTitle("Course System--Manager");
            thisStage.show();
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void init(){
        courses = service.getAllCourseByDepartment(manager.getDepartment());
        students = service.getAllStudentByDepartment(manager.getDepartment());
        teachers = service.getAllTeacherByDepartment(manager.getDepartment());

        initCourseTable();
        initStudentTable();
        initTeacherTable();
    }
    private void initStudentTable(){
        ObservableList<TableColumn> studentColumns = student_table.getColumns();
        studentColumns.get(0).setCellValueFactory(new PropertyValueFactory<Course,Integer>("id"));
        studentColumns.get(1).setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        studentColumns.get(2).setCellValueFactory(new PropertyValueFactory<Course,String>("department"));
        student_table.setItems(studentObservableList);
        updateStudentTable();
    }

    private void initTeacherTable(){
        ObservableList<TableColumn> teacherColumns = teacher_table.getColumns();
        teacherColumns.get(0).setCellValueFactory(new PropertyValueFactory<Course,Integer>("id"));
        teacherColumns.get(1).setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        teacherColumns.get(2).setCellValueFactory(new PropertyValueFactory<Course,String>("department"));
        teacher_table.setItems(teacherObservableList);
        updateTeacherTable();
    }

    private void initCourseTable(){
        ObservableList<TableColumn> courseColumns = course_table.getColumns();
        courseColumns.get(0).setCellValueFactory(new PropertyValueFactory<Course,Integer>("id"));
        courseColumns.get(1).setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        courseColumns.get(2).setCellValueFactory(new PropertyValueFactory<Course,Integer>("teacherID"));
        courseColumns.get(3).setCellValueFactory(new PropertyValueFactory<Course,String>("teacherName"));
        course_table.setItems(courseObservableList);
        updateCourseTable();
    }

    private void updateStudentTable(){
        flushTable();
        for(int i = 0;i<=students.size()-1;i++){
            studentObservableList.add(students.get(i));
        }
        student_table.setItems(studentObservableList);
    }

    private void updateTeacherTable(){
        flushTable();
        for(int i = 0;i<=teachers.size()-1;i++){
            teacherObservableList.add(teachers.get(i));
        }
        teacher_table.setItems(teacherObservableList);
    }

    private void updateCourseTable(){
        flushTable();
        for(int i = 0;i<=courses.size()-1;i++){
            courseObservableList.add(courses.get(i));
        }
        course_table.setItems(courseObservableList);
    }
    private void flushTable(){
        courseObservableList = FXCollections.observableArrayList();
        studentObservableList = FXCollections.observableArrayList();
        teacherObservableList = FXCollections.observableArrayList();
    }
    @FXML
    private void handleAddCourseAction(){
        int id = Integer.valueOf(course_id_input.getText());
        if(service.getCourseByID(id)==null){
            String name = course_name_input.getText();
            String department = manager.getDepartment();
            int teacherID = Integer.valueOf(course_teacherID_input.getText());
            String teacherName = service.getTeacherByID(teacherID).getName();
            Course c = new Course(id,name,department,teacherID,teacherName);
            courses.add(c);
            updateCourseTable();
            //service
            service.addCourse(c);
        }

    }
    @FXML
    private void handleSignOutAction(){
        new SigninPane();
        thisStage.close();
    }

    @FXML
    private void handleRemoveCourseAction(){
        Course course = null;
        course = (Course) course_table.getSelectionModel().getSelectedItem();
        if(course!=null){
            courses.remove(course);
            updateCourseTable();
            service.removeCourse(course);
        }
    }
    @FXML
    private void handleTeacherShowAction(){
        updateTeacherTable();
    }
    @FXML
    private void handleTeacherQueryAction(){
        if(teacher_query_id_input.getText().length()!=0){
            int id = Integer.valueOf(teacher_query_id_input.getText());
            ObservableList<Teacher> teacherQuery = FXCollections.observableArrayList();
            for(int i = 0;i<=teachers.size()-1;i++){
                if(teachers.get(i).getId()==id){
                    teacherQuery.add(teachers.get(i));
                }
            }
            teacher_table.setItems(teacherQuery);
        }else{
            updateTeacherTable();
        }
    }
    @FXML
    private void handleStudentShowAction(){
        updateStudentTable();
    }
    @FXML
    private void handleStudentQueryAction(){
        if(student_query_id_input.getText().length()!=0){
            int id = Integer.valueOf(student_query_id_input.getText());
            ObservableList<Student> studentQuery = FXCollections.observableArrayList();
            for(int i = 0;i<=students.size()-1;i++){
                if(students.get(i).getId()==id){
                    studentQuery.add(students.get(i));
                }
            }
            student_table.setItems(studentQuery);
        }else{
            updateStudentTable();
        }
    }
    @FXML
    private void handleCourseShowAction(){
        updateCourseTable();
    }
    @FXML
    private void handleCourseQueryAction(){
        if(course_query_id_input.getText().length()!=0){
            int id = Integer.valueOf(course_query_id_input.getText());
            ObservableList<Course> courseQuery= FXCollections.observableArrayList();
            for(int i = 0;i<=courses.size()-1;i++){
                if(courses.get(i).getId()==id){
                    courseQuery.add(courses.get(i));
                }
            }
            course_table.setItems(courseQuery);
        }else{
            updateStudentTable();
        }
    }

}

/*
SigninPane.java:登陆页面的逻辑;
(Controller)
1.登陆前进行本地校验，提交请求
2.跳转到注册页面;
3.用户可以选择身份登陆:学生,老师,教务员;
4.不同的身份对应不同的id:学生8位,老师5位21001,教务员4位1001
5.不同身份登陆到不同的页面,并据此获取不同的数据权限,比如只有教务员才拥有增删课程的权力;
 */
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;


public class SigninPane extends BorderPane {
    private Service service;
    private Stage thisStage;
    public SigninPane(){
        service = Service.getInstance();
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SigninPane.fxml"));
            loader.setController(this);
            Scene s = new Scene(loader.load());
            thisStage.setScene(s);
            thisStage.setTitle("Course System--Login");
            thisStage.show();
            init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showStage(){
        //thisStage.showAndWait();
    }
    @FXML
    private Button signin_button;
    @FXML
    private Button register_button;
    @FXML
    private TextField id_input;
    @FXML
    private PasswordField password_input;
    @FXML
    private Label id_tips;
    @FXML
    private Label password_tips;
    @FXML
    private ChoiceBox id_select;

    @FXML
    public void handleSigninButton(ActionEvent event) {
        reset();
        String identity = (String)id_select.getSelectionModel().getSelectedItem();
        validate(identity);
    }
    @FXML
    public void handleRegisterButton(ActionEvent event) {
        thisStage.close();
        new RegisterPane();
    }
    private void init(){
        id_select.getItems().addAll("学生","老师","教务");
        id_select.getSelectionModel().selectFirst();
    }

    private void reset(){
        id_tips.setText("");
        password_tips.setText("");
    }
    private void validate(String identity){
        int status = 404;
        int id = Integer.valueOf(id_input.getText());
        String password = password_input.getText();

        if(identity.equals("学生")){
            status = service.checkStudentLogin(id,password);
        }
        if(identity.equals("老师")){
            status = service.checkTeacherLogin(id,password);
        }
        if(identity.equals("教务")){
            status = service.checkManagerLogin(id,password);
        }

        switch(status){
            case 200:{
                if(identity.equals("学生")){
                    Student student = service.loginStudent(id,password);
                    new DetailPane(student);
                }
                if(identity.equals("老师")){
                    Teacher teacher = service.loginTeacher(id,password);
                    new TeacherPane(teacher);

                }
                if(identity.equals("教务")){
                    Manager manager = service.loginManager(id,password);
                    new ManagerPane(manager);
                }
                thisStage.close();
            }break;
            case 4221:{
                id_tips.setText("ID不存在.");
            }break;
            case 4222:{
                password_tips.setText("密码错误");
            }
            break;
        }
    }
}

/*
RegisterPane.java:注册页面;
(Controller)
1.学生输入自己的信息进行注册;
2.用户ID不能为空；
3.不能够重复注册ID;
4.注册完成之后进行跳转
5.切换到登陆页面
 */

import com.sun.xml.internal.txw2.TxwException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class RegisterPane extends BorderPane {
    private Service service;
    private Stage thisStage;
    @FXML
    private TextField id_input;
    @FXML
    private PasswordField password_input;
    @FXML
    private TextField name_input;
    @FXML
    private TextField department_input;
    @FXML
    private Label id_tips;
    @FXML
    private Label password_tips;
    @FXML
    private Label name_tips;
    @FXML
    private Label department_tips;

    private int select = 0;

    public RegisterPane(){
        service = Service.getInstance();
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterPane.fxml"));
            loader.setController(this);
            Scene s = new Scene(loader.load());
            thisStage.setScene(s);
            thisStage.setTitle("Course System--Register");
            thisStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //处理并提交登陆请求
    @FXML
    public void handleRegisterButtonAction(){
        int status = 404;
        Student student = null;
        if(id_input.getText().length()!=0){
            int id = Integer.valueOf(id_input.getText());
            String password = password_input.getText();
            String name = name_input.getText();
            String department = department_input.getText();
            student= new Student(id,password,name,department);
            status = service.registerStudent(student);
       }
        switch (status){
            case 404:{
                id_tips.setText("ID不能为空");
            }break;
            case 200:{
                thisStage.close();
                new DetailPane(student);
            }break;
            case 4220:{
                id_tips.setText("ID已经被注册");
            }break;
        }

    }
    //返回登录界面
    @FXML
    public void handleSignInButtonAction(){
        thisStage.close();
        new SigninPane();
    }
}

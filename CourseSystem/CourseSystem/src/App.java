/*
App.java:整个程序的入口
启动程序,默认为登陆界面
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.*;

public class App extends Application{
    public static void main(String[] args){
        launch();
    }
    public void start(Stage stage)throws IOException {
        Service.getInstance().start();
        new SigninPane().showStage();
    }
}

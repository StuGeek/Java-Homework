import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread{
    private Socket socket;
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Course> selectedCourse = new ArrayList<Course>();
    private Student student;
    private int status = 404;

    public Client() throws IOException{
        socket = new Socket("localhost",8000);
        start();
    }
    public void setStudent(Student s){
        student = s;
    }
    public Student getStudent(){
        return student;
    }
    public void run(){
        String response;
        String information;
        while(true){
            try{
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                response = inFromServer.readLine();
                System.out.println("RES: "+response);
                status = Integer.valueOf(response.substring(0,response.indexOf("?")));
                System.out.println("Status:"+status);
                if(status==200){//获得所有已选课程
                    int studentID = 0;
                    String courseName;
                    String department;
                    information = response.substring(response.indexOf("?")+1);
                    String[] course_selectedCourse_str = information.split(";");
                    String[] course_str = course_selectedCourse_str[0].split("%%");
                    courses = new ArrayList<Course>();
                    for(int i = 0;i<=course_str.length-1;i++){
                        System.out.println(course_str[i]);
                        String[] CourseInfos = course_str[i].split("&&");
                        studentID = Integer.valueOf(CourseInfos[0]);
                        courseName = CourseInfos[1];
                        department = CourseInfos[2];
                    }
                    selectedCourse = new ArrayList<Course>();

                    if(course_selectedCourse_str.length == 2){
                        String[] selectedCourse_str = course_selectedCourse_str[1].split("%%");
                        for(int i = 0;i <= selectedCourse_str.length-1;i++){
                            System.out.println(selectedCourse_str[i]);
                            String[] selectedCourseInfo = selectedCourse_str[i].split("&&");
                            studentID = Integer.valueOf(selectedCourseInfo[0]);
                            courseName = selectedCourseInfo[1];
                            department = selectedCourseInfo[2];
                        }
                    }
                }
                if(status==422){//认证失败
                    System.out.println("wrong");
                }
                //socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //login
    public void sendRequest(String req,int id,String password) throws IOException,InterruptedException{
        // socket = new Socket("localhost",8000);
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        if(req.length()!=0){
            outToServer.writeBytes(req+"?"+id+"&&"+password+"\n");
        }
    }
    //register
    public void sendRequest(String req,int id,String password,String name,int age,String home,String department)throws InterruptedException,IOException{
        socket = new Socket("localhost",8000);
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        if(req.length()!=0){
            outToServer.writeBytes(req+"?"+id+"&&"+password+"&&"+name+"&&"+age+"&&"+home+"&&"+department+"\n");
        }
    }
    //addCourse
    public void sendRequest(String req,int id,String courseName,String department)throws InterruptedException,IOException{
        socket = new Socket("localhost",8000);
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        if(req.length()!=0){
            outToServer.writeBytes(req+"?"+id+"&&"+courseName+"&&"+department+"\n");
        }
    }
    //removeSelectedCourse
    public void sendRequest(String req,String courseName)throws IOException{
        socket = new Socket("localhost",8000);
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        if(req.length()!=0){
            outToServer.writeBytes(req+"?"+courseName+"\n");
        }
    }
    public ArrayList<Course> getSelectedCourse() throws Exception{
        Thread.sleep(300);
        return selectedCourse;
    }
    public ArrayList<Course> getCourses() throws Exception{
        Thread.sleep(300);
        return courses;
    }
    public void setStatus(int t_status){
        status = t_status;
    }
    public int getStatus()throws Exception{
        Thread.sleep(1000);
        return status;
    }
    private void setSocket(Socket s){
        socket = s;
    }
}
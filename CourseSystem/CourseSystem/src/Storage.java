/*
Storage.java:数据存储和交换
(Model)
1.建立与JDBC数据库的连接(数据库名:test，用户名:root password:空)
2.建立表数据，
3.能通过发送sql语句进行增删查改;
4.所有与数据库的信息交互都必须通过Storage类实现;
5.为Service提供数据接口;
 */

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class Storage{
    private String sql;
    public Storage(){
        getConnection();
        //创建表;
        createStudentTable();
        createTeacherTable();
        createManagerTable();
        createCoursesTable();
        createSelectedCoursesTable();
    }
    private Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String source = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
            con = DriverManager.getConnection(source,"root","");
            return con;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return con;
        }catch (SQLException e){
            e.printStackTrace();
            return con;
        }
    }
    public Boolean createStudentTable(){
        sql = "create table if not exists student(sno int primary key auto_increment," +
                "id varchar(8)," +
                "password char(64),"+
                "name char(20),"+
                "department char(20)" +
                ")";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean createTeacherTable(){
        sql = "create table if not exists teacher(sno int primary key auto_increment," +
                "id varchar(8)," +
                "password char(64),"+
                "name char(20),"+
                "department char(20)" +
                ")";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean createManagerTable(){
        sql = "create table if not exists manager(sno int primary key auto_increment," +
                "id varchar(8)," +
                "password char(64),"+
                "name char(20),"+
                "department char(20)" +
                ")";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public Boolean createCoursesTable(){
        sql = "create table if not exists course(sno int primary key auto_increment," +
                "id varchar(8)," +
                "name char(64),"+
                "department char(32)," +
                "teacherID varchar(8),"+
                "teacherName char(20)"+
                ")";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean createSelectedCoursesTable(){
        sql = "create table if not exists selectedCourse(sno int primary key auto_increment," +
                "id varchar(8)," +
                "name char(64),"+
                "department char(32)," +
                "teacherID varchar(8),"+
                "teacherName char(20),"+
                "studentID varchar(8)"+
                ")";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.execute(sql);
            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insertStudent(Student t_student){
        sql = "INSERT INTO student(id,password,name,department) values(?,?,?,?)";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_student.getId());
            ptmt.setString(2,t_student.getPassword());
            ptmt.setString(3,t_student.getName());
            ptmt.setString(4,t_student.getDepartment());
            ptmt.execute();
            ptmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insertTeacher(Teacher t_teacher){
        sql = "INSERT INTO teacher(id,password,name,department) values(?,?,?,?)";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_teacher.getId());
            ptmt.setString(2,t_teacher.getPassword());
            ptmt.setString(3,t_teacher.getName());
            ptmt.setString(4,t_teacher.getDepartment());
            ptmt.execute();
            ptmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insertManager(Manager t_manager){
        sql = "INSERT INTO manager(id,password,name,department) values(?,?,?,?)";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_manager.getId());
            ptmt.setString(2,t_manager.getPassword());
            ptmt.setString(3,t_manager.getName());
            ptmt.setString(4,t_manager.getDepartment());
            ptmt.execute();
            ptmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insertCourse(Course t_course){
        sql = "INSERT INTO course(id,name,department,teacherID,teacherName) values(?,?,?,?,?)";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_course.getId());
            ptmt.setString(2,t_course.getName());
            ptmt.setString(3,t_course.getDepartment());
            ptmt.setInt(4,t_course.getTeacherID());
            ptmt.setString(5,t_course.getTeacherName());
            ptmt.execute();
            ptmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean insertSelectedCourse(SelectedCourse t_course){
        sql = "INSERT INTO selectedCourse(id,name,department,teacherID,teacherName,studentID) values(?,?,?,?,?,?)";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_course.getId());
            ptmt.setString(2,t_course.getName());
            ptmt.setString(3,t_course.getDepartment());
            ptmt.setInt(4,t_course.getTeacherID());
            ptmt.setString(5,t_course.getTeacherName());
            ptmt.setInt(6,t_course.getStudentID());
            ptmt.execute();
            ptmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deleteStudentByID(int t_id){
        sql = "DELETE FROM student where id=?";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_id);
            ptmt.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deleteStudentBetweenIDs(int l_id,int h_id){
        sql = "DELETE FROM student where id>=? AND id<=?";
        try {
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,l_id);
            ptmt.setInt(2,h_id);
            ptmt.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean deleteSelectedCourseByCourseName(String courseName){
        sql = "DELETE FROM selectedCourse where name=?";
        try {
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,courseName);
            ptmt.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Student queryStudentByID(int t_id){
        Student student = null;
        //SQL语句，用于查询学生表中的学号、姓名、年龄、籍贯和系别
        sql = "SELECT id,password,name,department FROM student where id=?";
        //执行查询，查询结果放在ResultSet的对象中
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_id);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                student = new Student();
                student.setId(rs.getInt(1));
                student.setPassword(rs.getString(2));
                student.setName(rs.getString(3));
                student.setDepartment(rs.getString(4));
            }
            ptmt.close();
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return student;
        }
    }

    public Teacher queryTeacherByID(int t_id){
        Teacher teacher= null;
        //SQL语句，用于查询学生表中的学号、姓名、年龄、籍贯和系别
        sql = "SELECT id,password,name,department FROM teacher where id=?";
        //执行查询，查询结果放在ResultSet的对象中
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_id);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setPassword(rs.getString(2));
                teacher.setName(rs.getString(3));
                teacher.setDepartment(rs.getString(4));
            }
            ptmt.close();
            return teacher;
        }catch (Exception e){
            e.printStackTrace();
            return teacher;
        }
    }

    public Manager queryManagerByID(int t_id){
        Manager manager = null;
        //SQL语句，用于查询学生表中的学号、姓名、年龄、籍贯和系别
        sql = "SELECT id,password,name,department FROM manager where id=?";
        //执行查询，查询结果放在ResultSet的对象中
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_id);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                manager = new Manager();
                manager.setId(rs.getInt(1));
                manager.setPassword(rs.getString(2));
                manager.setName(rs.getString(3));
                manager.setDepartment(rs.getString(4));
            }
            ptmt.close();
            return manager;
        }catch (Exception e){
            e.printStackTrace();
            return manager;
        }
    }

    public Course queryCourseByID(int t_id){
        Course course = null;
        //SQL语句，用于查询学生表中的学号、姓名、年龄、籍贯和系别
        sql = "SELECT id,name,department,teacherID,teacherName FROM course where id=?";
        //执行查询，查询结果放在ResultSet的对象中
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_id);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setDepartment(rs.getString(3));
                course.setTeacherID(rs.getInt(4));
                course.setTeacherName(rs.getString(5));
            }
            ptmt.close();
            return course;
        }catch (Exception e){
            e.printStackTrace();
            return course;
        }
    }


    public Course queryCourseByCourseName(String t_courseName){
        Course course = null;
        //SQL语句，用于查询学生表中的学号、姓名、年龄、籍贯和系别
        sql = "SELECT id,name,department,teacherID,teacherName FROM course where name=?";
        //执行查询，查询结果放在ResultSet的对象中
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,t_courseName);
            ResultSet rs = ptmt.executeQuery();
            int id,age;
            String name,home,department;
            while(rs.next()) {
                course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setDepartment(rs.getString(3));
                course.setTeacherID(rs.getInt(4));
                course.setTeacherName(rs.getString(5));
            }
            ptmt.close();
            return course;
        }catch (Exception e){
            e.printStackTrace();
            return course;
        }
    }

    public Student queryStudentByName(String t_name){
        Student student = null;
        //SQL语句，用于查询学生表中的学号、姓名、年龄、籍贯和系别
        sql = "SELECT id,password,name,department FROM student where name=?";
        //执行查询，查询结果放在ResultSet的对象中
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,t_name);
            ResultSet rs = ptmt.executeQuery();
            int id,age;
            String name,home,department;
            while(rs.next()) {
                student = new Student();
                student.setId(rs.getInt(1));
                student.setPassword(rs.getString(2));
                student.setName(rs.getString(3));
                student.setDepartment(rs.getString(4));
            }
            ptmt.close();
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return student;
        }
    }

    public ArrayList<Student> queryAllStudentsByDepartment(String t_department){
        ArrayList<Student> students = new ArrayList<Student>();
        sql = "SELECT id,password,name,department FROM student where department=?";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,t_department);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setPassword(rs.getString(2));
                student.setName(rs.getString(3));
                student.setDepartment(rs.getString(4));
                students.add(student);
            }
            ptmt.close();
            return students;
        }catch (Exception e){
            e.printStackTrace();
            return students;
        }
    }

    public ArrayList<Teacher> queryAllTeachersByDepartment(String t_department){
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        sql = "SELECT id,password,name,department FROM teacher where department=?";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,t_department);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt(1));
                teacher.setPassword(rs.getString(2));
                teacher.setName(rs.getString(3));
                teacher.setDepartment(rs.getString(4));
                teachers.add(teacher);
            }
            ptmt.close();
            return teachers;
        }catch (Exception e){
            e.printStackTrace();
            return teachers;
        }
    }



    public ArrayList<Course> queryAllCoursesByDepartment(String t_department){
        ArrayList<Course> courses = new ArrayList<Course>();
        sql = "SELECT id,name,department,teacherID,teacherName FROM course where department=?";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setString(1,t_department);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setDepartment(rs.getString(3));
                course.setTeacherID(rs.getInt(4));
                course.setTeacherName(rs.getString(5));
                courses.add(course);
            }
            ptmt.close();
            return courses;
        }catch (Exception e){
            e.printStackTrace();
            return courses;
        }
    }


    public ArrayList<Course> queryAllCoursesByTeacherID(int t_teacherID){
        ArrayList<Course> courses = new ArrayList<Course>();
        sql = "SELECT id,name,department,teacherID,teacherName FROM course where teacherID=?";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_teacherID);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setDepartment(rs.getString(3));
                course.setTeacherID(rs.getInt(4));
                course.setTeacherName(rs.getString(5));
                courses.add(course);
            }
            ptmt.close();
            return courses;
        }catch (Exception e){
            e.printStackTrace();
            return courses;
        }
    }

    public ArrayList<SelectedCourse> queryAllSelectedCoursesByStudentID(int t_studentId){
        ArrayList<SelectedCourse> courses = new ArrayList<SelectedCourse>();
        sql = "SELECT id,name,department,teacherID,teacherName,studentID FROM selectedCourse where studentID=?";
        try{
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,t_studentId);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                SelectedCourse course = new SelectedCourse();
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setDepartment(rs.getString(3));
                course.setTeacherID(rs.getInt(4));
                course.setTeacherName(rs.getString(5));
                course.setStudentID(rs.getInt(6));
                courses.add(course);
            }
            ptmt.close();
            return courses;
        }catch (Exception e){
            e.printStackTrace();
            return courses;
        }
    }

    public Boolean deleteCourse(Course course){
        sql = "DELETE FROM course where id=?";
        try {
            PreparedStatement ptmt = getConnection().prepareStatement(sql);
            ptmt.setInt(1,course.getId());
            ptmt.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}

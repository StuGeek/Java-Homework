/*
Service.java:服务类
1.负责请求数据库,处理数据库信息
2.对数据进行简单的重复校验和密码的加密工作;
3.对外提供服务接口;
 */

import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.ArrayList;


public class Service {
    private static Service instance;
    private Storage storage;
    public Service(){
        System.out.println("[Service] Course system start.");
    }

    //学生业务
    public Student loginStudent(int id,String password){
        if(checkStudentLogin(id,password)==200){
            return storage.queryStudentByID(id);
        }else{
            return null;
        }
    }
    public int registerStudent(Student t_student){
        int Status = checkStudentRegister(t_student);
        if(Status==200){
            storage.insertStudent(t_student);
            System.out.println("[Service] register 1 Student.");
        }
        return Status;
        // System.out.println(DigestUtils.md5Hex("123456"));
    }

    public ArrayList<Course> getAllCourseByDepartment(String department){
        return storage.queryAllCoursesByDepartment(department);
    }

    public ArrayList<SelectedCourse> getSelectedCourseByStudentID(int studentID){
        return storage.queryAllSelectedCoursesByStudentID(studentID);
    }

    public void selectCourse(SelectedCourse course){
        storage.insertSelectedCourse(course);
    }
    public void removeSelectedCourse(SelectedCourse course){
        storage.deleteSelectedCourseByCourseName(course.getName());
    }

    private int checkStudentRegister(Student t_student){
        //ID已被注册
        if(storage.queryStudentByID(t_student.getId())!=null){
            return 4220;
        }
        //
        return 200;
    }
    public int checkStudentLogin(int id,String password){
        Student query_student = storage.queryStudentByID(id);
        if(query_student==null){
            //ID不存在;
            return 4221;
        }else{
            if(!query_student.getPassword().equals(password)){
                //密码错误;
                return 4222;
            }
            return 200;
        }
    }

    //老师业务
    public void registerTeacher(Teacher t_teacher){
        storage.insertTeacher(t_teacher);
        System.out.println("[Service] register 1 Teacher.");
    }
    public Teacher loginTeacher(int id,String password){
        if(checkTeacherLogin(id,password)==200){
            return storage.queryTeacherByID(id);
        }else{
            return null;
        }
    }

    public int checkTeacherLogin(int id,String password){
        Teacher query_teacher = storage.queryTeacherByID(id);
        if(query_teacher==null){
            //ID不存在;
            return 4221;
        }else{
            if(!query_teacher.getPassword().equals(password)){
                //密码错误;
                return 4222;
            }
            return 200;
        }
    }
    public ArrayList<Course> getAllCourseByTeacherID(int id){
        return storage.queryAllCoursesByTeacherID(id);
    }


    //教务员业务

    public void registerManager(Manager t_manager){
        storage.insertManager(t_manager);
        System.out.println("[Service] register 1 Manager.");
    }

    public Manager loginManager(int id,String password){
        if(checkManagerLogin(id,password)==200){
            return storage.queryManagerByID(id);
        }else{
            return null;
        }
    }

    public ArrayList<Student> getAllStudentByDepartment(String department){
        return storage.queryAllStudentsByDepartment(department);
    }


    public ArrayList<Teacher> getAllTeacherByDepartment(String department){
        return storage.queryAllTeachersByDepartment(department);
    }

    public Teacher getTeacherByID(int id){
        return storage.queryTeacherByID(id);
    }

    public Course getCourseByID(int id){
        return storage.queryCourseByID(id);
    }

    public int checkManagerLogin(int id,String password){
        Manager manager = storage.queryManagerByID(id);
        if(manager==null){
            //ID不存在;
            return 4221;
        }else{
            if(!manager.getPassword().equals(password)){
                //密码错误;
                return 4222;
            }
            return 200;
        }
    }

    public void addCourse(Course course){
        storage.insertCourse(course);
        System.out.println("[Service] add 1 course.");
    }
    public void removeCourse(Course course){
        storage.deleteCourse(course);
        System.out.println("[Service] remove 1 course.");
    }

    public void start(){
        storage = new Storage();
        System.out.println("[Storage] JDBC Connected successfully");
        createTestCase();
    }
    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }
    private void createTestCase(){
        //Student testing cases;
        Student s1 = storage.queryStudentByID(18322001);
        if(s1==null){
            storage.insertStudent(new Student(18322001,"123456","Xiaoming","ComputerScience"));
        }
        Student s2 = storage.queryStudentByID(18322002);
        if(s2==null){
            storage.insertStudent(new Student(18322002,"123456","XiaoHong","ComputerScience"));
        }
        Student s3 = storage.queryStudentByID(18322101);
        if(s3==null){
            storage.insertStudent(new Student(18322101,"123456","Lengshao","Psychology"));
        }
        Student s4 = storage.queryStudentByID(18322102);
        if(s4==null){
            storage.insertStudent(new Student(18322102,"123456","Fanghua","Psychology"));
        }
        //Teacher testing cases;
        Teacher t1 = storage.queryTeacherByID(2001);
        if(t1==null){
            storage.insertTeacher(new Teacher(2001,"123456","Cai Guoyang","ComputerScience"));
        }
        Teacher t2 = storage.queryTeacherByID(2002);
        if(t2==null){
            storage.insertTeacher(new Teacher(2002,"123456","Gao yang","ComputerScience"));
        }
        Teacher t3 = storage.queryTeacherByID(2003);
        if(t3==null){
            storage.insertTeacher(new Teacher(2003,"123456","Liu fei","ComputerScience"));
        }
        Teacher t4 = storage.queryTeacherByID(2004);
        if(t4==null){
            storage.insertTeacher(new Teacher(2004,"123456","Li Fenghua","ComputerScience"));
        }
        Teacher t5 = storage.queryTeacherByID(2101);
        if(t5==null){
            storage.insertTeacher(new Teacher(2101,"123456","Emma","Psychology"));
        }
        Teacher t6 = storage.queryTeacherByID(2102);
        if(t6==null){
            storage.insertTeacher(new Teacher(2102,"123456","Belinda","Psychology"));
        }
        Teacher t7 = storage.queryTeacherByID(2103);
        if(t7==null){
            storage.insertTeacher(new Teacher(2103,"123456","Amy","Psychology"));
        }
        Teacher t8 = storage.queryTeacherByID(2104);
        if(t8==null){
            storage.insertTeacher(new Teacher(2104,"123456","Micheal","Psychology"));
        }
        //Manager testing cases
        Manager m1 = storage.queryManagerByID(1001);
        if(m1==null){
            storage.insertManager(new Manager(1001,"123456","Liufeng","ComputerScience"));
        }
        Manager m2 = storage.queryManagerByID(1002);
        if(m2==null){
            storage.insertManager(new Manager(1002,"123456","Mengxue","Psychology"));
        }
        //Course testing cases;
        Course c1 = storage.queryCourseByID(12001);
        if(c1==null){
            storage.insertCourse(new Course(12001,"DataStructure","ComputerScience",2001,"Cai Guoyang"));
        }
        Course c2 = storage.queryCourseByID(12002);
        if(c2==null){
            storage.insertCourse(new Course(12002,"MathAnalyse","ComputerScience",2002,"Gao yang"));
        }
        Course c3 = storage.queryCourseByID(12003);
        if(c3==null){
            storage.insertCourse(new Course(12003,"AlgorithmAnalyse","ComputerScience",2003,"Liu fei"));
        }
        Course c4 = storage.queryCourseByID(12101);
        if(c4==null){
            storage.insertCourse(new Course(12101,"PsychologyInstruction","Psychology",2101,"Emma"));
        }
        Course c5 = storage.queryCourseByID(12102);
        if(c5==null){
            storage.insertCourse(new Course(12102,"English","Psychology",2102,"Belinda"));
        }
    }
}

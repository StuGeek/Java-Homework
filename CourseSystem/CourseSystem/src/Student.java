/*
Student.java:定义学生类
 */
public class Student extends User{
    public Student(int id,String password,String name,String department){
        super(id,password,name,department);
    }
    public Student(){
        super();
    }
}

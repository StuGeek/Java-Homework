/*
Course.java:定义Course类:
 */
public class Course {
    private int id;
    private String name;
    private String department;
    private int teacherID;
    private String teacherName;
    public Course(){

    }
    /*
    * params@1:id;
    * params@2:name;
    * params@3:department;
    * params@4:teacherID;
    * params@5:teacherName
    */
    public Course(int t_id,String t_courseName,String t_department,int t_teacherID,String t_teacherName){
        id = t_id;
        name = t_courseName;
        department = t_department;
        teacherID = t_teacherID;
        teacherName = t_teacherName;
    }

    public String getName(){return name;}
    public int getId(){return id;}
    public String getDepartment(){return department;}
    public int  getTeacherID(){return teacherID;}
    public String getTeacherName(){return teacherName;}

    public void setName(String course){
        name = course;
    }
    public void setId(int t_id){
        id = t_id;
    }
    public void setDepartment(String t_department){
        department = t_department;
    }
    public void setTeacherID(int t_teacherID){
        teacherID = t_teacherID;
    }
    public void setTeacherName(String t_teacherName){
        teacherName =  t_teacherName;
    }
}

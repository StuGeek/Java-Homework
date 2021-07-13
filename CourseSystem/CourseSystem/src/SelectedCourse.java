/*
SelectedCourse.java:定义了选课类,增加了选课学生的ID;
 */

public class SelectedCourse extends Course{

    private int studentID;
    public SelectedCourse(){
        super();
    }
    /*
     * params@1:id;
     * params@2:name;
     * params@3:department;
     * params@4:teacherID;
     * params@5:teacherName;
     * params@6:studentID;
     */
    public SelectedCourse(int t_id,String t_name,String t_department,int t_teacherID,String t_teacherName,int t_studentID){
        super(t_id,t_name,t_department,t_teacherID,t_teacherName);
        studentID = t_studentID;
    }
    public void setStudentID(int t_studentID){
        studentID = t_studentID;
    }
    public int getStudentID(){
        return studentID;
    }
}

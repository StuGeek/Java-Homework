/*
User.java:定义了User基本类：ID,密码,姓名,学院
 */

public class User {
    //id,password,name,department
    private int id;
    private String name;
    private String password;
    private String department;
    public User(){

    }
    public User(int t_id,String t_password,String t_name,String t_department){
        this.id = t_id;
        this.password = t_password;
        this.name = t_name;
        this.department = t_department;
    }
    public void setId(int t_id){
        id = t_id;
    }
    public void setPassword(String t_password){
        password = t_password;
    }
    public void setName(String t_name){
        name = t_name;
    }
    public void setDepartment(String t_department){
        department = t_department;
    }
    public int getId(){
        return id;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    public String getDepartment(){
        return department;
    }
}

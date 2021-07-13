/*
* Manager.java:定义管理员类;
* */
public class Manager extends User{
    public Manager(int t_id,String t_password,String t_name,String t_department){
        super(t_id,t_password,t_name,t_department);
    }
    public Manager(){
        super();
    }
}

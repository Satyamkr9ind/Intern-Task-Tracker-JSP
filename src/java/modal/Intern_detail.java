/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author satya
 */
public class Intern_detail {
    int Iid;
    String Iname;
    int Sid;
    String Sname;
    public Intern_detail(){}
    public Intern_detail(int Iid,String Iname,int Sid,String Sname){
        this.Iid=Iid;
        this.Iname=Iname;
        this.Sid=Sid;
        this.Sname=Sname;
    }
    public int getIid(){
            return this.Iid;
    }
    public int getSid(){
            return this.Sid;
    }
    public String getIname(){
            return this.Iname;
    }
    public String getSname(){
            return this.Sname;
    }
    
  
}

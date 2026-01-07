/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modal.Intern_detail;
import modal.Supervisor_detail;

/**
 *
 * @author satya
 */
public class Login {
        private final String URL ="jdbc:mysql://localhost:3306/project";
        private final String USER= "root";
        private final String PASS="";
         
        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch (ClassNotFoundException e){
                e.printStackTrace();   
            }
        }
        public Intern_detail verifyIntern(String email,String pass,String role){
            String sql="SELECT intern.Iname, intern.Iid, supervisor.Sname, intern.supervisor_id FROM intern LEFT JOIN supervisor ON intern.supervisor_id = supervisor.Sid WHERE intern.email = ? AND intern.password = ?";
            try(
                Connection con = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement ps = con.prepareStatement(sql);)
                {
                ps.setString(1,email);
                ps.setString(2,pass);
                try (ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                    int Iid =rs.getInt("Iid");
                    String Iname = rs.getString("Iname");
                    int Sid = rs.getInt("supervisor_id");
                    String Sname = rs.getString("Sname");
                    return new Intern_detail(Iid,Iname,Sid,Sname);
                
                }
            }
            } catch(SQLException e){
                e.printStackTrace();
            } 
            return null;
        }
        
        public Supervisor_detail verifySupervisor(String email,String pass,String role){
            String sql = "SELECT Sid,Sname FROM supervisor where email=? and password=?";
            try(
                Connection con= DriverManager.getConnection(URL,USER,PASS);
                PreparedStatement ps = con.prepareStatement(sql);
                ){
                ps.setString(1,email);
                ps.setString(2,pass);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        int sid = rs.getInt("Sid");
                        String sname = rs.getString("Sname");
                        return new Supervisor_detail(sid,sname);
                        }
                 }
            }catch(SQLException e){
                e.printStackTrace();
            }
        return null;
    }
}

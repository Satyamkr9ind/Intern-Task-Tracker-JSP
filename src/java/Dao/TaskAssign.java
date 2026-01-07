package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;  
import java.time.LocalDateTime;

public class TaskAssign {

    public String insertTask(Connection con, int Sid, int internId, String title, String desc,
                             LocalDateTime assignedDate, LocalDateTime submissionDate) {

        String sql = "INSERT INTO task (title, description, assigned_to, assigned_by, created_at, date_of_submission) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, desc);
            ps.setInt(3, internId);
            ps.setInt(4, Sid);
            ps.setTimestamp(5, Timestamp.valueOf(assignedDate));
            ps.setTimestamp(6, Timestamp.valueOf(submissionDate));

            int row = ps.executeUpdate();
            if (row > 0) {
                return "Task assigned Succesfully !";
            } else {
                return "Some error occurred. Please try again later.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    
    public int deletetask(Connection con ,int Tid){
        String sql = "DELETE FROM task where id= ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setInt(1, Tid);
             int row= ps.executeUpdate();
             ps.close();
             return row;
        }catch (SQLException e) {
            e.printStackTrace();
           
        }
        
     return 0;   
    }
}

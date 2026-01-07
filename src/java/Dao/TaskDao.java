package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modal.Taskcount;
import modal.Task;

public class TaskDao {

    // Database connection info
    private final String URL = "jdbc:mysql://localhost:3306/project";
    private final String USER = "root";
    private final String PASS = "";

    // Load driver (optional for newer JDBC)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Fetch tasks assigned to a specific intern
    public List<Task> fetchTask(int Iid) {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE assigned_to = ? ORDER BY id DESC";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Iid); // set intern ID

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task t = new Task(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("assigned_to"),
                            rs.getInt("assigned_by"),
                            rs.getString("status"),
                            rs.getString("Intern_remark"),
                            rs.getString("supervisor_remark"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("date_of_submission") != null
                                    ? rs.getTimestamp("date_of_submission").toLocalDateTime()
                                    : null
                    );
                    list.add(t);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Update remark for a task
    public int updateRemark(int id, String remark) {
        String sql = "UPDATE task SET Intern_remark = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, remark);
            ps.setInt(2, id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return rowsUpdated;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Taskcount count_task (int Iid){
       String sql = "SELECT COUNT(*) AS total, SUM(CASE WHEN status='Pending' THEN 1 ELSE 0 END) AS pending, SUM(CASE WHEN status='Completed' THEN 1 ELSE 0 END) AS completed FROM task WHERE assigned_to = ?";
       try(
            Connection con = DriverManager.getConnection(URL,USER,PASS);
            PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1,Iid);
            try(ResultSet rs= ps.executeQuery()){
            if (rs.next()){
               int total = rs.getInt("total");
               int pending = rs.getInt("pending");
               int completed = rs.getInt("completed");
               return new Taskcount(total,pending,completed);
            }
            }
            
       }catch(SQLException e){
            e.printStackTrace();
       }
       return new Taskcount(0, 0, 0);
    }
     
}

//note : you can you can make util classes for connection and import it in servlet and pass to use it is efficient no 
//need to open connection again and again which makes system slow same has been done in case of supervisor dashboard. 
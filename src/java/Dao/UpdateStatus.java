/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author satya
 */
public class UpdateStatus {
    public String taskStatus(Connection con,int id , String status) {
                System.out.print(con);
        String sql = "UPDATE task SET status=? WHERE id=?;";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,status);
            ps.setInt(2,id);

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

}

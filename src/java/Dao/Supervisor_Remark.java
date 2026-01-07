/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author satya
 */
public class Supervisor_Remark {

    public int UpdateRemark(Connection con, int id, String remark) {
        String sql = "UPDATE task SET supervisor_remark = ? WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, remark);
            ps.setInt(2, id);
            int row = ps.executeUpdate();
            return row;
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return 0; 
    } 
}


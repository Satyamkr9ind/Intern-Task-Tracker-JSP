package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modal.DashboardSummary;
public class Super_dash_summary {

    public List<DashboardSummary> summary(Connection con, int Sid) {
        List<DashboardSummary> list = new ArrayList<>();

        String sql = """
            SELECT
                (SELECT COUNT(*) FROM intern i WHERE i.supervisor_id = s.Sid) AS total_intern,
                (SELECT COUNT(*) 
                 FROM task t
                 JOIN intern i ON t.assigned_to = i.Iid
                 WHERE i.supervisor_id = s.Sid AND t.assigned_by = s.Sid) AS total_task_assigned,
                (SELECT COUNT(*) 
                 FROM task t
                 JOIN intern i ON t.assigned_to = i.Iid
                 WHERE i.supervisor_id = s.Sid AND t.assigned_by = s.Sid AND t.status='Completed') AS total_task_completed,
                (SELECT COUNT(*) 
                 FROM task t
                 JOIN intern i ON t.assigned_to = i.Iid
                 WHERE i.supervisor_id = s.Sid AND t.assigned_by = s.Sid AND t.status='Pending') AS total_task_pending,
                i.Iid AS intern_id,
                i.Iname AS intern_name,
                (SELECT COUNT(*) 
                 FROM task t
                 WHERE t.assigned_to = i.Iid AND t.assigned_by = s.Sid) AS intern_total_tasks,
                (SELECT COUNT(*) 
                 FROM task t
                 WHERE t.assigned_to = i.Iid AND t.assigned_by = s.Sid AND t.status='Completed') AS intern_tasks_completed,
                (SELECT COUNT(*) 
                 FROM task t
                 WHERE t.assigned_to = i.Iid AND t.assigned_by = s.Sid AND t.status='Pending') AS intern_pending_tasks
            FROM supervisor s
            LEFT JOIN intern i
                ON i.supervisor_id = s.Sid
            WHERE s.Sid = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Sid);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int totalIntern = rs.getInt("total_intern");
                    int totalTaskAssigned = rs.getInt("total_task_assigned");
                    int totalTaskCompleted = rs.getInt("total_task_completed");
                    int totalTaskPending = rs.getInt("total_task_pending");
                    
                    int internId = rs.getInt("intern_id");
                    if (rs.wasNull()) internId = 0; 
                    String internName = rs.getString("intern_name");
                    if (internName == null) internName = "No Intern Assigned";
                    int internTotalTasks = rs.getInt("intern_total_tasks");
                    int internTasksCompleted = rs.getInt("intern_tasks_completed");
                    int internPendingTasks = rs.getInt("intern_pending_tasks");
                    DashboardSummary obj = new DashboardSummary(totalIntern, totalTaskAssigned, totalTaskCompleted, totalTaskPending,
                                      internId, internName, internTotalTasks, internTasksCompleted, internPendingTasks);
                    list.add(obj);
                    
                    
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}

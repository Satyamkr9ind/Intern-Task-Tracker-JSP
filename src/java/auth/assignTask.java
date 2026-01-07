package auth;

import Dao.TaskAssign;
import java.io.IOException;
import java.time.LocalDateTime;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBUtil;

@WebServlet(name = "assignTask", urlPatterns = {"/assignTask"})
public class assignTask extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // don't create new
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Session expired. Please login again.");
            return;
        }

        try {
            // Get supervisor ID from session
            int Sid = (int) session.getAttribute("SId");

            // Get task details from AJAX request
            String internIdStr = request.getParameter("internId");
            String title = request.getParameter("taskName");
            String desc = request.getParameter("taskDesc");
            String assignedDateStr = request.getParameter("assignedDate");
            String submissionDateStr = request.getParameter("submissionDate");

            // Convert to proper types
            int internId = Integer.parseInt(internIdStr);
            LocalDateTime assignedDate = LocalDateTime.parse(assignedDateStr);
            LocalDateTime submissionDate = LocalDateTime.parse(submissionDateStr);

            // Insert task into DB
            try (Connection con = DBUtil.getConnection()) {
                TaskAssign taDao = new TaskAssign();
                String message = taDao.insertTask(con, Sid, internId, title, desc, assignedDate, submissionDate);
                // Return success message to AJAX
                response.setContentType("text/plain");
                response.getWriter().write(message);
                
            }
         
            
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error assigning task: " + e.getMessage());
        }
    }
}

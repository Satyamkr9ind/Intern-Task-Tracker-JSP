/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package auth;

import Dao.TaskAssign;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.UpdateStatus;
import java.sql.Connection;
import util.DBUtil;
@WebServlet(name = "updateStatus", urlPatterns = {"/updateStatus"})
public class updateStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            String status = request.getParameter("status");
            try (Connection con = DBUtil.getConnection()) {
                 UpdateStatus us = new UpdateStatus();
                 us.taskStatus(con, taskId, status);
                // Return success message to AJAX
                response.setContentType("text/plain");
                //response.getWriter().write(message);
                
            }
          
            
            response.getWriter().write("Status received successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

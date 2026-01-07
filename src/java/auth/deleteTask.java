/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.TaskAssign;
import java.sql.Connection;
import util.DBUtil;
@WebServlet(name = "deleteTask", urlPatterns = {"/deleteTask"})
public class deleteTask extends HttpServlet {
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Tid=Integer.parseInt(request.getParameter("taskId"));
        try (Connection con = DBUtil.getConnection()) {
            TaskAssign del = new TaskAssign();
           int row= del.deletetask(con, Tid);
           if(row>0){
               response.setContentType("text/plain"); 
               response.getWriter().write("Task deleted successfully");
    } else {
        response.setContentType("text/plain");
        response.getWriter().write("Failed to delete task");
           }
        }catch (Exception e) {
            e.printStackTrace();
        }
       
        
        
        }
}

    



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package auth;

import Dao.TaskDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Task;
import modal.Taskcount;

@WebServlet(name = "addRemark", urlPatterns = {"/addRemark"})
public class addRemark extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        int id = Integer.parseInt(request.getParameter("taskId"));
        String remark = request.getParameter("remark");
        TaskDao task = new TaskDao();
        int row = task.updateRemark(id, remark);
        if(row>0){
               response.setContentType("text/plain"); 
               response.getWriter().write(" saved successfully !");
        } else {
        response.setContentType("text/plain");
        response.getWriter().write("operation failed !");
        }
         
    } catch (Exception e) {
        e.printStackTrace();
    }
}}  
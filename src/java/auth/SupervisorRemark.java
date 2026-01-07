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
import util.DBUtil;
import java.sql.Connection;
import Dao.Supervisor_Remark;
import java.sql.SQLException;

/**
 *
 * @author satya
 */
@WebServlet(name = "SupervisorRemark", urlPatterns = {"/SupervisorRemark"})
public class SupervisorRemark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id =Integer.parseInt(request.getParameter("taskId"));
            String remark = request.getParameter("remark");
            Connection con = DBUtil.getConnection();
            Supervisor_Remark sr = new Supervisor_Remark();
            int row =sr.UpdateRemark(con, id, remark);
            if(row>0){
               response.setContentType("text/plain"); 
               response.getWriter().write(" saved successfully !");
    } else {
        response.setContentType("text/plain");
        response.getWriter().write("operation failed !");
           }
        } catch (SQLException ex) {
            System.getLogger(SupervisorRemark.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
         
         
        }
}

    



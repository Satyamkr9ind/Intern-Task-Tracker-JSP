package auth;

import Dao.TaskDao; 
import Dao.Login;
import Dao.Super_dash_summary;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.DashboardSummary;
import modal.Intern_detail;
import modal.Supervisor_detail;
import modal.Taskcount;
import util.DBUtil;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String role = request.getParameter("role");

        Login log = new Login();
        HttpSession session = request.getSession();

        if ("intern".equals(role)) {
            Intern_detail intern = log.verifyIntern(email, pass, role);

            if (intern != null) {
                // Store intern info in session
                session.setAttribute("intern", intern);
                session.setAttribute("role", role);
                session.setAttribute("internId", intern.getIid());

                // Count tasks and store in session
                TaskDao taskDao = new TaskDao();
                Taskcount taskcount = taskDao.count_task(intern.getIid());
                session.setAttribute("taskcount", taskcount);

                // Fetch tasks and store in session
                List<?> taskList = taskDao.fetchTask(intern.getIid());
                session.setAttribute("tasks", taskList);

                // Redirect to dashboard (prevents form resubmission)
                response.sendRedirect(request.getContextPath() + "/internDashboard.jsp");

            } else {
                request.setAttribute("error", "Incorrect email or password!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } else if ("supervisor".equals(role)) {
            Supervisor_detail supervisor = log.verifySupervisor(email, pass, role);

            if (supervisor != null) {
                // Store supervisor info in session
                session.setAttribute("role", role);
                session.setAttribute("SId", supervisor.getSid());
                session.setAttribute("supervisorName", supervisor.getSname());

                // Fetch dashboard data and store in session
                try (Connection con = DBUtil.getConnection()) {
                    Super_dash_summary sdsDao = new Super_dash_summary();
                    List<DashboardSummary> dashboardData = sdsDao.summary(con, supervisor.getSid());
                    session.setAttribute("dashboardData", dashboardData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Redirect to dashboard (prevents form resubmission)
                response.sendRedirect(request.getContextPath() + "/supervisorDashboard.jsp");

            } else {
                request.setAttribute("error", "Incorrect email or password!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }
}

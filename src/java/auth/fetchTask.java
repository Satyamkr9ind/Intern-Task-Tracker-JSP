package auth;

import Dao.TaskDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson; // make sure Gson library is in your project
import modal.Task;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.GsonBuilder;


@WebServlet(name = "fetchTask", urlPatterns = {"/fetchTask"})
public class fetchTask extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            String internIdStr = request.getParameter("internId");
            if (internIdStr == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"internId missing\"}");
                return;
            }

            int internId = Integer.parseInt(internIdStr);

            TaskDao taskDao = new TaskDao();
            List<Task> taskList = taskDao.fetchTask(internId);
           Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();

          String json = gson.toJson(taskList); 
            response.getWriter().write(json);

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid internId\"}");
        } catch (Exception e) {
            e.printStackTrace(); // This prints the real exception in server logs
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Server error occurred\"}");
        }
    }
}

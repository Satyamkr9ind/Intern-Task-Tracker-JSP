<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modal.Intern_detail" %>
<%@ page import="modal.Taskcount" %>

<%
    // intern details from session
    Intern_detail intern = (Intern_detail) session.getAttribute("intern");

    
    if (intern == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return; // stop page execution
    }

    // Prevent caching to prevent relogin
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Expires", "0"); 

    Taskcount task = (Taskcount) session.getAttribute("taskcount");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Intern Dashboard</title>
    <link rel="stylesheet" href="stylesheet/dashboard.css">

    <!-- Declared internId globally for external access -->
    <script>
        var internId = <%= intern.getIid() %>;
        var role = '<%= session.getAttribute("role") %>';
    </script>
</head>
<body>
<div class="dashboard-container">

    <!-- Sidebar -->
    <div class="sidebar">
        <h2>Hi, <%= intern.getIname() %></h2>
        <h4>Intern id : INT-<%= intern.getIid() %></h4>
        <% if (intern.getSid() != 0) { %>
            <h4>Your Supervisor: <br> <%= intern.getSname() %> (SUP-<%= intern.getSid() %>)</h4>
        <% } else { %>
            <h4>Your Supervisor: <br>
                <div style="color: #d9534f; font-size: smaller; font-style: italic">
                    Not yet assigned
                </div>
            </h4>
        <% } %>

        <form action="<%= request.getContextPath() %>/logout" method="post">
            <input type="submit" value="Logout">
        </form>

        <% if (task != null) { %>
            <ul>
                <li>Total Tasks Assigned: <%= task.getTotal() %></li>
                <li>Completed Tasks: <%= task.getCompleted() %></li>
                <li>Pending Tasks: <%= task.getPending() %></li>
            </ul>
        <% } %>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <h1>My Task Progress</h1>
        <% if (task != null) {
            int totalTasks = task.getTotal();
            int completedTasks = task.getCompleted();
            int progressPercent = totalTasks > 0 ? (int) ((completedTasks * 100.0) / totalTasks) : 0;
        %>
            <div class="progress-container">
                <div class="progress-bar" style="width: <%= progressPercent %>%;"><%= progressPercent %>%</div>
            </div>
        <% } %>

        <h3>Task Details</h3>
        <div id="table-container" class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Task Number</th>
                        <th>Task</th>
                        <th>Description</th>
                        <th>Assigned on</th>
                        <th>Last Date of Submission</th>
                        <th>Status</th>
                        <th>Update Status</th>
                        <th>Supervisor Remark</th>
                        <th>Your Remark</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="taskTableBody">
                    <!-- dynamically populate rows -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="script/dashboard.js"></script>

<!-- Autoload task-->
<script>
    document.addEventListener("DOMContentLoaded", () => {
        if (internId !== 0) { 
           Task(internId); // populate tasks after page loads
        } else {
            console.warn("Intern ID not found!");
        }
    });
</script>

</body>
</html>

<%@page import="modal.Supervisor_detail" %>
<%@page import="java.util.List" %>
<%@page import="modal.DashboardSummary" %>

<%
    // Check  supervisor session
    String supName = (String) session.getAttribute("supervisorName");
    Integer supId = (Integer) session.getAttribute("SId");

    if (supName == null || supId == null) {
        // Redirect to login page if session expired
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return; // stop  page execution
    }

    // Prevent back button relogin
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Expires", "0"); 

    // Get dashboard data from session 
    List<DashboardSummary> ds = (List<DashboardSummary>) session.getAttribute("dashboardData");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Supervisor Dashboard</title>
    <link rel="stylesheet" href="stylesheet/dashboard.css">
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <h2>Hi, <%= supName %></h2>
            <h4>Supervisor id : SUP-<%= supId %></h4>

            <form method="POST" action="logout"><input type="submit" value="logout"></form>
            <h3>Search Intern</h3>
            <input type="text" id="searchBox" placeholder="Search by name, ID or task" onkeyup="filterInterns()" style="width:90%; padding:5px;">

            <h3>My Interns</h3>
            <ul id="internList" style="max-height:300px; overflow-y:auto; list-style:none; padding-left:0;">
                <%
                    if (ds != null && !ds.isEmpty()) {
                        for(DashboardSummary d : ds){
                %>
                    <li data-id="INT-<%=d.getInternId()%>" data-name="<%=d.getInternName()%>">
                        <strong><%=d.getInternName()%></strong> (INT-<%=d.getInternId()%>)<br>
                        Assigned: <%=d.getInternTotalTasks()%>  Completed: <%=d.getInternTasksCompleted()%><br>
                        Pending: <%=d.getInternPendingTasks()%><br>
                        <button onclick="assignTask('<%=d.getInternId()%>')">Assign Task</button>
                        <button onclick="viewIntern('<%=d.getInternId()%>')">View</button>
                    </li>
                <%
                        }
                    } else {
                %>
                    <li>No interns assigned yet.</li>
                <%
                    }
                %>
            </ul>
        </div>

        <!-- Main Content -->
        <div class="main-content" id="Super-mainContent" style="height: 97vh;">
            <h1>Supervisor Dashboard</h1>
            <%
                int totalIntern = 0, totalTaskAssigned = 0, totalTaskCompleted = 0, totalTaskPending = 0;
                if (ds != null && !ds.isEmpty()) {
                    DashboardSummary first = ds.get(0); 
                    totalIntern = first.getTotalIntern();
                    totalTaskAssigned = first.getTotalTaskAssigned();
                    totalTaskCompleted = first.getTotalTaskCompleted();
                    totalTaskPending = first.getTotalTaskPending();
                }
            %>
           <div class="summary-container">
                <div class="summary-row">
                    <div class="summary-item">Total Interns: <%= totalIntern %></div>
                    <div class="summary-item">Total Task Assigned: <%= totalTaskAssigned %></div>
                </div>
                <div class="summary-row">
                    <div class="summary-item">Total Task Completed: <%= totalTaskCompleted %></div>
                    <div class="summary-item">Total Task Pending: <%= totalTaskPending %></div>
                </div>
            </div>

            <p>Here you can manage tasks and see progress of all interns assigned to you.</p>

            <h2>Assign Task</h2>
<form id="assignForm" action="assignTask" method="post" autocomplete="off" class="assign-task-form">
    <div class="row">
        <div class="form-group">
            <label for="internId">Intern Id:</label>
            <input type="text" id="internId" name="internId" readonly>
        </div>
        <div class="form-group">
            <label for="taskName">Task Name:</label>
            <input type="text" id="taskName" name="taskName" placeholder="Enter task title" required>
        </div>
    </div>

    <div class="form-group full-width">
        <label for="taskDesc">Task Description:</label>
        <textarea id="taskDesc" name="taskDesc" rows="3" placeholder="Enter task details" required></textarea>
    </div>

    <div class="row">
        <div class="form-group">
            <label for="assignedDate">Assigned Date:</label>
            <input type="datetime-local" id="assignedDate" name="assignedDate" required>
        </div>
        <div class="form-group">
            <label for="submissionDate">Submission Date:</label>
            <input type="datetime-local" id="submissionDate" name="submissionDate" required>
        </div>
    </div>

    <div class="form-submit">
        <input type="submit" value="Assign Task">
    </div>
</form>

<div id="alertMessage" class="alert-message" style="display:none;"></div>

    <!-- Modal Box-->

<div id="taskModal" class="modal">

    <!-- Modal Content -->
    <div class="modal-content">

        <!-- Close Button -->
        <span id="closeModal" class="close">&times;</span>

        <h3>Task Details</h3>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Task Number</th>
                        <th>Task</th>
                        <th>Description</th>
                        <th>Assigned on</th>
                        <th>Last Date of Submission</th>
                        <th>Status</th>
                        <th>Intern remark</th>
                        <th>Your Remark</th>
                        <th>Add Remark</th>
                        <th>Delete Task</th>
                    </tr>
                </thead>
                <tbody id="taskTableBody">
                    <!-- Task rows populated using JS -->
                </tbody>
            </table>
        </div>

    </div>

</div>

<script src="script/dashboard.js"></script>    
</body>
</html>

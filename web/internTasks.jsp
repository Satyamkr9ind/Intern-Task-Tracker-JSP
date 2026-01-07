<%@page import="java.util.List"%>
<%@page import="modal.Task"%>

<%
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>

<table border="1" style="width:100%; text-align:center; border-collapse: collapse;">
   <tr>
        <th>Task Number</th>
        <th>Task</th>
        <th>Description</th>
        <th>Assigned on</th>
        <th>Last Date of Submission</th>
        <th>Status</th>
        <th>Update Status</th>
        <th>Remark</th>
        <th>Action</th>
    </tr>
<%
    if (tasks != null && !tasks.isEmpty()) {
        for (Task t : tasks) {
%>
<tr>
    <td>Task-<%= t.getId() %></td>
    <td><%= t.getTitle() %></td>
    <td><%= t.getDescription() %></td>
    <td><%= t.getCreatedAt() %></td>
    <td><%= t.getDateOfSubmission() %></td>
    <td><%= t.getStatus() %></td>
    <td>
        <input type="checkbox" name="status" value="completed"
               <%= t.getStatus().equalsIgnoreCase("completed") ? "checked" : "" %>
               onchange="this.form.submit()">
    </td>
    <td colspan="2">
        <form action="${pageContext.request.contextPath}/addRemark" method="post"
              style="display:flex; align-items:center; gap:15px;">
            <input type="text" name="remark" maxlength="100" value="<%= t.getRemark() %>"
                   style="flex:1; min-width:150px; margin-left:10px;">
            <input type="hidden" name="taskId" value="<%= t.getId() %>">
            <input type="submit" value="Add Remark" style="margin-bottom:15px;">
        </form>
    </td>
</tr>
<%
        }
    } else {
%>
<tr>
    <td colspan="9">No Task Assigned!</td>
</tr>
<% } %>
</table>

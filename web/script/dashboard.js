/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function showMsg(message) {
    alert(message);
}

// Assign current date 
function setCurrentDateTime() {
    const now = new Date();

    const formattedDate =
        now.getFullYear() + "-" +
        String(now.getMonth() + 1).padStart(2, "0") + "-" +
        String(now.getDate()).padStart(2, "0") + "T" +
        String(now.getHours()).padStart(2, "0") + ":" +
        String(now.getMinutes()).padStart(2, "0");

    document.getElementById("assignedDate").value = formattedDate;
}
setCurrentDateTime();

// Assigning task
function assignTask(id){
    document.getElementById('internId').value = id;
}

// Search box
function filterInterns() {
    const query = document.getElementById("searchBox").value.toLowerCase();
    const interns = document.querySelectorAll("#internList li");

    interns.forEach(function(intern) {
        const text = intern.innerText.toLowerCase();
        intern.style.display = text.includes(query) ? "" : "none";
    });
}

// Assign task form submit
document.getElementById("assignForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const form = this;
    const formData = new FormData(form);

    const params = new URLSearchParams();
    formData.forEach((value, key) => {
        params.append(key, value);
    });

    const msgDiv = document.getElementById("alertMessage");

    fetch("assignTask", { 
        method: "POST", 
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params.toString()
    })
    .then(response => response.text())
    .then(msg => {
        window.alert(msg);
    })
    .catch(err => {
        console.error(err);
        msgDiv.style.color = "red";
        msgDiv.style.display = "block";
        msgDiv.innerText = "Error assigning task.";
    });
});

// Fetch tasks for supervisor view
function viewIntern(internId) {
    const params = "internId=" + encodeURIComponent(internId);

    fetch("fetchTask", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(response => {
        if (!response.ok) throw new Error("HTTP error! Status: " + response.status);
        return response.json();
    })
    .then(data => {
        console.log(data);
        populateTable(data);
    })
    .catch(error => {
        console.error("Error:", error);
    });
}

// Populate table for supervisor
function populateTable(tasks) {
    const modal = document.getElementById("taskModal");
    const tableBody = document.getElementById("taskTableBody");

    tableBody.innerHTML = "";
 if (tasks.length !==0){
    tasks.forEach(task => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>Task-${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${new Date(task.createdAt).toLocaleString()}</td>
            <td>${new Date(task.dateOfSubmission).toLocaleString()}</td>
            <td>${task.status}</td>
            <td style="width: 50%;">
            <textarea class="internRemark" readonly>${task.Intern_remark}</textarea>
            </td>
            <td style="width: 50%;">
            <textarea class="supervisorRemark">${task.supervisor_remark}</textarea>
            </td>
            <td><button onclick="addRemark('${task.id}', this)">Save</button></td>
            <td><button onclick="deleteTask(${task.id}, this)">Delete</button></td>
        `;

        tableBody.appendChild(row);
    });
    }else{
    tableBody.innerHTML = `
            <tr>
                <td colspan="10" style="text-align:center;">You have not assigned any Task !</td>
            </tr>
        `;
}


    modal.style.display = "block";
    document.body.style.overflow = "hidden";

    const closeBtn = document.getElementById("closeModal");
    closeBtn.onclick = () => { 
        modal.style.display = "none"; 
        document.body.style.overflow = "";
    };

    window.onclick = event => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    };
}

// Delete task
function deleteTask(taskId, btn) {
    if (!confirm("Are you sure you want to delete this task?")) return;

    const params = "taskId=" + encodeURIComponent(taskId);

    fetch("deleteTask", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(response => {
        if (!response.ok) throw new Error("Server returned status: " + response.status);
        return response.text();
    })
    .then(data => {
        window.alert(data);
        btn.closest("tr").remove();
    })
    .catch(error => console.error(error));
}

// Add supervisor remark
function addRemark(taskId, btn) {
    const row = btn.closest("tr");
    const remarkInput = row.querySelector(".supervisorRemark");
    const remarkValue = remarkInput.value.trim();

    const isEmpty = remarkValue === "";
    const confirmMsg = isEmpty
        ? "Remark is empty. Are you sure you want to delete it?"
        : "Are you sure you want to add this remark?";

    if (!confirm(confirmMsg)) return;

    const params =
        "taskId=" + encodeURIComponent(taskId) +
        "&remark=" + encodeURIComponent(remarkValue);

    fetch("SupervisorRemark", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(response => {
        if (!response.ok) throw new Error("Server returned status: " + response.status);
        return response.text();
    })
    .then(data => {
        window.alert(data);
        remarkInput.value = remarkValue;
    })
    .catch(error => console.error("Error:", error));
}

// Intern dashboard fetch
function Task(internId) {
    const params = "internId=" + encodeURIComponent(internId);

    fetch("fetchTask", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(response => {
        if (!response.ok) throw new Error("HTTP error! Status: " + response.status);
        return response.json();
    })
    .then(data => {
        populatetask(data);
    })
    .catch(error => console.error("Error:", error));
}

// Populate table for intern
function populatetask(tasks) {
    //const modal = document.getElementById("taskModal");
    const tableBody = document.getElementById("taskTableBody");

    tableBody.innerHTML = "";
    if (tasks.length !==0){
    
    tasks.forEach(task => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>Task-${task.id}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${new Date(task.createdAt).toLocaleString()}</td>
            <td>${new Date(task.dateOfSubmission).toLocaleString()}</td>
            <td class="status">${task.status}</td>
            <td>
            <select class="statusSelect" data-task-id="${task.id}">
            <option value="Pending" ${task.status === 'pending' ? 'selected' : ''}>Pending</option>
           <option value="Completed" ${task.status === 'completed' ? 'selected' : ''}>Completed</option>
           </select>
           <button onclick="updateStatus('${task.id}', this)">Save</button>
            </td>

            <td style="width: 50%;">
  <textarea class="supervisorRemark" readonly>
    ${task.supervisor_remark}
  </textarea>
</td>

<td style="width: 50%;">
  <textarea class="internRemark">
    ${task.Intern_remark}
  </textarea>
</td>
            <td><button onclick="add_Intern_Remark('${task.id}', this)">Save</button></td>
        `;

        tableBody.appendChild(row);
    });
}else{
    tableBody.innerHTML = `
            <tr>
                <td colspan="10" style="text-align:center;">No Task Assigned!</td>
            </tr>
        `;
}
}

// Add intern remark
function add_Intern_Remark(taskId, btn) {
    const row = btn.closest("tr");
    const remarkInput = row.querySelector(".internRemark");
    const remarkValue = remarkInput.value.trim();

    const isEmpty = remarkValue === "";
    const confirmMsg = isEmpty
        ? "Remark is empty. Are you sure you want to delete it?"
        : "Are you sure you want to add this remark?";

    if (!confirm(confirmMsg)) return;

    const params =
        "taskId=" + encodeURIComponent(taskId) +
        "&remark=" + encodeURIComponent(remarkValue);

    fetch("addRemark", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(response => {
        if (!response.ok) throw new Error("Server returned status: " + response.status);
        return response.text();
    })
    .then(data => {
        window.alert(data);
        remarkInput.value = remarkValue;
    })
    .catch(error => console.error("Error:", error));
}

// Event delegation for status update
function updateStatus(taskId, btn) {
    const row = btn.closest("tr");
    const select = row.querySelector(".statusSelect");
    const newStatus = select.value;
    const params =
        "taskId=" + encodeURIComponent(taskId) +
        "&status=" + encodeURIComponent(newStatus);
    // Update the visible status cell immediately
    const statusCell = row.querySelector(".status"); 
    statusCell.innerHTML = newStatus;


    fetch("updateStatus", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(response => {
        if (!response.ok) throw new Error("Failed to update status");
        return response.text();
    })
    .then(data => {
               
        alert(data); 
    })
    .catch(err => {
        console.error(err);
        alert("Status update failed");
    });
}

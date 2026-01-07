/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function validateRole() {
    let roles = document.getElementsByName("role");
    let selected = false;
//iterate over all radio button
    for (let i = 0; i < roles.length; i++) {
        if (roles[i].checked) {
            selected = true;
            break;
        }
    }

    if (!selected) {
        alert("Please select a role (Supervisor or Intern)");
        return false;
    }
    return true;
}

# Intern Task Tracker (JSP & Servlet)

##  Project Overview
The **Internship Management System** is a role-based web application developed using **JSP, Servlets, JDBC, HTML, CSS, and JavaScript**.  
It is designed to manage interns, supervisors, and tasks efficiently in an internship environment.

Supervisors can assign and monitor tasks, while interns can track task progress, update status, and view remarks through a personalized dashboard.

---

## Tech Stack
- **Frontend:** JSP, HTML5, CSS3, JavaScript  
- **Backend:** Java Servlets, JDBC  
- **Database:** MySQL  
- **Server:** Apache Tomcat  
- **Architecture:** MVC (Model–View–Controller)

---

## Database Configuration
- **Database Name:** `project`

### Tables:
1. `intern`
2. `supervisor`
3. `task`

---

##  User Roles & Features

### Intern
- Secure login with session management
- View intern profile and Intern ID
- View assigned supervisor details
- View all assigned tasks
- Track progress using a progress bar
- Update task status (Pending / Completed)
- Add personal remarks
- View supervisor remarks
- Secure logout

---

### Supervisor
- Secure login
- View assigned interns
- Assign tasks to interns
- Set deadlines
- Monitor task progress
- Add remarks on intern work

---

## Key Features
-  Session-based authentication
-  Unauthorized access prevention
-  Browser back-button cache control
-  Dynamic task progress calculation
-  Role-based dashboards
-  AJAX-based task loading
-  MVC architecture implementation

---

##  Intern Dashboard Highlights
- Intern greeting with Intern ID
- Supervisor assignment status
- Task summary:
  - Total tasks
  - Completed tasks
  - Pending tasks
- Visual progress bar
- Dynamic task table including:
  - Task details
  - Description
  - Assigned date
  - Submission deadline
  - Status update option
  - Supervisor & intern remarks

---

## Project Structure
```

project/
│
├── src/
│   ├── controller/      # Servlets
│   ├── modal/           # Java Beans
│   └── dao/             # Database operations
│
├── web/
│   ├── jsp/
│   │   ├── login.jsp
│   │   ├── internDashboard.jsp
│   │   └── supervisorDashboard.jsp
│   ├── script/
│   │   └── dashboard.js
│   └── stylesheet/
│       └── dashboard.css
│
└── README.md

```

---

##  How to Run the Project
1. Clone this repository
2. Open the project in **NetBeans / Eclipse**
3. Configure **Apache Tomcat**
4. Create MySQL database `project`
5. Create tables `intern`, `supervisor`, `task`
6. Update JDBC credentials in the project
7. Run the project on the server

---

## Future Enhancements
- Password encryption
- Admin module
- Email notifications
- File upload for task submission
- Cloud deployment

---



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

import java.time.LocalDateTime;

/**
 *
 * @author satya
 */
public class Task {
    private int id;
    private String title;
    private String description;
    private int assignedTo;
    private int assignedBy;
    private String status;
    private String Intern_remark;
    private LocalDateTime createdAt;
    private LocalDateTime dateOfSubmission;
    private String supervisor_remark;
    
    public Task(){}
    public Task(int id, String title, String description,
                int assignedTo, int assignedBy,
                String status, String Intern_remark,String supervisor_remark,
                LocalDateTime createdAt, LocalDateTime dateOfSubmission){
        
                this.id = id;
                this.title = title;
                this.description = description;
                this.assignedTo = assignedTo;
                this.assignedBy = assignedBy;
                this.status = status;
                this.Intern_remark = Intern_remark;
                this.createdAt = createdAt;
                this.dateOfSubmission = dateOfSubmission;
                this.supervisor_remark=supervisor_remark;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getAssignedTo() { return assignedTo; }
    public int getAssignedBy() { return assignedBy; }
    public String getStatus() { return status; }
    public String getIntern_remark() { return Intern_remark; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getDateOfSubmission() { return dateOfSubmission; }
    public String getSupervisor_remark(){return supervisor_remark;}
}

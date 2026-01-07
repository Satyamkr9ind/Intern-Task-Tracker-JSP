/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;
public class DashboardSummary {
    private int totalIntern;
    private int totalTaskAssigned;
    private int totalTaskCompleted;
    private int totalTaskPending;
    private int internId;
    private String internName;
    private int internTotalTasks;
    private int internTasksCompleted;
    private int internPendingTasks;

    // Constructor
    public DashboardSummary(int totalIntern, int totalTaskAssigned, int totalTaskCompleted, int totalTaskPending,
               int internId, String internName, int internTotalTasks, int internTasksCompleted, int internPendingTasks) {
        this.totalIntern = totalIntern;
        this.totalTaskAssigned = totalTaskAssigned;
        this.totalTaskCompleted = totalTaskCompleted;
        this.totalTaskPending = totalTaskPending;
        this.internId = internId;
        this.internName = internName;
        this.internTotalTasks = internTotalTasks;
        this.internTasksCompleted = internTasksCompleted;
        this.internPendingTasks = internPendingTasks;
    }

    // Getters
    public int getTotalIntern() { return totalIntern; }
    public int getTotalTaskAssigned() { return totalTaskAssigned; }
    public int getTotalTaskCompleted() { return totalTaskCompleted; }
    public int getTotalTaskPending() { return totalTaskPending; }

    public int getInternId() { return internId; }
    public String getInternName() { return internName; }
    public int getInternTotalTasks() { return internTotalTasks; }
    public int getInternTasksCompleted() { return internTasksCompleted; }
    public int getInternPendingTasks() { return internPendingTasks; }
}

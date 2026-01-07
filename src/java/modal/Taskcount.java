/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

/**
 *
 * @author satya
 */
public class Taskcount {
    int total;
    int pending;
    int completed;
    public Taskcount(){};
    public Taskcount(int total,int pending,int completed){
            this.total = total;
            this.pending = pending;
            this.completed = completed;
        }
    public int getTotal(){return this.total;}
    public int getPending(){return this.pending;}
    public int getCompleted(){return this.completed;}
}

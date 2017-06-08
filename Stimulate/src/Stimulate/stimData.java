/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

/**
 *
 * @author User
 */
public class stimData {
    int location;
    double time;
    String fileName;
    int event;
    
    public stimData(int loc, double t, String f, int e){
        this.location = loc;
        this.time = t;
        this.fileName = f;
        this.event = e;
    }
    
    public void setLocation(int l){
        this.location = l;
    }
    
    public void setEvent(int e){
        this.event = e;
    }
    
    public void setTime(double t){
        this.time = t;
    }
    
    public void setFileName(String l){
        this.fileName = l;
    }
    
    public int getLocation(){
        return location;
    }
    
    public double getTime(){
        return time;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public int getEvent(){
        return event;
    }
    
    public void printSD(){
        System.out.println(this.location + " " + this.time + " " + this.event + " " + this.fileName);
    }
    
   
}

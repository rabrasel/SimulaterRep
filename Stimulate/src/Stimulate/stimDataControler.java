/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author User
 */
public class stimDataControler {
    //stimData[] stimArray;
    ArrayList<stimData> stimArray = new ArrayList<>();
    boolean isDirty;
    static String  file = "";
    String  nickName;
    static boolean saveCanceled = false;
    public final String FS = "\b"; //for seperating the different fields on the disk
    
    
    public stimDataControler(String name){
        this.isDirty = true;
       this.nickName = name;
       //count++;

    }
    
    public static void setSaveCanceled(boolean sc){
       saveCanceled = sc;
    }
    
    public static void setFile(String f){
        file = f;
    }
    
    public void setFile2(String f){
        file = f;
    }
    
    //  add more simData - incriment count
    public void addStimData(int l, double t, String f, int e){
        this.stimArray.add(new stimData(l,t,f,e));
    }
    
    //remove an element from the arraylist
    public void deleteStimData(int c){
        stimArray.remove(c);
    }
    
    public int getCount(){
        return stimArray.size();
    }
    
    public void printArray(){
        System.out.println("the count is " + getCount());
        for (int i = 0; i<getCount(); i++){
            System.out.println(stimArray.get(i).getLocation() + " " + stimArray.get(i).getTime() + " " + stimArray.get(i).getFileName());
        }
    }
 
    
    //write file to disk
    public void toDisk(boolean nf){
        
        if((file.length() == 0) || (nf)){  
            fileDialog.isSave = true;
            fileDialog x = new fileDialog(new javax.swing.JFrame(), true);
            x.setVisible(true);
            
        }
        
        if (!saveCanceled){
           try (PrintWriter out = new PrintWriter(this.file)) {

               for (int i = 0; i < getCount(); i++){
                   out.println(global.myStimData.stimArray.get(i).getLocation() +
                   FS + global.myStimData.stimArray.get(i).getTime() +
                   FS + global.myStimData.stimArray.get(i).getFileName()  +
                   FS + global.myStimData.stimArray.get(i).getEvent());
               }
               out.close();
           } catch (IOException e){
               System.out.println("no file joy for you buddy    " + e.getMessage());
               System.out.println("the file is   " + file);
           }
           
           
        }
        saveCanceled = false;
    }
    
    public void fromDisk(){
        fileDialog.isOpen = true;
        
        fileDialog x = new fileDialog(new javax.swing.JFrame(), true);
        x.setVisible(true);
        
        if(!saveCanceled){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            nickName = "Created from New" + date.toString();
            global.myStimData.stimArray.clear();

            BufferedReader br;
            String line;
            int l;
            double t;
            String f;

            try {
                br = new BufferedReader(new FileReader(file));
                line = br.readLine();

                while (line != null){
                    String[] ss;
                    
                    ss = line.split(FS);
  for(String s:ss){
 System.out.println(s);
  }
                    global.myStimData.addStimData(Integer.parseInt(ss[0]), 
                            Double.parseDouble(ss[1]), ss[2], Integer.parseInt(ss[3]));
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("No joy in creating this buffer   " 
                        + e.getMessage());
            }
            stimulatForm.fillTable();
        }
        saveCanceled = false;
        
    }
}

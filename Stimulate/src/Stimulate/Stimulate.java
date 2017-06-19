/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

import java.awt.Color;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;


/**
 *
 * @author User
 */
public class Stimulate {

    

        static {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
  }
        
      public static stimulatForm myform;  

  public static void main(String[] args) {
      
            
                /**
                 *
                 */
        myform = new stimulatForm();
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
           System.out.println("Problem with look and feel   "  + e.getMessage());
        }

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
        defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

        
        showWindow();

         
        TableColumn a = new TableColumn();
        
        global x = new global("/Users/user/StimulatorStuff/Alan"); // x is used just to initiate the global variable
        
        global.myStimData.addStimData(1, 10, "/Users/user/Desktop/desktop/11-24-09_1756.jpeg", 7);
        global.myStimData.addStimData(2, 10, "/Users/user/Pictures/microScope.png", 4);
        global.myStimData.addStimData(3, 10, "/Users/user/Music/iTunes/iTunes Music/Unknown Artist/Unknown Album/kellys-heroes.mp3", 2);

        
        stimulatForm.fillTable();
        
//        hideWindow();
//            
//        try {
//                openFiles tryThis = new openFiles("/Users/user/Pictures/microScope.png");
//                tryThis.run();
//                TimeUnit.SECONDS.sleep(3);
//                //tryThis.close();
//                openFiles tryThis2 = new openFiles("/Users/user/Desktop/desktop/11-24-09_1756.jpeg");
//                tryThis2.run();
//                tryThis.close();
//                TimeUnit.SECONDS.sleep(3);
//                
//                openFiles tryThis3 = new openFiles("/Users/user/Music/iTunes/iTunes Music/Unknown Artist/Unknown Album/kellys-heroes.mp3");
//                tryThis3.run();
//                tryThis2.close();
//                TimeUnit.SECONDS.sleep(10);
//                tryThis3.close();
//                
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Stimulate.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        showWindow(); 
       
    }
  
  
    public static void hideWindow(){
        myform.setVisible(false);
    }
    
    public static void showWindow(){
        myform.setVisible(true);
    }
    
    
  }
  

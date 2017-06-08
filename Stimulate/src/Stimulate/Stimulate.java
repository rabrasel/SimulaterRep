/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPort;


/**
 *
 * @author User
 */
public class Stimulate {

    

        static {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
  }
        
        public static stimulatForm myForm = new stimulatForm();

  public static void main(String[] args) {
/**
     * @param args the command line arguments
     */
        gpio8 io;
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
        
        global.myStimData.addStimData(1, 4, "D:\\my documents\\BioTech\\Axima\\Chmelik Axima CFR.pdf", 7);
        global.myStimData.addStimData(2, 4, "D:\\my documents\\BioTech\\Axima\\Axima QIT CMYK.tif", 4);
        global.myStimData.addStimData(3, 4, "D:\\my documents\\BioTech\\Axima\\axima_us[1].mpeg", 2);
        
        
        stimulatForm.fillTable();
        
        String port = "";
        
            String[] portNames = SerialPortList.getPortNames();
        
        for (int i = 0; i < portNames.length; i++){
            
            port = portNames[i];
        }
        boolean yn = false;
        SerialPort sp = new SerialPort(port);
     System.out.println(port);
            try {
                sp.openPort();
                if (sp.isOpened()){
                    System.out.println("is open");
                }
                sp.setFlowControlMode(0);
                
                sp.writeString("gpio iodir 00\r");
       
                System.out.println("iodir set" );  //<<<<<<< it never gets here
                
                System.out.println("the read is = " + sp.readHexString());
                sp.closePort();
                
                

            } catch (SerialPortException ex) {
                Logger.getLogger(Stimulate.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("the problem is " + ex);
            }
            
    }
    public static void hideWindow(){
        myForm.setVisible(false);
    }
    
    public static void showWindow(){
        myForm.setVisible(true);
    }//    io = new gpio8();
//        try 
//            io.writeSt("ver\r\n");
//        } catch (SerialPortException ex) {
//            Logger.getLogger(Stimulate.class.getName()).log(Level.SEVERE, null, ex);
//        }
  }
  

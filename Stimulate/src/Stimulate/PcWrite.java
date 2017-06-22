/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author user
 */
public class PcWrite {
    
    SerialPort sp;
    Timer tm2;
    
    public PcWrite(){
        
    }
    
    public void Write(String s) throws SerialPortException{
        
        String port = "", outString;
        
        String[] portNames = SerialPortList.getPortNames();
        
        for (int i = 0; i < portNames.length; i++){
            port = portNames[i];
        }
        sp = new SerialPort(port);


        s ="\rgpio writeall 0" + s + "\r";
        try {
                sp.openPort();
                if (sp.isOpened()){
                    System.out.println("is open");
                }
                sp.setFlowControlMode(0);
                sp.writeString("\rgpio iodir 00\r");
                sp.writeString(s);
                sp.closePort();
                

            } catch (SerialPortException ex) {
                System.out.println("the problem is " + ex);
            }
        tm2 = new Timer(100,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s ="\rgpio writeall 00\r";
                try {
                    sp.openPort();
                    sp.writeString("\rgpio iodir 00\r");
                    sp.writeString(s);
                    sp.closePort();
                } catch (SerialPortException ex) {
                    Logger.getLogger(PcWrite.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                tm2.stop();
            }
        });
        
        tm2.start();
    }
    
}

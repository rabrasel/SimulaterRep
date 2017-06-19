/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author user
 */
public class PcWrite {
    
    SerialPort sp;
    
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
    System.out.println(s);
                sp.writeString("\rgpio iodir 00\r");
                sp.writeString(s);
       
                System.out.println("iodir set" );  //<<<<<<< it never gets here
                
                System.out.println("the read is = " + sp.readHexString());
                sp.closePort();
                

            } catch (SerialPortException ex) {
                System.out.println("the problem is " + ex);
            }
    }
    
}

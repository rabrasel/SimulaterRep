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
        
//        sp.openPort();
//        if(sp.isOpened()){
//            System.out.println("opened the pc port   "   + sp.getPortName());
//        } else {
//            System.out.println("did not open the pc port  " + sp.getPortName());
//        }
//        sp.setFlowControlMode(0);
//        
//        outString = "\r" + s + "\r";
// System.out.println(outString);
//        boolean b = sp.writeString(outString);
//        if(b){
//            System.out.println("we wrote pc string");
//        } else {
//            System.out.println("We didn't do the pc string");
//        }
//        //sp.purgePort(SerialPort.PURGE_RXCLEAR & SerialPort.PURGE_TXCLEAR);
//        sp.closePort();

        s = "\r" + s + "\r";
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

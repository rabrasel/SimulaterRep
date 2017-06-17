package Stimulate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;  /* Calls the respective serial port */
import jssc.SerialPortException; /* initializes unmathced catagories as string */
/**
 *
 * @author rabrasel
 */
public class gpio8 {
    private String com = "/dev/tty.usbmodem14231";
    private SerialPort port;
    private String outString = "";
    
    public gpio8(String c){
        this.com = c;
        port = new SerialPort(com);
    }
    
    public gpio8(){
        //use default com
        port = new SerialPort(com);
         
    }
    
    public void writePort(int p){
     System.out.println("p = " + p);
        try {
            /*Try to open port*/
                if(port.openPort() == true){
                    System.out.println("Port " + com + " opened successfully...");
                }
                else{
                    System.exit(0);
                }  
            outString = "gpio iodir 00\r";
            port.writeString(outString);
            outString = "gpio writeall 0" + p + "\r";
            port.writeString("\r");
            port.writeString(outString);
            //Thread.sleep(100);
            port.purgePort(SerialPort.PURGE_RXCLEAR & SerialPort.PURGE_TXCLEAR);
            port.closePort();
        } catch (SerialPortException ex) {
            Logger.getLogger(gpio8.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
                
    }
    
    public void writeSt(String s) throws SerialPortException{
        if(port.openPort() == true){
                    System.out.println("Port " + com + " opened successfully...");
                }
                else{
                    System.exit(0);
                }
        port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        
     System.out.println("Flow control");
     System.out.println(port.getPortName());
     System.out.println(port.readString());
        port.writeString(s);
        System.out.println("did writeString");
    }
    
}

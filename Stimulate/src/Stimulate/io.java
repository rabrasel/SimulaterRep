/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author user
 */
public class io {
    
    public io(){
        
    }
    
    public void Write(String s){
        if(os.isMac()){
            MacWrite x = new MacWrite();
            try {
                x.Write(s);
            } catch (InterruptedException ex) {
                Logger.getLogger(io.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Didn't do MacWrite because " + ex);
            }
        } else {
            PcWrite x = new PcWrite();
            try {
                x.Write(s);
            } catch (SerialPortException ex) {
                Logger.getLogger(io.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Didn't do PcWrite because " + ex);
            }
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stimulate;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author user
 */
public class openFiles {
    boolean isSound = false;
    BasicPlayer playSound = new BasicPlayer();
    
    public openFiles(String sFile, JLabel jl){
        String[] f = sFile.split("[.]");

        switch (f[1].toLowerCase()){
            case "jpg": case "gif": case "png": case "jpeg":
                doImage(sFile, jl);
                break;
            
            case "txt":
        {
            try {
                doText(sFile, jl);
            } catch (IOException ex) {
                Logger.getLogger(openFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
             
            case "wav": case "mp3": case "aiff":
                isSound = true;
                jl.setText("");
                jl.setIcon(null);
                
                playSound.play(sFile);
                break;
                
            default:
                break;
        }
    }
    
    public void close(){
        if(isSound){
            playSound.stop();
        }
    }
    
    private void doImage(String s, JLabel jl){
        jl.setText("");
        ImageIcon icon = new ImageIcon(s);
        Dimension newSize = picRatio(jl,icon);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance((int) newSize.getWidth(), (int) newSize.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        jl.setIcon(newImc);
        
    }
    
     private void doText(String sFile, JLabel jl) throws IOException {
        jl.setIcon(null);
        String s = readText(sFile, jl);
        jl.setText("<html><div style='text-align: center;'>" + s.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</div></html>");
        
     }
  
    private String readText(String sFile, JLabel jl) throws FileNotFoundException, IOException{
        ArrayList<String> sb = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(sFile));
        String line = br.readLine();
        sb.add(line + "\n");
        while(((line = br.readLine()) != null)){
                //JLabel lab = new JLabel(line, JLabel.CENTER);
                sb.add(line + "\n");
         }
        String s = "";
        int largest = 0;
        for(int i = 0; i<sb.size();i++){
            s = s + sb.get(i);
            if(i>0){
                if(sb.get(i).length() > sb.get(i -1).length()){
                    largest = i;
                }
            }
        }
        Dimension r = Toolkit.getDefaultToolkit().getScreenSize();

        Dimension d = new Dimension();
        int fSize = 0;
        for(fSize = 10; fSize < 250; fSize++){
            Font myFont = new Font(jl.getName(), Font.PLAIN, fSize);
            d = getTextSize(jl,myFont,sb.get(largest));
            if(r.getWidth() <= d.getWidth() | r.getHeight() <= d.getHeight()){
                fSize--;
                break;
            }
        }
        
        Font myFont = new Font(jl.getName(),Font.PLAIN,fSize);
        jl.setFont(myFont);

        return s;
    }
    
    private Dimension getTextSize(JLabel l, Font f, String t) {
        Dimension size=new Dimension();
        Graphics g = null;
        FontMetrics mt;
        mt = new FontMetrics(f) {};      
        Rectangle2D bounds = mt.getStringBounds(t, null);
        size.setSize(bounds.getWidth(), bounds.getHeight());
        
        return size;
    }
    
    private Dimension picRatio(JLabel l, ImageIcon img){
        double result;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double percentH = screenSize.getHeight()/img.getIconHeight();
        double percentW = screenSize.getWidth()/img.getIconWidth();
        if (percentH < percentW){
            result = percentH;
        } else {
            result = percentW;
        }
        int newW = (int) (img.getIconWidth() * result);
        int newH = (int) (img.getIconHeight() * result);
        return new Dimension(newW, newH );
    }
}

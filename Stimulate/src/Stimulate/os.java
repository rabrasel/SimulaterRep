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
public final class os {
    private static String OS = null;
    public static String getOS()
    {
        if(OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }
    
    public static boolean isPC()
    {
        return getOS().startsWith("Win");
    }
    
    public static boolean isMac()
    {
        return getOS().startsWith("Mac");
    }
}

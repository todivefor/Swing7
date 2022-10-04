/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author peterream
 * 
 */

public class Utils {
    
    /**
     * 
     * This class returns the extension of a file or null if there is none.
     * @param name - file name
     * @return - Extension / null
     */
    public static String getFileExtension(String name) {
        
        int pointIndex = name.lastIndexOf(".");
        
        if (pointIndex == -1) {                                                     // Find "." in file name?
            return null;                                                            // No
        }
        
        if (pointIndex == name.length() - 1) {                                      // End in ","
            return null;                                                            // Yes, no ext
        }
        
        return name.substring(pointIndex + 1, name.length());                       // Extension
    }
    
    /**
     * Pass icon back. Done this way so can report back if problem loading
     * icon.
     * 
     * As of Java 9, the module system allows classes to only load resources 
     * from the same module. The System class belongs to the java.base module, 
     * hence it cannot load a resource from our custom package.
     * 
     * Also found that JDK for Maven must match Sources and Compile.
     * 
     * @param path
     * @return 
     */
    public static ImageIcon createIcon(String path) {
        
        //  TODO doesn't work
        
//        URL url = getClass().getResource(path);
        URL url = Utils.class.getResource(path);
        
        if (url == null) {
            System.err.println("Unable to load image: " + path);
        }
        
        ImageIcon icon = new ImageIcon(url);
        
        return icon;
    }
}

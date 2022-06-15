/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

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
}

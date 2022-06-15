/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author peterream
 */
public class PersonFileFilter extends FileFilter {

    /**
     * This method determines if a file is acceptable or not.
     * @param file - File to be tested
     * @return - true if acceptable, else false
     */
    @Override
    public boolean accept(File file) {
        
        if (file.isDirectory()) {
            return true;                                                            // Accept directories
        }
        
        String name = file.getName();                                               // File name
        String extension = Utils.getFileExtension(name);                            // Get extension
        if (extension == null) {                                                    // Have ext?
            return false;                                                           // No
        }
        
        if (extension.equals("per")) {                                              // .per only
            return true;
        }
        return false;
    }

    /**
     * Description of acceptable files
     * @return 
     */
    @Override
    public String getDescription() {
        
        return "Person database files (*.per)";
    }
    
    
}

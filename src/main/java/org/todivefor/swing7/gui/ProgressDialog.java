/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Window;
import javax.swing.JDialog;

/**
 *
 * @author peterream
 */
public class ProgressDialog extends JDialog {
    
    
    public ProgressDialog(Window parent) {
        
        super(parent, "Downloading messages ..", 
                ModalityType.APPLICATION_MODAL);
        
        setSize(400, 200);
    }
}

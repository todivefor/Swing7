/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.todivefor.swing7;

import java.util.EventListener;

/**
 *
 * @author peterream
 */
public interface FormListener extends EventListener {
    
    /**
     * This method triggers event in Mainframe when (OK) is clicked in
     * formPanel.
     * @param e 
     */
    public void formEventOcurred(FormEvent e);
}

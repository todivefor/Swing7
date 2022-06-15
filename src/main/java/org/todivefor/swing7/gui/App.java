/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.todivefor.swing7.gui;

import javax.swing.SwingUtilities;
import org.todivefor.laf.LAF;

/**
 *
 * @author peterream
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                LAF.initLookAndFeel("Metal", "DefaultMetal");
                new MainFrame();
            }

        });
    }
}

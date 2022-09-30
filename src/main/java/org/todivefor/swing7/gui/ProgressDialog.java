/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author peterream
 */
public class ProgressDialog extends JDialog {

    private JButton cancelButton;
    private JProgressBar progressBar;
    
    /**
     * ProgressDialog constructor
     * @param parent 
     */
    public ProgressDialog(Window parent) {
        
        super(parent, "Downloading messages ..", 
                ModalityType.APPLICATION_MODAL);

        cancelButton = new JButton("Cancel");

        progressBar = new JProgressBar();

//        progressBar.setIndeterminate(true);                                         // If you don't know maximum
        
        setLayout(new FlowLayout());

        // Make progressBar same height as cancelButton with width of 400
        Dimension size = cancelButton.getPreferredSize();
        size.width = (400);
        progressBar.setPreferredSize(size);

        add(progressBar);
        add(cancelButton);

        pack();

        setLocationRelativeTo(parent);
    }

    /**
     * Set maximum value (designates done). If maximum is unknown, can set
     * progressBar.setIndeterminate(true).
     * @param value 
     */
    public void setMaximum (int value) {

        progressBar.setMaximum(value);
    }

    /**
     * Set current value while running.
     * @param value 
     */
    public void setValue (int value) {

        progressBar.setValue(value);
    }

    /**
     * Override Dialog.setVisible(). When dialog ends, setVisible is called with
     * false. If false, pause slightly to see progressBar complete, else 
     * setValue to 0.
     * @param visible 
     */
    @Override
    public void setVisible(boolean visible) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                if (visible == false) {
                    try {
                        Thread.sleep(1000);                                         // Slight pause @ 100%
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    progressBar.setValue(0);                                        // Initially
                }
                
                ProgressDialog.super.setVisible(visible);                           // ProgressDialog
            }

        });
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author peterream
 */
public class PrefsDialog extends JDialog {
    
    private final JButton okButton;
    private final JButton cancelButton;
    private JSpinner portSpinner;
    private final SpinnerNumberModel spinnerModel;
    
    public PrefsDialog(JFrame parent) {
        
        super(parent, "Preferences", false);
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridy = 0;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;                                          // Controls preferred size, 
                                                                                    // rather than fill whole cell
        add(new JLabel("Port: "), gc);
        
        gc.gridx++;                                                                 // 1 to right
        add(portSpinner, gc);
        
        ///////Next row
        
        gc.gridy++;                                                                 // (OK)
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.insets = new Insets(0, 0, 10, 0);                                        // Bottom
        add(okButton, gc);
        
        gc.gridx++;                                                                 // (Cancel)
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.insets = new Insets(0, 0, 10, 10);                                       // Bottom and right
        gc.weightx = 0.2;                                                           // Buttons closer together
        add(cancelButton, gc);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int value = (int)portSpinner.getValue();
                
                System.out.println(value);                                          // Debug
                setVisible(false);
            }
            
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                setVisible(false);
            }
            
        });
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
    
}

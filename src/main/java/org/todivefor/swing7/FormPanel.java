/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author peterream
 */
public class FormPanel extends JPanel {
    
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    
    public FormPanel() {
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);                                             // 10 characters
        occupationField = new JTextField(10);
        
        okBtn = new JButton("OK");
        
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        ///// First row /////
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;
        
        // 0, 0
        gc.anchor = GridBagConstraints.LINE_END;                                    // Align cell right
        gc.insets = new Insets(0, 0, 0, 5);                                         // Add spacing between label and field
        add(nameLabel, gc);
        
        // 1, 0
        gc.gridx = 1;
        gc.gridy = 0;                                                               // Not needed, for clarity
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1;                                                           // Relative height of cells
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);
        
        // 1, 0
        gc.gridx = 1;
        gc.gridy = 0;                                                               // Not needed, for clarity
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);                                         // Reset
        add(nameField,gc);
        
        ///// Second row /////
        // 0, 1
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(occupationLabel, gc);
        
        // 1, 1
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(occupationField, gc);
        
        ///// Third row /////
        // 1, 2
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 2.0;                                                           // Reative height, bigger cell
        gc.anchor = GridBagConstraints.FIRST_LINE_START;                            // Top right of cell
//        gc.anchor = GridBagConstraints.LAST_LINE_START;                             // Bottom right of cell
        gc.insets = new Insets(10, 0, 0, 0);                                        // Space (OK) down a little
        add(okBtn, gc);
    }
    
}

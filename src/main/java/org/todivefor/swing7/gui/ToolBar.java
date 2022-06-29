/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author peterream
 */
public class ToolBar extends JPanel implements ActionListener {

    private JButton saveButton;
    private JButton refreshButton;
    
    private ToolbarListener textListener;

    public ToolBar() {

        setBorder(BorderFactory.createEtchedBorder());
        saveButton = new JButton("Save");
        refreshButton = new JButton("Refresh");
        
        Dimension refreshSize = refreshButton.getPreferredSize();                   // Make buttons
        saveButton.setPreferredSize(refreshSize);                                   // same size 

        saveButton.addActionListener(this);
        refreshButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(refreshButton);

    }

    public void setToolbarListener(ToolbarListener listener) {
        
        this.textListener = listener;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();
        if (clicked == saveButton) {
            if (textListener != null) {
                textListener.saveEventOccured();
            }
        } else {
            if (textListener != null) {
                textListener.refreshEventOccured();
            }
        }
    }
}

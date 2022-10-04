/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author peterream
 */
public class TextPanel extends JPanel {
    
    private JTextArea textArea;
    
    public TextPanel() {
        
        textArea = new JTextArea();
        
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));            // Move down a little in textArea
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    
    public void appendText(String text) {
        
        textArea.append(text);
    }

    public void setText(String text) {
        
        textArea.setText(text);
    }
            
}

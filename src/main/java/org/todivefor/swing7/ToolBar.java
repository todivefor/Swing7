/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author peterream
 */
public class ToolBar extends JPanel implements ActionListener {

    private JButton helloButton;
    private JButton goodbyeButton;
    
    private TextPanel textPanel;

    public ToolBar() {

        helloButton = new JButton("Hello\n");
        goodbyeButton = new JButton("Goodbye\n");

        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(helloButton);
        add(goodbyeButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();
        if (clicked == helloButton) {
            textPanel.appendText("Hello\n");
        } else {
            textPanel.appendText("Goodbye\n");
        }
    }

    /**
     * This provides link to textPanel textArea. Couples the two components
     * which is bad as they should know as little as possible about each other.
     * @param textPanel 
     */
    void setTextPanel(TextPanel textPanel) {
        
        this.textPanel = textPanel;
    }
}

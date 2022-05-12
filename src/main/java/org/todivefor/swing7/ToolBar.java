/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7;

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

    private JButton helloButton;
    private JButton goodbyeButton;
    
    private StringListener textListener;

    public ToolBar() {

        setBorder(BorderFactory.createEtchedBorder());
        helloButton = new JButton("Hello\n");
        goodbyeButton = new JButton("Goodbye\n");

        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(helloButton);
        add(goodbyeButton);

    }

    public void setStringListener(StringListener listener) {
        
        this.textListener = listener;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();
        if (clicked == helloButton) {
            if (textListener != null) {
                textListener.textEmitted("Hello\n");
            }
        } else {
            if (textListener != null) {
                textListener.textEmitted("Goodbye\n");
            }
        }
    }
}

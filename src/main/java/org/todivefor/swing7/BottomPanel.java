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
public class BottomPanel extends JPanel implements ActionListener {

    private final JButton exitButton;
    private ExitListener exitListener;
    
    public BottomPanel() {
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        
/*
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                exitListener.systemExit();
            }
                
        });
*/
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        add(exitButton);
    }

    void setExitListener(ExitListener exitListener) {

        this.exitListener = exitListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        exitListener.systemExit();
    }
    
}

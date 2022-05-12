/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author peterream
 */
public class MainFrame extends JFrame {
    
    private final TextPanel textPanel;
//    private JButton btn;
    private final ToolBar toolBar;
    private final FormPanel formPanel;
    
    public MainFrame() {

        super("Hellow World!");
        
        setLayout(new BorderLayout());
        
        toolBar = new ToolBar();
        
        textPanel = new TextPanel();
        
        /**
         * 
         */
        toolBar.setStringListener(new org.todivefor.swing7.StringListener() {
            @Override
            public void textEmitted(String text) {
                
                textPanel.appendText(text);
            }
            
        });
        
        formPanel = new FormPanel();
        
        /*
        btn = new JButton("Click Me");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                textPanel.appendText("Hello World!\n");
            }
        });
        */
        add(toolBar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
//        add(btn, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

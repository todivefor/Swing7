/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.AppletsAsSwing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author peterream
 */
public class Main {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        
        Game game = new Game();
        
        frame.add(game, BorderLayout.CENTER);
                
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                System.out.println("Window closing");
                game.stop();
                frame.dispose();
                System.gc();
            }
            
        });
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(new Dimension(600, 500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.AppletsAsSwing;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

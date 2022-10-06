/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.AppletsAsSwing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author peterream
 */
public class Game extends JComponent {

    /**
     * Black background.
     */
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        //  Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        //  Ball
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(100, 100, 15, 15));
        
        //  Bat
        g2.setColor(Color.BLUE);
        g2.fill(new RoundRectangle2D.Double(200, 200, 100, 10, 20, 20));
    }
}

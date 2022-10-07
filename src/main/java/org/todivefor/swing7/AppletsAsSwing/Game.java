/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.AppletsAsSwing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author peterream
 */
public class Game extends JComponent {

    private Timer timer;
    
    private ActionListener listener;
    
    private Ellipse2D.Double ball = new Ellipse2D.Double(100, 100, 15, 15);
    private RoundRectangle2D.Double bat;
    
    private double speed = 10.0;
    
    private int xDirectionBall = 1;
    private int yDirectionBall = 1;
    
    private BufferedImage buffer;
    
    /**
     * Game constructor. Setup and start timer.
     */
    public Game() {
    
    listener = new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {

            update();
        }
    };
    
        timer = new Timer(20, listener);
    
        timer.start();
    }
//    
//    private int xDirectionBat = 1;
//    private int yDirectionNat = 1;
    /**
     * Black background.
     */
    @Override
    protected void paintComponent(Graphics g) {
        
        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), 
                    BufferedImage.TYPE_INT_RGB);
        }
        
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        //  Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        //  Ball
        g2.setColor(Color.RED);
        g2.fill(ball );
        
        //  Bat
        g2.setColor(Color.BLUE);
        g2.fill(new RoundRectangle2D.Double(200, 200, 100, 10, 20, 20));
        
        g.drawImage(buffer, 0, 0, null);
    }
    
    public void update() {

        ball.x += xDirectionBall * speed;
        ball.y += yDirectionBall * speed;

        if (ball.x < 0) {															// Hit left?
            xDirectionBall = 1;
            ball.x = 0;
        } else {
            if (ball.x > getWidth() - ball.getBounds().getWidth()) {                // Hit right?
                xDirectionBall = -1;                                                // Change direction
                ball.x = getWidth() - ball.getBounds().getWidth();                  // Restart ball width inside right
            }
        }

        if (ball.y < 0) {                                                           // Hit top?															// Hit top?
            yDirectionBall = 1;
            ball.y = 0;
        } else {
            if (ball.y > getHeight() - ball.getBounds().getHeight()) {              // Hit bottom?
                yDirectionBall = -1;
                ball.y = getHeight() - ball.getBounds().getHeight();
            }
        }
        
        repaint();
    }

    /**
     * May or may not help. Update clears screen, calling paint directly
     * avoids this????
     * @param g 
     */
    @Override
    public void update(Graphics g) {
        
        paint(g);
    }
    
//    void start() {
//        
//        timer.start();
//    }

    public void stop() {
        
        timer.stop();
    }
}

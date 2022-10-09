/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.AppletsAsSwing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author peterream
 */
public class Game extends JComponent implements ActionListener {

    private Timer timer;
    
    private ActionListener listener;
    
    private Ellipse2D.Double ball = new Ellipse2D.Double(100, 100, 15, 15);
    
    private RoundRectangle2D.Double bat = new RoundRectangle2D.Double(
            200, 200, 100, 10, 20, 20);
    private double batSpeed = 10.0;
    
    private double speed = 10.0;
    
    private int xDirectionBall = 1;
    private int yDirectionBall = 1;
    
    private BufferedImage buffer;
    
    private boolean checkIntersection = true;
    
    /**
     * Game constructor. Setup and start timer.
     */
    public Game() {
        
//      *********** Init ****************
//    listener = new ActionListener() {
//        
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            update();
//        }
//    };
    
        timer = new Timer(20, this);

        timer.start();
//      ***************************

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                bat.x = e.getX() - bat.width / 2;
                bat.y = e.getY() - bat.height / 2;
            }
        });
        
        /**
         * Use MouseAdapter because fewer methods
         */
        addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            
            //  Not sure what this is for
            bat.x = e.getX();
            bat.y = e.getY();
            
            //  Pause game (my innovation)
            if (timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
        }
            
        });
        
        /**
         * Return true if you have handled key and don't want it handled 
         * any further.
         * 
         * Not using KeyListener due to focus issue. See notes in lesson 84.
         */
        KeyboardFocusManager.getCurrentKeyboardFocusManager().
                addKeyEventDispatcher(new KeyEventDispatcher() {
                    
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                int key = e.getKeyCode();
                
                //  Only on ket pressed, not on release
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (key) {
                        case KeyEvent.VK_UP:

                            bat.y -= batSpeed;
                            break;

                        case KeyEvent.VK_DOWN:
                            bat.y += batSpeed;
                            break;

                        case KeyEvent.VK_RIGHT:
                            bat.x += batSpeed;
                            break;

                        case KeyEvent.VK_LEFT:
                            bat.x -= batSpeed;
                            break;
                    }
                }
                return false;
            }

        });
        
        /**
         * Change in component size. ComponentAdapter used (fewer methods).
         * Buffer == null tested for in paintComponent().
         */
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                buffer = null;
            }
        });

        //  Hide cursor hotspot used in tutorial (1, 1) doesn't work
        BufferedImage cursorImg = new BufferedImage(1, 1, 
                BufferedImage.TYPE_INT_ARGB);
        Point hotSpot = new Point(0, 0);
        Cursor hiddenCursor = getToolkit().createCustomCursor(cursorImg, 
                hotSpot, "hidden cursor");

        setCursor(hiddenCursor);
    }

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
        g2.fill(bat);
        
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
        
        /*
            checkIntersection does not check for intersection until ball clears
            the bat.
        */
        if (ball.intersects(bat.getBounds2D())) {
            if (checkIntersection) {
//                xDirectionBall = -(xDirectionBall);
                yDirectionBall = -(yDirectionBall);
                checkIntersection = false;
            }
        } else {
            checkIntersection = true;
        }
        
        repaint();                                                                  // Trigers paint to be called
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
        update();
    }
}

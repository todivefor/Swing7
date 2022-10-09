/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.AppletsAsSwing;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author peterream
 */
public class Main {
    
    private CardLayout cards;
    
    private Game game;
    
    private JPanel start;

    public Main() {

        JFrame frame = new JFrame();
        
        cards = new CardLayout();
        frame.setLayout(cards);

        game = new Game();
        
        StartPanel startPanel = new StartPanel();
        start = startPanel.getPanel();
        
        startPanel.setStartPanelListener(new StartPanelListener() {
            
            @Override
            public void fireStartGame() {
                cards.show(frame.getContentPane(), "game");
            }

        });
        
        frame.add(start, "start");
        frame.add(game, "game");

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                game.stop();
                frame.dispose();
                System.gc();
            }
        });

        cards.show(frame.getContentPane(), "start");
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(new Dimension(600, 500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

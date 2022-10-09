/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.AppletsAsSwing;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author peterream
 */
public class StartPanel {

    private JPanel startPanel;
    
    private StartPanelListener listener;
    
    public StartPanel() {

        startPanel = new JPanel();
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0};
        startPanel.setLayout(gridBagLayout);

        JLabel lblSwingAppletDemo = new JLabel("Swing Applet Demo");
        lblSwingAppletDemo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        GridBagConstraints gbc_lblSwingAppletDemo = new GridBagConstraints();
        gbc_lblSwingAppletDemo.anchor = GridBagConstraints.SOUTH;
        gbc_lblSwingAppletDemo.weighty = 0.5;
        gbc_lblSwingAppletDemo.insets = new Insets(0, 0, 5, 0);
        gbc_lblSwingAppletDemo.gridx = 0;
        gbc_lblSwingAppletDemo.gridy = 0;
        startPanel.add(lblSwingAppletDemo, gbc_lblSwingAppletDemo);

        JButton btnNewSimulation = new JButton("New Simulation");
        btnNewSimulation.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                if (listener != null) {
                    listener.fireStartGame();
                }
            }
        });

        GridBagConstraints gbc_btnNewSimulation = new GridBagConstraints();
        gbc_btnNewSimulation.insets = new Insets(20, 0, 0, 0);
        gbc_btnNewSimulation.anchor = GridBagConstraints.NORTH;
        gbc_btnNewSimulation.weighty = 0.5;
        gbc_btnNewSimulation.gridx = 0;
        gbc_btnNewSimulation.gridy = 1;
        startPanel.add(btnNewSimulation, gbc_btnNewSimulation);
    }

    JPanel getPanel() {

        return startPanel;
    }

    void setStartPanelListener(StartPanelListener listener) {
        
        this.listener = listener;
    }
}

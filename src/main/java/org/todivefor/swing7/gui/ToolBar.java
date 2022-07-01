/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author peterream
 */
public class ToolBar extends JPanel implements ActionListener {

    private JButton saveButton;
    private JButton refreshButton;
    
    private ToolbarListener textListener;

    public ToolBar() {

        setBorder(BorderFactory.createEtchedBorder());
        
        saveButton = new JButton("Save");
        saveButton.setIcon(createIcon("/org/todivefor/swing7/images/"               // Package within Other Sources
                + "save16.gif"));                                                   // Icon from /src/main/resources (Other Sources)
//        saveButton.setIcon(new ImageIcon(getClass().getResource("/org/"
//                + "todivefor/swing7/images/save16.gif")));                          // Alternative
        
        refreshButton = new JButton("Refresh");
        refreshButton.setIcon(createIcon("/org/todivefor/swing7/images/"
                + "refresh16.gif"));
        
        Dimension refreshSize = refreshButton.getPreferredSize();                   // Make buttons
        saveButton.setPreferredSize(refreshSize);                                   // same size 

        saveButton.addActionListener(this);
        refreshButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(refreshButton);

    }
    
    /**
     * Pass icon back. Done this way so can report back if problem loading
     * icon.
     * @param path
     * @return 
     */
    private ImageIcon createIcon(String path) {
        
        URL url = getClass().getResource(path);
        
        if (url == null) {
            System.err.println("Unable to load image: " + path);
        }
        
        ImageIcon icon = new ImageIcon(url);
        
        return icon;
    }

    public void setToolbarListener(ToolbarListener listener) {
        
        this.textListener = listener;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton) e.getSource();
        if (clicked == saveButton) {
            if (textListener != null) {
                textListener.saveEventOccured();
            }
        } else {
            if (textListener != null) {
                textListener.refreshEventOccured();
            }
        }
    }
}

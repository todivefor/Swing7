/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import org.todivefor.iconutils.IconUtils;
import org.todivefor.swing7.model.Message;

/**
 *
 * @author peterream
 * 
 * Using a JPanel here is overkill. Using just to show that just about anything
 * can be used with JList and ListCellRenderer. Could just use JLabel.
 */
public class MessageListRenderer implements ListCellRenderer {
    
    private JPanel panel;
    
    private JLabel label;
    
    private Color selectedColor;
    private Color normalColor;
    
    public MessageListRenderer() {
        
        panel = new JPanel();
        label = new JLabel();
        
        /*
            createFont() returns font of size 1 (basically invisible). 
            deriveFont() doesn't work with size only?, need style.
        */
        label.setFont(Utils.createFont("/org/todivefor/swing7/fonts/"
                + "CrimewaveBb.ttf").deriveFont(Font.PLAIN, 20));
        
        selectedColor = new Color(210, 210, 255);
        normalColor = Color.WHITE;
        
        label.setIcon(new IconUtils().getIcon("general", "Information", 24));
        
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        panel.add(label);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, 
            int index, boolean isSelected, boolean cellHasFocus) {
        
        Message message = (Message) value;
        
        label.setText(message.getTitle());
        
        panel.setBackground(cellHasFocus ? selectedColor : normalColor);
        
        return panel;
    }
    
}

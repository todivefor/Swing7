/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import org.todivefor.iconutils.IconUtils;
import org.todivefor.swing7.model.ServerInfo;

/**
 *
 * @author peterream
 */
public class ServerTreeCellRenderer implements TreeCellRenderer {

    private JCheckBox leafRenderer;
    private DefaultTreeCellRenderer nonLeafRenderer;
    
    private Color textForeground;
    private Color textBackground;
    private Color selectionForeground;
    private Color selectionBackground;
    
    public ServerTreeCellRenderer() {
        
        leafRenderer = new JCheckBox();
        nonLeafRenderer = new DefaultTreeCellRenderer();
        
        // Set icons on nodes and leaves
        nonLeafRenderer.setLeafIcon(new IconUtils().getIcon(
                "development", "Server", 16));
        nonLeafRenderer.setOpenIcon(new IconUtils().getIcon(
                "development", "WebComponent", 16));
        nonLeafRenderer.setClosedIcon(new IconUtils().getIcon(
                "development", "WebComponentAdd", 16));
        
        textForeground = UIManager.getColor("Tree.textForeground");
        textBackground = UIManager.getColor("Tree.textBackground");
        selectionForeground = UIManager.getColor("Tree.selectionForeground");
        selectionBackground = UIManager.getColor("Tree.selectionBackground");
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        if (leaf) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            ServerInfo nodeInfo = (ServerInfo) node.getUserObject();
            
            if (selected) {
                leafRenderer.setForeground(selectionForeground);
                leafRenderer.setBackground(selectionBackground);
            } else {
                leafRenderer.setForeground(textForeground);
                leafRenderer.setBackground(textBackground);
            }
            
            leafRenderer.setText(nodeInfo.getName());
            leafRenderer.setSelected(nodeInfo.isChecked());
            
            return leafRenderer;
        } else {
            return nonLeafRenderer.getTreeCellRendererComponent(tree, value, 
                    leaf, expanded, leaf, row, hasFocus);
        }
    }
}
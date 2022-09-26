/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import org.todivefor.swing7.model.ServerInfo;

/**
 *
 * @author peterream
 * 
 * TreCellEditor has a multitude of methods. This is reduced by extending with
 * AbstractCellEditor. Then we override isCellEditable(). When a node is 
 * clickedThe 3 methods are called in sequence by the Tree. If isCellEditable() 
 * returns true, getTreeCellEditorComponent() is called which returns a 
 * component like checkBox, and then fireEditingStopped() is called to stop
 * editing which in turn calls getCellEditorValue() which will return the user
 * Object.
 */
public class ServerTreeCellEditor extends AbstractCellEditor 
        implements TreeCellEditor {
    
    private ServerTreeCellRenderer renderer;
    
    private JCheckBox checkBox;
    
    private ServerInfo info;
    
    public ServerTreeCellEditor() {
        
        renderer = new ServerTreeCellRenderer();
    }
    
    /**
     * Method is called if node is editable. Get checkBox and set a listener
     * for it. When clicked stop editing (fireEditingStopped()) and resume
     * rendering. This triggers getCellEditorValue(). Remove listener, since
     * there is only 1 checkBox, no need to accumulate listeners.
     * @param tree
     * @param value
     * @param isSelected
     * @param expanded
     * @param leaf
     * @param row
     * @return 
     */
    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, 
            boolean isSelected, boolean expanded, boolean leaf, int row) {
        
        Component component = renderer.getTreeCellRendererComponent(tree, value, 
                isSelected, expanded, leaf, row, true);                             // Pass arguments as called and add true for hasFocus
        
        // Leaf test not needed because only leaves are editible
//        if (leaf) {
            
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) value;
            info = (ServerInfo) treeNode.getUserObject();
            
            checkBox = (JCheckBox) component;
            
            ItemListener itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    
                    fireEditingStopped();                                           // Stop using editor and start using renderer
                    checkBox.removeItemListener(this);                              // this = itemListener
                }
            };
            
            checkBox.addItemListener(itemListener);
            
//        }
        return component;
    }

    /**
     * Method is called on fireEditingStopped().
     * Info is a checkBox. Set selected or not and pass it back.
     * @return 
     */
    @Override
    public Object getCellEditorValue() {
        
        info.setChecked(checkBox.isSelected());
        return info;
    }

    /**
     * Get to node clicked and check if leaf.
     * @param event
     * @return 
     */
    @Override
    public boolean isCellEditable(EventObject event) {
        
        // Last component selected
        if (!(event instanceof MouseEvent)) {
            return false;
        }
        
        MouseEvent mouseEvent = (MouseEvent) event;
        
        JTree tree = (JTree) event.getSource();
        
        TreePath path = tree.getPathForLocation(mouseEvent.getX(), 
                mouseEvent.getY());
        
        if (path == null) {
            return false;
        }
        
        Object lastComponent = path.getLastPathComponent();
        
        if (lastComponent == null) {
            return false;
        }
        
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) 
                lastComponent;
        
        return treeNode.isLeaf();
    }
    
}

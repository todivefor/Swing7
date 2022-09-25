/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author peterream
 */
class ServerInfo {

    private final String name;
    private final int id;
    private boolean checked;
    
    public ServerInfo(String name, int id, boolean checked) {
        
        this.name = name;
        this.id = id;
        this.checked = checked;
    }

    public boolean isChecked() {
        
        return checked;
    }

    public void setChecked(boolean checked) {
        
        this.checked = checked;
    }

    public String getName() {
        
        return name;
    }

    public int getId() {
        
        return id;
    }

    @Override
    public String toString() {

//        return id + ": " + name;
        return name;
    }
    
    
}
public class MessagePanel extends JPanel {
    
    private final JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    
    public MessagePanel() {
        
        treeCellRenderer = new ServerTreeCellRenderer();
        
        serverTree = new JTree(createTree() );                                      // Tree of structure DefaultMutableTreeNode
        serverTree.setCellRenderer(treeCellRenderer);
        
        serverTree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);                          // Select one node only
        
        serverTree.addTreeSelectionListener(new TreeSelectionListener() {           // Tree click
            
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        serverTree.getLastSelectedPathComponent();
                
                Object userObject = node.getUserObject();
                
//                if (userObject instanceof ServerInfo) {                             // Leaf?
//                    int id = ((ServerInfo) userObject).getId();                     // Yes
//                    System.out.println("Got user object with ID: " + id);
//                }
                

                System.out.println(userObject);
            }
            
        });
        
        setLayout(new BorderLayout());
        
        // JTree is almost always / always added to a JScrollPane
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }
    
    /**
     * Build theoretical tree of node (Servers), branches (counties), and 
     * leaves (cities).
     * @return 
     */
    private DefaultMutableTreeNode createTree() {
        
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");         // Node
        
        DefaultMutableTreeNode usaBranch = new DefaultMutableTreeNode("USA");       // Branch
        
        DefaultMutableTreeNode nyLeaf = new DefaultMutableTreeNode(
                new ServerInfo("New York", 0, true));                                     // Leaf
        DefaultMutableTreeNode bostonLeaf = new DefaultMutableTreeNode(
                new ServerInfo("Boston", 1, false));
        DefaultMutableTreeNode laLeaf = new DefaultMutableTreeNode(
                new ServerInfo("Los Angeles", 2, true));
        
        
        usaBranch.add(nyLeaf);                                                      // Add leaf to branch
        usaBranch.add(bostonLeaf);
        usaBranch.add(laLeaf);
        
        DefaultMutableTreeNode ukBranch = new DefaultMutableTreeNode("UK");
        
        DefaultMutableTreeNode londonLeaf = new DefaultMutableTreeNode(
                new ServerInfo("London", 3, false));
        DefaultMutableTreeNode edinburghLeaf = new DefaultMutableTreeNode(
                new ServerInfo("Edinburgh", 4, true));
        
        ukBranch.add(londonLeaf);
        ukBranch.add(edinburghLeaf);
        
        top.add(usaBranch);                                                         // Add branch to node
        top.add(ukBranch);
        
        return top;                                                                 // Return structure
    }
}

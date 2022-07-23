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
public class MessagePanel extends JPanel {
    
    private final JTree serverTree;
    
    public MessagePanel() {
        
        serverTree = new JTree(createTree() );                                      // Tree of structureDefaultMutableTreeNode
        
        serverTree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);                          // Select one node only
        
        serverTree.addTreeSelectionListener(new TreeSelectionListener() {           // Tree click
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        serverTree.getLastSelectedPathComponent();
                
                Object nodeObject = node.getUserObject();
                System.out.println(nodeObject);
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
        
        DefaultMutableTreeNode nyLeaf = new DefaultMutableTreeNode("New York");     // Leaf
        DefaultMutableTreeNode bostonLeaf = new DefaultMutableTreeNode(
                "Boston");
        DefaultMutableTreeNode laLeaf = new DefaultMutableTreeNode(
                "Los Angeles");
        
        
        usaBranch.add(nyLeaf);                                                      // Add leaf to branch
        usaBranch.add(bostonLeaf);
        usaBranch.add(laLeaf);
        
        DefaultMutableTreeNode ukBranch = new DefaultMutableTreeNode("UK");
        
        DefaultMutableTreeNode londonLeaf = new DefaultMutableTreeNode(
                "London");
        DefaultMutableTreeNode edinburghLeaf = new DefaultMutableTreeNode(
                "Edinburgh");
        
        ukBranch.add(londonLeaf);
        ukBranch.add(edinburghLeaf);
        
        top.add(usaBranch);                                                         // Add branch to node
        top.add(ukBranch);
        
        return top;                                                                 // Return structure
    }
}

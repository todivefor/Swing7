/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import org.todivefor.swing7.Controller.MessageServer;
import org.todivefor.swing7.model.Message;
import org.todivefor.swing7.model.ServerInfo;

/**
 *
 * @author peterream
 */
public class MessagePanel extends JPanel implements ProgressDialogListener {
    
    private final JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;
    
    private Set<Integer> selectedServers;
    
    private MessageServer messageServer;
    
    private ProgressDialog progressDialog;
    
    private SwingWorker<List<Message>, Integer> worker;
    
    /**
     * Constructor for MessagePanel
     * @param parent 
     */
    public MessagePanel(JFrame parent) {
        
        progressDialog = new ProgressDialog(parent, 
                "Retrieving Downloading ...");
        
        messageServer = new MessageServer();
        
        selectedServers = new TreeSet<Integer>();
        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);
        
        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor(); 
        
        serverTree = new JTree(createTree() );                                      // Tree of structure DefaultMutableTreeNode
        serverTree.setRootVisible(true);
        serverTree.setShowsRootHandles(true);
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        
        serverTree.setEditable(true);
        
        serverTree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);                          // Select one node only
        
        treeCellEditor.addCellEditorListener(new CellEditorListener(){
            @Override
            public void editingStopped(ChangeEvent e) {
                
                ServerInfo info = (ServerInfo) treeCellEditor.
                        getCellEditorValue();
                System.out.println(info + ": " + info.getId() + ": " + 
                        info.isChecked());
                
                int serverId = info.getId();
                
                if (info.isChecked()) {
                    selectedServers.add(serverId);
                } else {
                    selectedServers.remove(serverId);
                }
                
                messageServer.setSelectedServers(selectedServers);
                
                retrieveMessages();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); 
            }
            
        });

/*
        This code is replaced by above. Listen for fireEditingStopped(). Not
        interested in editingCanceled().
        
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
*/
        
        setLayout(new BorderLayout());
        
        // JTree is almost always / always added to a JScrollPane
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }
    
    /**
     * Where the work is done in another thread.
     * doInBckground() - where work is done.
     * process() - is called on each publish().
     * done() - called when work is done.
     */
    private void retrieveMessages() {

//        MessagePanel.this.serverTree.setEditable(false);                            // make tree not editable (checkboxes are not clickable from now on)
        
        progressDialog.setMaximum(messageServer.getMessageCount());

        progressDialog.setVisible(true);
        
        progressDialog.setProgressDialogListener(this);

/*      Below moved to ProgressDialog.setVisible()
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                progressDialog.setVisible(true);
            }

        });
*/
        worker = new SwingWorker<List<Message>, Integer>() {

            /**
             * Code you want to run in a separate thread.
             */
            @Override
            protected List<Message> doInBackground() throws Exception {
              
                List<Message> retrievedMessages = new ArrayList<Message>();
                
                int count = 0;
                for (Message message : messageServer) {

                    if (isCancelled()) {
                        break;                                                      // Stop processing
                    }

                    System.out.println(message.getTitle());
                    retrievedMessages.add(message);
                    count++;
                    publish(count);
                }
                
                return retrievedMessages;
            }

            @Override
            protected void done() {
                
                progressDialog.setVisible(false);
                
                if (isCancelled()) {
                    return;
                }

                try {
                    List<Message> retrivedMessages = get();
                    
//                    System.out.println("Retrived " + retrivedMessages.size() +
//                            " messages.");
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(MessagePanel.class.getName()).log(
                            Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(MessagePanel.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
                
//                MessagePanel.this.serverTree.setEditable(true);                     // make Tree editable again (make checkboxes clickable again)
                
            }

            @Override
            protected void process(List<Integer> counts) {

                int retrieved = counts.get(counts.size() - 1);
                
                progressDialog.setValue(retrieved);
            }

        };
        
        worker.execute();
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
                new ServerInfo("New York", 0, selectedServers.contains(0)));        // Leaf
        DefaultMutableTreeNode bostonLeaf = new DefaultMutableTreeNode(
                new ServerInfo("Boston", 1, selectedServers.contains(1)));
        DefaultMutableTreeNode laLeaf = new DefaultMutableTreeNode(
                new ServerInfo("Los Angeles", 2, selectedServers.contains(2)));
        
        
        usaBranch.add(nyLeaf);                                                      // Add leaf to branch
        usaBranch.add(bostonLeaf);
        usaBranch.add(laLeaf);
        
        DefaultMutableTreeNode ukBranch = new DefaultMutableTreeNode("UK");
        
        DefaultMutableTreeNode londonLeaf = new DefaultMutableTreeNode(
                new ServerInfo("London", 3, selectedServers.contains(3)));
        DefaultMutableTreeNode edinburghLeaf = new DefaultMutableTreeNode(
                new ServerInfo("Edinburgh", 4, selectedServers.contains(4)));
        
        ukBranch.add(londonLeaf);
        ukBranch.add(edinburghLeaf);
        
        top.add(usaBranch);                                                         // Add branch to node
        top.add(ukBranch);
        
        return top;                                                                 // Return structure
    }

    @Override
    public void progressDialogCancelled() {
        
        if (worker != null) {
            worker.cancel(true);
        }
    }
}
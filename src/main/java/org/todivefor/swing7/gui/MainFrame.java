/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import org.todivefor.swing7.Controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import org.todivefor.iconutils.IconUtils;
import org.todivefor.laf.LAF;

/**
 *
 * @author peterream
 */
public class MainFrame extends JFrame {
    
    private final ToolBar toolBar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final Controller controller;
    private TablePanel tablePanel;
    private final PrefsDialog prefsDialog;
    private final JSplitPane splitPane;
    private final JTabbedPane tabbedPane;
    private MessagePanel messagePanel;
    
    private Preferences prefs;
    
    public MainFrame() {

        super("Hello World!");
        
        LAF.initLookAndFeel(this, "Metal", "DefaultMetal");
        
        setLayout(new BorderLayout());
        
        toolBar = new ToolBar();
        
        controller = new Controller();
        
        tablePanel = new TablePanel();
        
        prefsDialog = new PrefsDialog(this);
        
        formPanel = new FormPanel();

        messagePanel = new MessagePanel(this);
        
        tabbedPane = new JTabbedPane();                                             // TabbedPane
        tabbedPane.add("Person Database", tablePanel);                              // 1st
        tabbedPane.add("Messages", messagePanel);                                   // 2nd
              
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, 
                tabbedPane);
        splitPane.setOneTouchExpandable(true);                                      // icon to expand / contract
        
        prefs = Preferences.userRoot().node("db");                                  // Set prfs node to "db"
        
        tablePanel.removeByColumnName(null);                                        // Remove column
        
        tablePanel.setData(controller.getPeople());
        
        tablePanel.setPersonTableListener(new PersonTableListener() {               // "Delete row" from TablePanel

            @Override
            public void removePerson(int row) {

                int id = controller.removePerson(row);                              // person ID from row
                
                int okOrCancel = JOptionPane.showConfirmDialog(MainFrame.this, 
                        "Delete person with ID: " + id + " from SQL database?", 
                        "Delete From DB", 
                        JOptionPane.OK_CANCEL_OPTION);                              // Delete from SQL DB?
                
                if (okOrCancel == JOptionPane.OK_OPTION) {
                    try {
                        controller.removePersonFromSQLDb(id);                       // Yes, remove person with ID from SQL DB
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, 
                                "Error deleting DB row with ID: " + id, 
                                "SQL DB Delete Error", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        prefsDialog.setPrefsListener(new PrefsListener() {                          // PrefsDialog (OK)
            
            @Override
            public void prefsSet(String user, String password, int port) {
                
                prefs.put("user", user);
                prefs.put("password", password);
                prefs.putInt("port", port);
            }
            
        });

         // Get user, password, port or their default from Preferences

        String user = prefs.get("user", "");                                        // from prefs user / ""
        String password = prefs.get("password", "");                                // from prefs password / ""
        int port = prefs.getInt("port", 3306);                                      // from prefs port / 3306
        
        // Set in PrefsDialog fields
        prefsDialog.setDefaults(user, password, port);                              // Set fields in PrefsDialog

        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        
        setJMenuBar(createMenuBar());
        
        /**
         * 
         */
        toolBar.setToolbarListener(new ToolbarListener() {
            @Override
            public void saveEventOccured() {

                connect();

                try {                                                               // Save DB
                    controller.save();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, 
                            "Problem saving database.", 
                            "Database Save Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void refreshEventOccured() {
                
                connect();
                
                try {
                    controller.load();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, 
                            "Problem loading database.", 
                            "Database Load Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
                
                tablePanel.refresh();
            }
        });
        
        formPanel.setFormListener(new FormListener() {                              // FormPanel (OK)
            @Override
            public void formEventOcurred(FormEvent e) {

                controller.addPerson(e);                                            // Add to table   
                tablePanel.refresh();                                               // Table has been updated
            }
        });
        
        /**
         * When MainFram window closes, disconnect DB, dispose of window, and
         * perform garbage collection to avoid interruption that does not
         * occur on this system.
         * 
         * We use new WindowAdapter() because new WindowListener() has too many
         * implemented methods.
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
//                System.out.println("Window closing");                               // Debug
                controller.disconnect();                                            // Close DB
                dispose();                                                          // MainFram close window
                System.gc();                                                        // Garbage collection
            }
            
            
        });
        
        add(toolBar, BorderLayout.PAGE_START);                                      // Make draggable

        add(splitPane, BorderLayout.CENTER);
//        add(tablePanel, BorderLayout.CENTER);
//        add(formPanel, BorderLayout.WEST);
//        add(btn, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));                                    // Minimum so FormPanel won't get too small
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void connect() throws HeadlessException {
        
        try {                                                                       // Connect DB
            controller.connect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Unable to connect database.",
                    "Database Connection Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Setup the menuBar with 2 menus. the File menu has 4 items and a
     * separator. The Window menu has 1 submenu which then contains 1 item.
     * @return 
     */
    private JMenuBar createMenuBar() {
        
        UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);          // Underscore JMenu mnemonics
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem exportDataItem = new JMenuItem("Export Data...");                 // Export data
        exportDataItem.setIcon(new IconUtils().getIcon(
                "general", "Export", 16));
        
        exportDataItem.setMnemonic(KeyEvent.VK_E);
        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if (fileChooser.showSaveDialog(MainFrame.this) == 
                        JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
//                    System.out.println(fileChooser.getSelectedFile());
                    } catch (IOException ex) {
                       JOptionPane.showMessageDialog(MainFrame.this, 
                                "Could not save data to file.", "Save Error", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        });
        JMenuItem importDataItem = new JMenuItem("Import Data...");                 // Import data
        importDataItem.setIcon(new IconUtils().getIcon(
                "general", "Import", 16));
        
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.META_MASK));
        
        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if (fileChooser.showOpenDialog(MainFrame.this) == 
                        JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();                                       // Refresh table
//                    System.out.println(fileChooser.getSelectedFile());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, 
                                "Could not load data from file.", "Load Error", 
                                JOptionPane.ERROR_MESSAGE);
//                        ex.printStackTrace();
                    }
                }
            }
            
        });
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setIcon(new IconUtils().getIcon("media", "Stop", 16));
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.META_MASK));
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
/*              This is just a demo of .showInputDialog
                
                String text = JOptionPane.showInputDialog(
                        MainFrame.this,  "Enter your username.",
                        "Enter User Name", JOptionPane.OK_OPTION | 
                                JOptionPane.QUESTION_MESSAGE);
                System.out.println(text);
*/
                int action = JOptionPane.showConfirmDialog(
                        MainFrame.this,  "Do you really want to exit?",
                        "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    
                    WindowListener[] listeners = getWindowListeners();              // Array of listeners (we only have 1)
                    
                    for (WindowListener listener : listeners) {
                        listener.windowClosing(new WindowEvent(
                                MainFrame.this, 0));
                    
//                    listeners[0].windowClosing(                                     // Alternative
//                            new WindowEvent(MainFrame.this, 0));
                    }
                }
            }
        });
        
        fileMenu.add(importDataItem);
        fileMenu.add(exportDataItem);
        fileMenu.addSeparator();;
        fileMenu.add(exitItem);
        
        JMenu windowMenu = new JMenu("Window");
        
        JMenu showMenu = new JMenu("Show");
        showMenu.setIcon(new IconUtils().getIcon("media", "Play", 16));
        
        JMenuItem prefsItem = new JMenuItem("Preferences...");
        prefsItem.setIcon(new IconUtils().getIcon("general", 
                "Preferences", 16));
        
        prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, 
                ActionEvent.META_MASK));
        
        prefsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                prefsDialog.setVisible(true);
            }
            
        });
        
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);
       
        showMenu.add(showFormItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        
        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
                if (menuItem.isSelected()) {
                    splitPane.setDividerLocation(
                            (int)formPanel.getMinimumSize().getWidth());            // Needed to reappear
                }
                formPanel.setVisible(menuItem.isSelected());
            }
            
        });
        
        return menuBar;
    }
}
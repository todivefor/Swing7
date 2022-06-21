/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import org.todivefor.swing7.Controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 *
 * @author peterream
 */
public class MainFrame extends JFrame {
    
    private final TextPanel textPanel;
//    private JButton btn;
    private final ToolBar toolBar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final Controller controller;
    private TablePanel tablePanel;  
    
    public MainFrame() {

        super("Hello World!");
        
        setLayout(new BorderLayout());
        
        toolBar = new ToolBar();
        
        textPanel = new TextPanel();
        
        controller = new Controller();
        
        tablePanel = new TablePanel();
        
        tablePanel.setData(controller.getPeople());
        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        
        setJMenuBar(createMenuBar());
        
        /**
         * 
         */
        toolBar.setStringListener(new org.todivefor.swing7.gui.StringListener() {
            @Override
            public void textEmitted(String text) {
                
                textPanel.appendText(text);
            }
            
        });
        
        formPanel = new FormPanel();
        
        formPanel.setFormListener(new FormListener() {                              // FormPanel (OK)
            public void formEventOcurred(FormEvent e) {
                
                // Get input data from EventObject
//                String name = e.getName();
//                String occupation = e.getOccupation();
//                int ageCat = e.getAgeCategory();
//                String empCat = e.getEmploymentCategory();
//                
//                // Append data to TextArea in TextPanel
//                textPanel.appendText(name + ": " + occupation + ": " + 
//                        ageCat + ", " + empCat + "\n");
//                System.out.println(e.getGender());                                  // debug

                controller.addPerson(e);                                            // Add to table
                tablePanel.refresh();                                               // Table has been updated
            }
        });
        
        /*
        btn = new JButton("Click Me");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                textPanel.appendText("Hello World!\n");
            }
        });
        */
        add(toolBar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
//        add(btn, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));                                    // Minimum so FormPanel won't get too small
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
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
                    System.exit(0);
                }
            }
        });
        
        fileMenu.add(importDataItem);
        fileMenu.add(exportDataItem);
        fileMenu.addSeparator();;
        fileMenu.add(exitItem);
        
        JMenu windowMenu = new JMenu("Window");
        
        JMenu showMenu = new JMenu("Show");
        
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        
        windowMenu.add(showMenu);
       
        showMenu.add(showFormItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        
        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
                formPanel.setVisible(menuItem.isSelected());
//                formPanel.setVisible(showFormItem.isSelected());
            }
            
        });
        
        return menuBar;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
    
    public MainFrame() {

        super("Hello World!");
        
        setLayout(new BorderLayout());
        
        toolBar = new ToolBar();
        
        textPanel = new TextPanel();
        
        setJMenuBar(createMenuBar());
        
        /**
         * 
         */
        toolBar.setStringListener(new org.todivefor.swing7.StringListener() {
            @Override
            public void textEmitted(String text) {
                
                textPanel.appendText(text);
            }
            
        });
        
        formPanel = new FormPanel();
        
        formPanel.setFormListener(new FormListener() {
            public void formEventOcurred(FormEvent e) {
                
                // Get input data from EventObject
                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCat = e.getAgeCategory();
                String empCat = e.getEmploymentCategory();
                
                // Append data to TextArea in TextPanel
                textPanel.appendText(name + ": " + occupation + ": " + 
                        ageCat + ", " + empCat + "\n");
//                System.out.println(e.getGender());                                  // debug
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
        add(textPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);
//        add(btn, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        exportDataItem.setMnemonic(KeyEvent.VK_E);
        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("EXPORT!");
            }
            
        });
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.META_MASK));
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.exit(0);
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

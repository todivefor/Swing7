/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import org.todivefor.swing7.model.Person;

/**
 *
 * @author peterream
 */
public class TablePanel extends JPanel {
    
    private final JTable table;
    private final PersonTableModel tableModel;
    private JPopupMenu popUp;
    
    public TablePanel() {
        
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        popUp = new JPopupMenu();
        
        JMenuItem removeItem = new JMenuItem("Delete row");
        popUp.add(removeItem);
        /**
         * Using (new MouseAdapter) because MouseListener interface has lots and
         * lots of methods to implement.
         */
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {                                // Mouse press
                
                int row = table.rowAtPoint(e.getPoint());                           // Get row

                table.getSelectionModel().setSelectionInterval(row, row);           // Select row
                
                if (e.getButton() == MouseEvent.BUTTON3) {                          // Right click?
                    popUp.show(table, e.getX(), e.getY());                          // Yes, show "Delete row"
                }
            }
            
        });
        
        /**
         * When "Delete row" JMenuItem is clicked.
         */
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int row = table.getSelectedRow();                                   // Selected row
                System.out.println(row);                                            // Debug
            }
            
        });
      
        setLayout(new BorderLayout());
        
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setData(List<Person> db) {
        
        tableModel.setData(db);
    }

    public void refresh() {
        
        tableModel.fireTableDataChanged();
    }
    
    /**
     * Remove column by column number.
     * @param col 
     */
    public void removeByColumnNumber(int col) {
        
        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn(tcm.getColumn(col));
    }
    
    /**
     * This method looks up column name to remove. If found, calls 
     * removeByColumnNumber with column number. Null or invalid column name
     * causes nothing to be deleted.
     * @param columnName 
     */
    public void removeByColumnName(String columnName) {
        
        if (columnName == null) {
            return;
        }
        
        String[] columnNames = tableModel.getColNames();
        for (int col = 0; col < columnNames.length; col++) {
            if (columnNames[col].equals(columnName)) {
                removeByColumnNumber(col);
            }
        }
    }
}

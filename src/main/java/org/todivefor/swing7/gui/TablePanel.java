/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.todivefor.swing7.model.Person;

/**
 *
 * @author peterream
 */
public class TablePanel extends JPanel {
    
    private final JTable table;
    private final PersonTableModel tableModel;
    
    public TablePanel() {
        
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setData(List<Person> db) {
        
        tableModel.setData(db);
    }

    public void refresh() {
        
        tableModel.fireTableDataChanged();
    }
}

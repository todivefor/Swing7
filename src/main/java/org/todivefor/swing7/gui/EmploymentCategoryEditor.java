/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.todivefor.swing7.model.EmploymentCategory;

/**
 *
 * @author peterream
 */
public class EmploymentCategoryEditor extends AbstractCellEditor 
        implements TableCellEditor {
    
    private JComboBox combo;

    public EmploymentCategoryEditor() {
        
        combo = new JComboBox(EmploymentCategory.values());
    }

    @Override
    public Object getCellEditorValue() {
        
        return combo.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
            boolean isSelected, int row, int column) {

        combo.setSelectedItem(value);
        
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                fireEditingStopped();
//                EmploymentCategoryEditor.this.fireEditingStopped();
            }
            
        });
        
        return combo;
    }

    /**
     * May not be needed.
     * @param e
     * @return 
     */
    @Override
    public boolean isCellEditable(EventObject e) {
        
        return true;
    }
    
}

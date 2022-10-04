/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.todivefor.swing7.model.EmploymentCategory;

/**
 *
 * @author peterream
 */
public class EmploymentCategoryRenderer implements TableCellRenderer {
    
    private JComboBox combo;

    public EmploymentCategoryRenderer() {
        
        combo = new JComboBox(EmploymentCategory.values());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        combo.setSelectedItem(value);
        
        return combo;
    }
    
}

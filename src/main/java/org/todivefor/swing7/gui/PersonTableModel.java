/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.todivefor.swing7.model.Person;

/**
 *
 * @author peterream
 */
public class PersonTableModel extends AbstractTableModel {
    
    List<Person> db;
    
    private final String[] colNames = {"ID", "Name", "Occupation", 
        "Age Category","Employment Category", "US Citizen", "Tax ID", "Gender"};
    
//    public PersonTableModel() {
//        
//    }
    
    public void setData(List<Person> db) {
        
        this.db = db;
    }

    @Override
    public String getColumnName(int column) {
        
        return colNames[column];
    }

    @Override
    public int getRowCount() {
        
        return db.size();                                                           // Row count is size of list
    }

    @Override
    public int getColumnCount() {
        
        return colNames.length;                                                     // 8 columns
    }

    @Override
    public Object getValueAt(int row, int col) {
        
        Person person = db.get(row);
        
        switch (col) {
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCategory();
            case 4:
                return person.getEmpCat();
            case 5:
                return person.isUsCitizen();
            case 6:
                return person.getTaxId();
            case 7:
                return person.getGender();
        }
        return null;                                                                // Shouldn't happen
    }

    /**
     * Return names of columns.
     * @return 
     */
    public String[] getColNames() {
        
        return colNames;
    }
    
    
}

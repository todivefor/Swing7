/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.todivefor.swing7.model.EmploymentCategory;
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

    /**
     * Overridden from AbstractTableModel. Set true for those cells we want to be
     * able to edit.
     * @param row
     * @param col
     * @return 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        
        switch (col) {
            case 1:
                return true;
            case 5:                                                                 // Citizen
                return true;
            default:
                return false;
        }
    }

    /**
     * Overridden from AbstractTableModel. Ignore all columns but column 1. 
     * Set in our db ArrayList<Person>.
     * @param value
     * @param row
     * @param col 
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        
        if (db == null) {
            return;
        }
        
        Person person = db.get(row);
        
        switch (col) {
            case 1:                                                                 // Name
                person.setName((String) value);
                break;
    
            case 5:                                                                 // US citizen
                person.setUsCitizen((Boolean) value);
                break;
            default:
                return;
        }
    }

    /**
     * Overridden from AbstractTableModel. Setting column 5 to Boolean adds
     * the checkbox.
     * @param col
     * @return 
     */
    @Override
    public Class<?> getColumnClass(int col) {

        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return EmploymentCategory.class;
            case 5:
                return Boolean.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            default:
                return null;
        }
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
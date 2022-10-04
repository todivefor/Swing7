/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.model;

/**
 *
 * @author peterream
 */
public enum EmploymentCategory {
    
    employed("employed"),
    selEmployed("self employed"),
    unemployed("un-employed"),
    other("other");
    
    private final String text;
    
    /**
     * Enumeration constructors have to be private.
     * @param text 
     */
    private EmploymentCategory(String text) {
        
        this.text = text;
    }

    @Override
    public String toString() {
        
        return text;
    }
    
}

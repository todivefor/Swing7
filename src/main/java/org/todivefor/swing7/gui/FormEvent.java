/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.util.EventObject;

/**
 *
 * @author peterream
 */
public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int ageCategory;
    private String empCat;
    private String taxId;
    private boolean usCitizen;
    private String gender;
    
    /**
     * Constructor to save data from the JTextFields in FormPanel
     * @param source
     * @param name
     * @param occupation 
     * @param ageCat 
     * @param empCat 
     * @param taxId 
     * @param usCitizen 
     * @param gender 
     */
    public FormEvent(Object source, String name, String occupation, int ageCat,
            String empCat, String taxId, Boolean usCitizen, String gender) {
        
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCat;
        this.empCat = empCat;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
        this.gender = gender;
    }
    
    public String getName() {
        
        return name;
    }

    public void setName(String name) {
        
        this.name = name;
    }

    public String getOccupation() {
        
        return occupation;
    }

    public void setOccupation(String occupation) {
        
        this.occupation = occupation;
    }
    
    public int getAgeCategory () {
        
        return ageCategory;
    }

    public String getEmploymentCategory() {
        
        return empCat;
    }

    public String getTaxId() {
        
        return taxId;
    }

    public boolean isUsCitizen() {
        
        return usCitizen;
    }

    public String getGender() {
        
        return gender;
    }
    
}

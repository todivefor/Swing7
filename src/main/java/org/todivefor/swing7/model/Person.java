/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.model;

import java.io.Serializable;

/**
 *
 * @author peterream
 */
public class Person implements Serializable {
    
    private static int count = 1;                                                   // Person counter
    private static final long serialVersionUID = 1L;                                // Video 29 noted (explanation)
    
    private int id;                                                                 // Unique ID in DB
    private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private EmploymentCategory empCat;
    private String taxId;
    private boolean usCitizen;
    private Gender gender;

    public Person (String name, String occupation, AgeCategory ageCategory, 
            EmploymentCategory empCat, String taxId, boolean usCitizen, 
            Gender gender) {
        
        this.name =name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.empCat = empCat;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
        this.gender = gender;
        
        this.id = count;                                                            // New Person
        count++;                                                                    // Increment on each new Person
    }
    
    /**
     * Overload constructor by adding id. Call original constructor and id
     * instead of generating on other.
     * @param id
     * @param name
     * @param occupation
     * @param ageCategory
     * @param empCat
     * @param taxId
     * @param usCitizen
     * @param gender 
     */
    public Person (int id, String name, String occupation, 
            AgeCategory ageCategory, EmploymentCategory empCat, String taxId, 
            boolean usCitizen, Gender gender) {
        
        this(name, occupation, ageCategory, empCat, taxId, usCitizen, gender);
        this.id = id;                                                                 // id from DB
    }
    
    public int getId() {
        
        return id;
    }

    public void setId(int id) {
        
        this.id = id;
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

    public AgeCategory getAgeCategory() {
        
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        
        this.ageCategory = ageCategory;
    }

    public EmploymentCategory getEmpCat() {
        
        return empCat;
    }

    public void setEmpCat(EmploymentCategory empCat) {
        
        this.empCat = empCat;
    }

    public String getTaxId() {
        
        return taxId;
    }

    public void setTaxId(String taxId) {
        
        this.taxId = taxId;
    }

    public boolean isUsCitizen() {
        
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        
        this.usCitizen = usCitizen;
    }

    public Gender getGender() {
        
        return gender;
    }

    public void setGender(Gender gender) {
        
        this.gender = gender;
    }
    
    /**
     * Partial toString for testing.
     * @return 
     */
    public String toString() {
        
        return "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Occupation: " + occupation + "\n";
    }
}

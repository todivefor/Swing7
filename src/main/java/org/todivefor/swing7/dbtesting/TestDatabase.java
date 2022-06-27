/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.todivefor.swing7.dbtesting;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.todivefor.swing7.model.AgeCategory;
import org.todivefor.swing7.model.Database;
import org.todivefor.swing7.model.EmploymentCategory;
import org.todivefor.swing7.model.Gender;
import org.todivefor.swing7.model.Person;

/**
 *
 * @author peterream
 */
public class TestDatabase {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        
        System.out.println("Running database test");
        
        Database db = new Database();
        
        db.addPerson(new Person("Peter Ream", "Pro Golfer", AgeCategory.senior, 
                EmploymentCategory.selEmployed, "nnn-nn-nnnn", true, 
                Gender.male));
        
        db.addPerson(new Person("Sue", "Artist", AgeCategory.adult, 
                EmploymentCategory.employed, null, false, 
                Gender.female));
        
        try {
            db.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            db.save();
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.disconnect();
    }
    
}

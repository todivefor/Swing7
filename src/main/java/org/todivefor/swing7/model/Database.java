/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peterream
 */
public class Database {
    
    private ArrayList<Person> people;
    
    public Database() {
        
        people = new ArrayList<Person>();
    }
    
    public void addPerson(Person person) {
        
        people.add(person);
    }
    
    public List<Person> getPeople() {
        
        return people;
    }
}

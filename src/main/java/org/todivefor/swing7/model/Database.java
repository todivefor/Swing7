/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author peterream
 */
public class Database {
    
    private List<Person> people;
    
    public Database() {
        
        people = new LinkedList<Person>();
    }
    
    public void addPerson(Person person) {
        
        people.add(person);
    }
    
    public List<Person> getPeople() {
        
        return Collections.unmodifiableList(people);                                // Prevents other classes from modifying
    }
    
    /**
     * Serialization
     * Save data to file. Convert people from ArrayList to Array to keep 
     * <person> information (not sure why). Can writeObject because Arrays are
     * just Objects,
     * @param file
     * @throws IOException 
     */
    public void saveToFile(File file) throws IOException {
        
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        Person[] persons = people.toArray(new Person[people.size()]);               // Persons array
        oos.writeObject(persons);                                                   // persons array is just an object
        
        oos.close();
    }
    
    /**
     * Deserialization
     * Load data from a file. We want to reuse the people ArrayList because
     * of it's association with our PersonTableModel.
     * @param file
     * @throws IOException 
     */
    public void loadFromFile(File file) throws IOException {
        
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            Person[] persons = (Person[])ois.readObject();
            
            people.clear();
            
            people.addAll(Arrays.asList(persons));
            
        } catch (ClassNotFoundException ex) {
            //TODO Auto-generated catch block
            ex.printStackTrace();
        }
        
        ois.close();
    }

    public void removePerson(int row) {
        
        people.remove(row);                                                         // Remove from people
    }
}

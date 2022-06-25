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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Connection con;

    public Database() {

        people = new LinkedList<Person>();
    }

    /**
     * Add person to people array.
     * @param person 
     */
    public void addPerson(Person person) {

        people.add(person);
    }

    /**
     * Return people array.
     * @return 
     */
    public List<Person> getPeople() {

        return Collections.unmodifiableList(people);                                // Prevents other classes from modifying
    }

    /**
     * Serialization Save data to file. Convert people from ArrayList to Array
     * to keep
     * <person> information (not sure why). Can writeObject because Arrays are
     * just Objects,
     *
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
     * Deserialization Load data from a file. We want to reuse the people
     * ArrayList because of it's association with our PersonTableModel.
     *
     * @param file
     * @throws IOException
     */
    public void loadFromFile(File file) throws IOException {

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            Person[] persons = (Person[]) ois.readObject();

            people.clear();

            people.addAll(Arrays.asList(persons));

        } catch (ClassNotFoundException ex) {
            //TODO Auto-generated catch block
            ex.printStackTrace();
        }

        ois.close();
    }

    /**
     * Remove a person from people array by row number.
     * @param row 
     */
    public void removePerson(int row) {

        people.remove(row);                                                         // Remove from people
    }

    /**
     * Connect to swingtest.sqlite (sqlite). Could not get mysql to work. Will
     * require some redearch.
     * @throws Exception 
     */
    public void connect() throws Exception {

        if (con != null) {                                                          // Already connected?
            return;                                                                 // Yes
        }

        try {
            Class.forName("org.sqlite.JDBC");                                       // Causes the class "org.sqlite.JDBC" to be initialized.
//            Class.forName("com.mysql.cj.jdbc.Driver");                              // mysql
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver not found");
        }

//        String URL = "jdbc:mysql://localhost:3306/swingtest";                       // mysql
//        con = DriverManager.getConnection(URL, "peterream",
//                "wb3izu");                                                          // mysql
        con = DriverManager.getConnection("jdbc:sqlite:/Users/peterream"
                + "/DELETE/Swing/swingtest.sqlite");
        System.out.println("Connected: " + con);

    }

    /**
     * Disconnect from sql if connected.
     */
    public void disconnect() {

        if (con != null) {                                                          // Connection established?
            try {
                con.close();                                                        // No, close
            } catch (SQLException ex) {
                System.out.println("Can't close connection");                       // Error
            }
        }
    }

    /**
     * Saves people array to DB.
     * @throws SQLException 
     */
    public void save() throws SQLException {

        String checkSQL = "select count(*) as count from people where id=?";
        PreparedStatement checkStmt = con.prepareStatement(checkSQL);
        
        for (Person person: people) {
            int id = person.getId();
            checkStmt.setInt(1, id);
            ResultSet checkQuery = checkStmt.executeQuery();
            checkQuery.next();
            
            int count = checkQuery.getInt(1);
 
            System.out.println("Count for person with ID " + id + " is " + 
                    count);                                                         // Debug
        }
        
        checkStmt.close();
    }
}

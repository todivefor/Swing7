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
import java.sql.Statement;
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
     * Get count of people on DB with id. If id not on DB (countID = 0), do an
     * insert into DB, otherwise do an update.
     * @throws SQLException 
     */
    public void save() throws SQLException {

        String checkSQL = "select count(*) as count from people where id=?";        // count of each ID
                                                                                    // "as count" column heading (not needed)
        PreparedStatement checkStmt = con.prepareStatement(checkSQL);
        
        String insertSQL = "insert into people (id, name, age, "
                + "employment_status, tax_id, us_citizen, gender, occupation) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSQL);
        
        String updateSQL = "update people SET name = ?, age = ?, "
                + "employment_status = ?, tax_id = ?, us_citizen = ?, "
                + "gender = ?, occupation = ? WHERE id = ?";
//                + "values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement updateStmt = con.prepareStatement(updateSQL);
        
        for (Person person: people) {
            
            // Person info
            int id = person.getId();
            String name = person.getName();
            String occupation = person.getOccupation();
            AgeCategory age = person.getAgeCategory();
            EmploymentCategory empCat = person.getEmpCat();
            Boolean isUS = person.isUsCitizen();
            String taxID = person.getTaxId();
            Gender gender = person.getGender();
            
            checkStmt.setInt(1, id);                                                // Set id at 1st "?" in select
            ResultSet checkQuery = checkStmt.executeQuery();                        // Query
            // checkQuery points just before 1st row
            checkQuery.next();                                                      // Make 1st row current
            
            int countID = checkQuery.getInt(1);                                     // Get count from result
 
            int col = 1;                                                            // Column start
            if (countID == 0) {                                                     // person(id) on DB?
                System.out.println("Inserting person with ID: " + id);              // No, insert
                
                // Add person info to insert prepared statement
                insertStmt.setInt(col++, id);
                insertStmt.setString(col++, name);
                insertStmt.setString(col++, age.name());                            // Returns name of enum constant
                insertStmt.setString(col++, empCat.name());
                insertStmt.setString(col++, taxID);
                insertStmt.setBoolean(col++, isUS);
                insertStmt.setString(col++, gender.name());
                insertStmt.setString(col++, occupation);
                
                insertStmt.executeUpdate();
                
            } else {
                System.out.println("Updating person with ID: " + id);               // Yes, update
                
                // Add person info to update prepared statement
                updateStmt.setString(col++, name);
                updateStmt.setString(col++, age.name());                            // Returns name of enum constant
                updateStmt.setString(col++, empCat.name());
                updateStmt.setString(col++, taxID);
                updateStmt.setBoolean(col++, isUS);
                updateStmt.setString(col++, gender.name());
                updateStmt.setString(col++, occupation);
                updateStmt.setInt(col++, id);
                
                updateStmt.executeUpdate();
            }
        }
        
        insertStmt.close();
        updateStmt.close();
        checkStmt.close();
    }
    
    public void load() throws SQLException {
        
        people.clear();
        
        String sqlSelect = "SELECT * FROM people ORDER BY name";
        
//      Use this select just to remember what we are getting
//        String sqlSelect = "SELECT id, name, age, employment_status, tax_id, "
//                + "us_citizen, gender, occupation FROM people ORDER BY name";
        
        Statement selectStatement = con.createStatement();
        
        ResultSet results = selectStatement.executeQuery(sqlSelect);
        
        while (results.next()) {
            
            int id = results.getInt("id");
            String name = results.getString("name");
            String occupation = results.getString("occupation");
            String age = results.getString("age");
            String emp = results.getString("employment_status");
            String tax = results.getString("tax_id");
            boolean isUS = results.getBoolean("us_citizen");
            String gender = results.getString("gender");
            
            Person person = new Person(id, name, occupation, 
                    AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), 
                    tax, isUS, Gender.valueOf(gender));
            
            people.add(person);
            
//            people.add(new Person(id, name, occupation, 
//                    AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), 
//                    tax, isUS, Gender.valueOf(gender)));
            
//            System.out.println(person);                                             // debug
        }
        
        results.close();
        selectStatement.close();
        
    }
}

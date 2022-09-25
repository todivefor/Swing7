/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package org.todivefor.swing7.model;

import java.io.File;
import java.util.List;
import static org.testng.Assert.*;

/**
 *
 * @author peterream
 */
public class DatabaseNGTest {
    
    public DatabaseNGTest() {
    }

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of addPerson method, of class Database.
     */
    @org.testng.annotations.Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Person person = null;
        Database instance = new Database();
        instance.addPerson(person);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPerson method, of class Database.
     */
    @org.testng.annotations.Test
    public void testGetPerson() {
        System.out.println("getPerson");
        int row = 0;
        Database instance = new Database();
        Person expResult = null;
        Person result = instance.getPerson(row);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPeople method, of class Database.
     */
    @org.testng.annotations.Test
    public void testGetPeople() {
        System.out.println("getPeople");
        Database instance = new Database();
        List expResult = null;
        List result = instance.getPeople();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveToFile method, of class Database.
     */
    @org.testng.annotations.Test
    public void testSaveToFile() throws Exception {
        System.out.println("saveToFile");
        File file = null;
        Database instance = new Database();
        instance.saveToFile(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFromFile method, of class Database.
     */
    @org.testng.annotations.Test
    public void testLoadFromFile() throws Exception {
        System.out.println("loadFromFile");
        File file = null;
        Database instance = new Database();
        instance.loadFromFile(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePerson method, of class Database.
     */
    @org.testng.annotations.Test
    public void testRemovePerson() {
        System.out.println("removePerson");
        int row = 0;
        Database instance = new Database();
        instance.removePerson(row);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class Database.
     */
    @org.testng.annotations.Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        Database instance = new Database();
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disconnect method, of class Database.
     */
    @org.testng.annotations.Test
    public void testDisconnect() {
        System.out.println("disconnect");
        Database instance = new Database();
        instance.disconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class Database.
     */
    @org.testng.annotations.Test
    public void testSave() throws Exception {
        System.out.println("save");
        Database instance = new Database();
        instance.save();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class Database.
     */
    @org.testng.annotations.Test
    public void testLoad() throws Exception {
        System.out.println("load");
        Database instance = new Database();
        instance.load();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePersonWithId method, of class Database.
     */
    @org.testng.annotations.Test
    public void testDeletePersonWithId() throws Exception {
        System.out.println("deletePersonWithId");
        int id = 0;
        Database instance = new Database();
        instance.deletePersonWithId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAllDbRows method, of class Database.
     */
    @org.testng.annotations.Test
    public void testDeleteAllDbRows() throws Exception {
        System.out.println("deleteAllDbRows");
        Database instance = new Database();
        instance.deleteAllDbRows();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

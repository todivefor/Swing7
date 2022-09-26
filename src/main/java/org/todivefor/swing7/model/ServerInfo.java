/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.model;

/**
 *
 * @author peterream
 */
public class ServerInfo {

    private final String name;
    private final int id;
    private boolean checked;
    
    public ServerInfo(String name, int id, boolean checked) {
        
        this.name = name;
        this.id = id;
        this.checked = checked;
    }

    public boolean isChecked() {
        
        return checked;
    }

    public void setChecked(boolean checked) {
        
        this.checked = checked;
    }

    public String getName() {
        
        return name;
    }

    public int getId() {
        
        return id;
    }

    @Override
    public String toString() {

//        return id + ": " + name;
        return name;
    }
}

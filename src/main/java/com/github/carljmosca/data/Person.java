/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.data;

/**
 *
 * @author moscac
 */
public class Person {
    
    private String title;
    private String firstName;
    private String lastName;
    private String middleName;

    public Person() {
        
    }
    
    public Person(String title, String firstName, String middleName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    
    public String getInitials() {
        StringBuilder result = new StringBuilder();
        result.append(firstName != null && !firstName.isEmpty() ? firstName.substring(0, 1).toUpperCase() : "");
        result.append(middleName != null && !middleName.isEmpty() ? middleName.substring(0, 1).toUpperCase() : "");
        result.append(lastName != null && !lastName.isEmpty() ? lastName.substring(0, 1).toUpperCase() : "");
        return result.toString();
    }
          
}

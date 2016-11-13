/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.processor;

import com.github.carljmosca.data.Person;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author moscac
 */

public class PersonProcessor {
    
    private final List<Person> list;
    
    public PersonProcessor() {
        list = new ArrayList<>();
        init();
    }
    
    private void init() {
        list.add(new Person("Mr.", "John", "", "Smith"));
        list.add(new Person("Mrs.", "Tom", "", "Johnson"));
    }
    
    public void addPerson(Person person) {
        list.add(person);
    }
    
    public List<Person> findAll() {
        return list;
    }
    
}

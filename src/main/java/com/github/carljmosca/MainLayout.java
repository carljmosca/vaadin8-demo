/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca;

import com.github.carljmosca.data.Person;
import com.github.carljmosca.processor.PersonProcessor;
import com.vaadin.data.BeanBinder;
import com.vaadin.data.HasValue;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import java.util.Arrays;

/**
 *
 * @author moscac
 */
public class MainLayout extends CssLayout {

    //private final VerticalLayout mainLayout;
    private final HorizontalLayout personLayout;
    //private final HorizontalLayout buttonLayout;
    private final PersonProcessor personProcessor;
    private BeanBinder<Person> binder;
    private ComboBox<String> title;
    private TextField firstName;
    private TextField middleName;
    private TextField lastName;
    private Person person;
    private final String[] titles = {"Mr.", "Mrs.", "Ms.", "Dr."};

    public MainLayout() {
        personProcessor = new PersonProcessor();
        //mainLayout = new VerticalLayout();
        personLayout = new HorizontalLayout();
        //buttonLayout = new HorizontalLayout();
        person = new Person();
        init();
    }

    private void init() {
        setSizeFull();
        addStyleName("menylayout");
        Responsive.makeResponsive(this);
        ComboBox<Person> cmbPerson = new ComboBox<>();
        cmbPerson.setItems(personProcessor.findAll());
        cmbPerson.setItemCaptionGenerator(c -> c.getTitle() + " " + c.getFirstName()
                + " " + c.getLastName());
        cmbPerson.addValueChangeListener((HasValue.ValueChangeEvent<Person> event) -> {
            binder.setBean(cmbPerson.getValue());
        });
        title = new ComboBox("Title", Arrays.asList(titles));
        firstName = new TextField("First");
        middleName = new TextField("Middle");
        lastName = new TextField("Last");
        Button btnNew = new Button("New");
        btnNew.addClickListener((Button.ClickEvent event) -> {
            person = new Person();
            binder.setBean(person);
        });
        Button btnSave = new Button("Save");
        btnSave.addClickListener((Button.ClickEvent event) -> {
            personProcessor.addPerson(person);
            cmbPerson.setItems(personProcessor.findAll());
        });
        personLayout.setWidth(null);
        personLayout.setSpacing(true);
        personLayout.addStyleName("menu");
        personLayout.addComponent(title);
        personLayout.addComponent(firstName);
        personLayout.addComponent(middleName);
        personLayout.addComponent(lastName);
        personLayout.addComponent(btnNew);
        addComponent(btnSave);
        addComponent(cmbPerson);
        addComponent(personLayout);
        //addComponent(buttonLayout);
        doBinding();
    }

    private void doBinding() {
        binder = new BeanBinder<>(Person.class);
        binder.forField(title)
                .bind(Person::getTitle, Person::setTitle);
        binder.forField(firstName)
                .bind(Person::getFirstName, Person::setFirstName);
        binder.forField(middleName)
                .bind(Person::getMiddleName, Person::setMiddleName);
        binder.forField(lastName)
                .bind(Person::getLastName, Person::setLastName);
        binder.setBean(person);
    }

}

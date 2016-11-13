package com.github.carljmosca;

import com.vaadin.annotations.Theme;
import com.vaadin.data.HasValue;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.github.carljmosca.data.Person;
import com.github.carljmosca.processor.PersonProcessor;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.BeanBinder;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MainUI extends UI {

    private final Panel rootLayout;
    private final VerticalLayout mainLayout;
    private final HorizontalLayout personLayout;
    private final HorizontalLayout buttonLayout;
    private final PersonProcessor personProcessor;
    private BeanBinder<Person> binder;
    private ComboBox<String> title;
    private TextField firstName;
    private TextField middleName;
    private TextField lastName;
    private Person person;
    private final String[] titles = { "Mr.", "Mrs.", "Ms.", "Dr." };

    public MainUI() {
        personProcessor = new PersonProcessor();
        rootLayout = new Panel();
        mainLayout = new VerticalLayout();
        personLayout = new HorizontalLayout();
        buttonLayout = new HorizontalLayout();
        person = new Person();
    }

    @Override
    protected void init(VaadinRequest request) {

        rootLayout.setContent(mainLayout);
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
        personLayout.addComponent(title);
        personLayout.addComponent(firstName);
        personLayout.addComponent(middleName);
        personLayout.addComponent(lastName);
        buttonLayout.addComponent(btnNew);
        buttonLayout.addComponent(btnSave);
        mainLayout.addComponent(cmbPerson);
        mainLayout.addComponent(personLayout);
        mainLayout.addComponent(buttonLayout);
        setContent(rootLayout);
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

    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
    }
}

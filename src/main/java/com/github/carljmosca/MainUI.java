package com.github.carljmosca;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
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

    private MainLayout mainLayout;

    public MainUI() {

    }

    @Override
    protected void init(VaadinRequest request) {
        mainLayout = new MainLayout();
        setContent(mainLayout);

    }

    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
    }
}

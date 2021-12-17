package com.finalprojultimate.controller;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

    /**
     * Initialize log4j and I18n when the application is being started
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // initialize log4j here
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);

        // I18n ininialization

        // obtain file name with locales descriptions
        String localesFileName = context.getInitParameter("locales");

        // obtain reale path on server
        String localesFileRealPath = context.getRealPath(localesFileName);

        // locad descriptions
        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // save descriptions to servlet context
        context.setAttribute("locales", locales);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // do nothing
    }

}

package app;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // This runs once when the web app starts
        ServletContext context = sce.getServletContext();

        // Example: Setting a global application start time
        context.setAttribute("appStartTime", LocalDateTime.now());

        // Example: Setting a global support email used across all pages
        context.setAttribute("supportEmail", "support@wellnesshub.MatthewOka.ac.ke");

        System.out.println("Wellness Hub Application Initialized at: " + LocalDateTime.now());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // This runs when the server is stopping
        System.out.println("Wellness Hub Application is shutting down. Cleaning up resources...");
    }
}
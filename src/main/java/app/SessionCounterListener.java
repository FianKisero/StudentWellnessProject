package app;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener {

    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        updateContext(se);
        System.out.println("Session Created. Active Users: " + activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (activeSessions > 0) {
            activeSessions--;
        }
        updateContext(se);
        System.out.println("Session Destroyed. Active Users: " + activeSessions);
    }

    private void updateContext(HttpSessionEvent se) {
        // Store the count in ServletContext so all servlets can access it
        ServletContext context = se.getSession().getServletContext();
        context.setAttribute("activeUsers", activeSessions);
    }
}
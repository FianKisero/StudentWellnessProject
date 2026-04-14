package app;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

public class MyRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // Record the start time (in nanoseconds) when a request enters the server
        long startTime = System.nanoTime();
        sre.getServletRequest().setAttribute("startTime", startTime);

        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        System.out.println(">>> Request Started: " + req.getMethod() + " " + req.getRequestURI());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // Retrieve the start time and calculate the processing duration
        Long startTime = (Long) sre.getServletRequest().getAttribute("startTime");
        if (startTime != null) {
            long duration = (System.nanoTime() - startTime) / 1_000_000; // Convert to milliseconds
            System.out.println("<<< Request Finished. Processing Time: " + duration + "ms");
        }
    }
}
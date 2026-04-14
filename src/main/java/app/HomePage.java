package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class HomePage extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();

        // 1. Cast for session access (Personalization)
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : "Student";

        // 2. Session Listener Data: Active User Count
        Integer activeUsers = (Integer) getServletContext().getAttribute("activeUsers");
        if (activeUsers == null) activeUsers = 1;

        // 3. Application Listener Data: Global Support Info
        String supportEmail = (String) getServletContext().getAttribute("supportEmail");
        if (supportEmail == null) supportEmail = "support@wellnesshub.usiu.ac.ke";

        // 4. Request Listener Data: Performance Tracking
        Long startTime = (Long) req.getAttribute("startTime");
        long processingTime = 0;
        if (startTime != null) {
            processingTime = (System.nanoTime() - startTime) / 1_000_000;
        }

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='en'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        writer.println("    <title>Wellness Hub</title>");
        writer.println("    <style>");
        writer.println("        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; color: #2c3e50; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }");
        writer.println("        .container { background-color: #ffffff; max-width: 600px; width: 90%; padding: 40px; border-radius: 12px; box-shadow: 0 8px 16px rgba(0,0,0,0.1); text-align: center; }");
        writer.println("        h1 { color: #2e8b57; margin-bottom: 10px; }");
        writer.println("        .welcome-msg { font-size: 1.2em; color: #34495e; font-weight: 600; margin-bottom: 20px; }");
        writer.println("        p { color: #555555; font-size: 1.1em; margin-bottom: 30px; }");
        writer.println("        .hero-image { width: 100%; height: auto; border-radius: 8px; margin-bottom: 25px; object-fit: cover; max-height: 250px; }");
        writer.println("        .nav-links { display: flex; flex-direction: column; gap: 15px; }");
        writer.println("        .btn { display: inline-block; padding: 12px 24px; background-color: #3cb371; color: white; text-decoration: none; font-size: 1.1em; font-weight: bold; border-radius: 6px; transition: all 0.3s ease; }");
        writer.println("        .btn:hover { background-color: #2e8b57; transform: translateY(-2px); }");
        writer.println("        .footer-info { margin-top: 35px; border-top: 1px solid #eee; padding-top: 20px; }");
        writer.println("        .status-badge { display: inline-block; padding: 6px 15px; background: #e8f5e9; color: #2e7d32; border-radius: 20px; font-size: 0.85em; font-weight: bold; margin-bottom: 10px; }");
        writer.println("        .dot { height: 8px; width: 8px; background-color: #4caf50; border-radius: 50%; display: inline-block; margin-right: 5px; }");
        writer.println("        .support-text { font-size: 0.85em; color: #95a5a6; margin-bottom: 8px; }");
        writer.println("        .perf-text { font-size: 0.75em; color: #bdc3c7; font-style: italic; }");
        writer.println("    </style>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("    <div class='container'>");
        writer.println("        <img class='hero-image' src='https://images.unsplash.com/photo-1518241353330-0f7941c2d9b5?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80' alt='Calm nature scene'>");

        writer.println("        <div class='welcome-msg'>Welcome back, " + username + "!</div>");
        writer.println("        <h1>Student Wellness & Productivity Hub</h1>");
        writer.println("        <p>Helping you stay mentally strong and productive.</p>");

        writer.println("        <div class='nav-links'>");
        writer.println("            <a href='mood' class='btn'>Track Your Mood</a>");
        writer.println("            <a href='tips' class='btn'>View Tips</a>");
        writer.println("            <a href='about' class='btn'>About Us</a>");
        writer.println("        </div>");

        // UI section for all Listeners
        writer.println("        <div class='footer-info'>");

        // From Session Listener
        writer.println("            <div class='status-badge'>");
        writer.println("                <span class='dot'></span> " + activeUsers + " students online now");
        writer.println("            </div>");

        // From Application Listener
        writer.println("            <div class='support-text'>Support: " + supportEmail + "</div>");

        // From Request Listener
        writer.println("            <div class='perf-text'>Page processed in " + processingTime + "ms</div>");

        writer.println("        </div>");

        writer.println("    </div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
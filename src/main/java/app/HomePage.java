package app;

import app.service.AnyValidationService;
import app.service.ComprehensiveValidationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.inject.Inject;

import java.io.IOException;
import app.framework.PageContent;
import jakarta.servlet.RequestDispatcher;

public class HomePage extends HttpServlet {

    // Field injection - demonstrates field injection point
    @Inject
    private ComprehensiveValidationService comprehensiveService;
    
    // @Any injection - demonstrates @Any annotation
    @Inject
    private AnyValidationService anyValidationService;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        StringBuilder content = new StringBuilder();

        // Use injected services to demonstrate CDI
        if (comprehensiveService != null) {
            comprehensiveService.testDataSource();
        }
        if (anyValidationService != null) {
            anyValidationService.validateWithAll("Home page check");
        }

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

        content.append("<div class='container'>");
        content.append("<img class='hero-image' src='https://images.unsplash.com/photo-1518241353330-0f7941c2d9b5?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80' alt='Calm nature scene' style='max-width: 600px; display: block; margin: 0 auto 25px auto; border-radius: 8px;'>");

        content.append("<div class='welcome-msg'>Welcome back, ").append(username).append("!</div>");
        content.append("<h1>Student Wellness & Productivity Hub</h1>");
        content.append("<p>Helping you stay mentally strong and productive.</p>");

        content.append("<div class='nav-links'>");
        content.append("<a href='./register_mood' class='btn'>Log Your Mood</a><br/>");
        content.append("<a href='./register_student' class='btn'>Register Student</a>");
        content.append("</div>");

        // UI section for all Listeners
        content.append("<div class='footer-info' style='margin-top: 35px; border-top: 1px solid #eee; padding-top: 20px;'>");

        // From Session Listener
        content.append("<div class='status-badge'>");
        content.append("<span class='dot'></span> ").append(activeUsers).append(" students online now");
        content.append("</div><br/>");

        // From Application Listener
        content.append("<div class='support-text'>Support: ").append(supportEmail).append("</div>");

        // From Request Listener
        content.append("<div class='perf-text'>Page processed in ").append(processingTime).append("ms</div>");

        content.append("</div>");
        content.append("</div>");

        req.setAttribute(PageContent.CONTENT.name(), content.toString());
        RequestDispatcher rd = req.getRequestDispatcher("./app_page");
        rd.include(req, res);
    }
}
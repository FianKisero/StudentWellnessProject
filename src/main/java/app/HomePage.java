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

        /* ── MAIN CONTAINER ────────────────────────────── */
        content.append("<div class='container'>");

        /* ── HERO SECTION WITH IMAGE ───────────────────── */
        content.append("<div style='position:relative; border-radius:20px; overflow:hidden; margin-bottom:40px; box-shadow:0 12px 40px rgba(0,0,0,0.12);'>");
        content.append("<img class='hero-image' src='https://images.unsplash.com/photo-1518241353330-0f7941c2d9b5?ixlib=rb-4.0.3&auto=format&fit=crop&w=1200&q=80' alt='Calm nature scene' style='display:block; width:100%;'>");
        content.append("<div style='position:absolute; inset:0; background:linear-gradient(to top, rgba(15,61,37,0.75) 0%, rgba(15,61,37,0.15) 50%, transparent 100%);'></div>");
        content.append("<div style='position:absolute; bottom:0; left:0; right:0; padding:36px 32px;'>");
        content.append("<div class='welcome-msg'>").append("👋 Welcome back, ").append(username).append("</div>");
        content.append("<h1 style='color:#fff; font-size:2.2rem; margin:10px 0 6px;'>Student Wellness &amp; Productivity Hub</h1>");
        content.append("<p style='color:rgba(255,255,255,0.8); font-size:1.05rem; margin:0;'>Helping you stay mentally strong, balanced, and productive.</p>");
        content.append("</div>");
        content.append("</div>");

        /* ── ACTION CARDS ROW ──────────────────────────── */
        content.append("<div class='nav-cards'>");

        // Log Mood Card
        content.append("<a href='./register_mood' class='btn' style='flex:1; min-width:200px; padding:18px 28px; font-size:1rem; text-align:center;'>");
        content.append("<span style='font-size:1.4rem;'>📝</span> Log Your Mood");
        content.append("</a>");

        // Register Student Card
        content.append("<a href='./register_student' class='btn' style='flex:1; min-width:200px; padding:18px 28px; font-size:1rem; text-align:center;'>");
        content.append("<span style='font-size:1.4rem;'>🎓</span> Register Student");
        content.append("</a>");

        content.append("</div>");

        /* ── FOOTER INFO STRIP ─────────────────────────── */
        content.append("<div class='footer-info'>");

        // From Session Listener — Active Users
        content.append("<div class='status-badge'>");
        content.append("<span class='dot'></span> ").append(activeUsers).append(" student").append(activeUsers != 1 ? "s" : "").append(" online");
        content.append("</div>");

        // From Application Listener — Support
        content.append("<div class='support-text'>✉ ").append(supportEmail).append("</div>");

        // From Request Listener — Perf
        content.append("<div class='perf-text'>⚡ ").append(processingTime).append("ms</div>");

        content.append("</div>");

        content.append("</div>");

        req.setAttribute(PageContent.CONTENT.name(), content.toString());
        RequestDispatcher rd = req.getRequestDispatcher("./app_page");
        rd.include(req, res);
    }
}
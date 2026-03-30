package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TipsPage extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='en'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        writer.println("    <title>Wellness Tips</title>");

        writer.println("    <style>");
        writer.println("        body { font-family: 'Inter', system-ui, -apple-system, sans-serif; background-color: #f8fafc; color: #1e293b; margin: 0; padding: 40px 20px; }");
        writer.println("        .header { text-align: center; margin-bottom: 50px; }");
        writer.println("        h1 { color: #0f172a; font-size: 2.5rem; margin-bottom: 10px; }");
        writer.println("        .subtitle { color: #64748b; font-size: 1.1rem; }");

        // Grid Layout for Tips
        writer.println("        .tips-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 25px; max-width: 1000px; margin: 0 auto; }");

        // Individual Tip Card Styling
        writer.println("        .tip-card { background: white; padding: 30px; border-radius: 16px; border: 1px solid #e2e8f0; transition: all 0.3s ease; display: flex; align-items: flex-start; gap: 15px; }");
        writer.println("        .tip-card:hover { transform: translateY(-5px); box-shadow: 0 10px 25px rgba(0,0,0,0.05); border-color: #3cb371; }");
        writer.println("        .icon { font-size: 1.8rem; background: #ecfdf5; padding: 10px; border-radius: 12px; }");
        writer.println("        .tip-text { font-size: 1.1rem; font-weight: 500; line-height: 1.4; color: #334155; margin-top: 12px; }");

        // Back Button
        writer.println("        .footer { text-align: center; margin-top: 50px; }");
        writer.println("        .back-btn { text-decoration: none; color: #3cb371; font-weight: 600; border: 2px solid #3cb371; padding: 10px 25px; border-radius: 30px; transition: all 0.3s; }");
        writer.println("        .back-btn:hover { background: #3cb371; color: white; }");
        writer.println("    </style>");
        writer.println("</head>");

        writer.println("<body>");

        writer.println("    <div class='header'>");
        writer.println("        <h1>Wellness & Productivity</h1>");
        writer.println("        <p class='subtitle'>Small habits that lead to big changes.</p>");
        writer.println("    </div>");

        writer.println("    <div class='tips-grid'>");

        // Tip 1
        writer.println("        <div class='tip-card'>");
        writer.println("            <div class='icon'>🎯</div>");
        writer.println("            <div class='tip-text'>Set small daily goals to maintain momentum.</div>");
        writer.println("        </div>");

        // Tip 2
        writer.println("        <div class='tip-card'>");
        writer.println("            <div class='icon'>☕</div>");
        writer.println("            <div class='tip-text'>Avoid burnout — schedule short breaks every hour.</div>");
        writer.println("        </div>");

        // Tip 3
        writer.println("        <div class='tip-card'>");
        writer.println("            <div class='icon'>🌱</div>");
        writer.println("            <div class='tip-text'>Focus on staying consistent, not being perfect.</div>");
        writer.println("        </div>");

        // Tip 4
        writer.println("        <div class='tip-card'>");
        writer.println("            <div class='icon'>💧</div>");
        writer.println("            <div class='tip-text'>Prioritize deep sleep and stay hydrated throughout the day.</div>");
        writer.println("        </div>");

        // Tip 5
        writer.println("        <div class='tip-card'>");
        writer.println("            <div class='icon'>🤝</div>");
        writer.println("            <div class='tip-text'>Don't carry it all alone; talk to a friend when stressed.</div>");
        writer.println("        </div>");

        // Tip 6 (Bonus Tip to even out the grid)
        writer.println("        <div class='tip-card'>");
        writer.println("            <div class='icon'>📵</div>");
        writer.println("            <div class='tip-text'>Try a digital detox for 30 minutes before bed.</div>");
        writer.println("        </div>");

        writer.println("    </div>");

        writer.println("    <div class='footer'>");
        writer.println("        <a href='./' class='back-btn'>Return to Hub</a>");
        writer.println("    </div>");

        writer.println("</body></html>");
    }
}
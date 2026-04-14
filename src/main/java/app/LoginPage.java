package app;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class LoginPage extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();

        String userEntry = req.getParameter("username");
        String passEntry = req.getParameter("password");

        // 1. Get Admin params from Config
        ServletConfig config = getServletConfig();
        String adminUser = config.getInitParameter("adminUser");
        String adminPass = config.getInitParameter("adminPass");

        // 2. Get Registered user from Context (THIS WAS MISSING)
        ServletContext context = getServletContext();
        String registeredUser = (String) context.getAttribute("storedUser");
        String registeredPass = (String) context.getAttribute("storedPass");

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        if (userEntry != null && passEntry != null) {

            // Check against Admin OR Registered User
            boolean isAdmin = userEntry.equals(adminUser) && passEntry.equals(adminPass);
            boolean isRegistered = userEntry.equals(registeredUser) && passEntry.equals(registeredPass);

            if (isAdmin || isRegistered) {
                HttpSession session = httpReq.getSession(true);
                session.setAttribute("username", userEntry); // Store the actual name

                httpRes.sendRedirect(httpReq.getContextPath() + "/home");
                return;
            } else {
                req.setAttribute("error", "Invalid username or password!");
            }
        }

        // --- BEAUTIFUL UI RESTORATION ---
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='en'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        writer.println("    <title>Wellness Hub Login</title>");
        writer.println("    <style>");
        writer.println("        body { font-family: 'Inter', sans-serif; background-color: #f0f4f8; margin: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }");
        writer.println("        .auth-card { background: white; padding: 40px; border-radius: 20px; box-shadow: 0 15px 35px rgba(0,0,0,0.1); width: 100%; max-width: 400px; text-align: center; }");
        writer.println("        .brand-icon { font-size: 2.5rem; margin-bottom: 10px; }");
        writer.println("        h2 { color: #1a365d; margin-bottom: 10px; font-weight: 700; }");
        writer.println("        .subtitle { color: #718096; margin-bottom: 30px; font-size: 0.95rem; }");
        writer.println("        .error-msg { background: #fff5f5; color: #c53030; padding: 10px; border-radius: 8px; margin-bottom: 20px; font-size: 0.9rem; border: 1px solid #feb2b2; }");
        writer.println("        .form-group { margin-bottom: 20px; text-align: left; }");
        writer.println("        label { display: block; margin-bottom: 8px; font-size: 0.9rem; color: #4a5568; font-weight: 600; }");
        writer.println("        input { width: 100%; padding: 12px 15px; border: 2px solid #e2e8f0; border-radius: 10px; font-size: 1rem; box-sizing: border-box; transition: all 0.3s ease; }");
        writer.println("        input:focus { border-color: #3cb371; outline: none; box-shadow: 0 0 0 3px rgba(60, 179, 113, 0.1); }");
        writer.println("        .login-btn { background-color: #3cb371; color: white; border: none; padding: 14px; width: 100%; border-radius: 10px; font-size: 1.1rem; font-weight: 700; cursor: pointer; transition: background 0.3s, transform 0.2s; margin-top: 10px; }");
        writer.println("        .login-btn:hover { background-color: #2e8b57; transform: translateY(-1px); }");
        writer.println("        .footer-text { margin-top: 25px; font-size: 0.9rem; color: #718096; }");
        writer.println("        .footer-text a { color: #3cb371; text-decoration: none; font-weight: 600; }");
        writer.println("    </style>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("    <div class='auth-card'>");
        writer.println("        <div class='brand-icon'>🌿</div>");
        writer.println("        <h2>Welcome Back</h2>");
        writer.println("        <p class='subtitle'>Please enter your details to sign in.</p>");

        // Error display
        if (req.getAttribute("error") != null) {
            writer.println("        <div class='error-msg'>" + req.getAttribute("error") + "</div>");
        }

        writer.println("        <form method='POST' action='login'>");
        writer.println("            <div class='form-group'>");
        writer.println("                <label>Username</label>");
        writer.println("                <input type='text' name='username' placeholder='Enter username' required>");
        writer.println("            </div>");
        writer.println("            <div class='form-group'>");
        writer.println("                <label>Password</label>");
        writer.println("                <input type='password' name='password' placeholder='Enter password' required>");
        writer.println("            </div>");
        writer.println("            <button type='submit' class='login-btn'>Sign In</button>");
        writer.println("        </form>");

        writer.println("        <p class='footer-text'>Don't have an account? <a href='register'>Register now</a></p>");
        writer.println("    </div>");

        writer.println("</body></html>");
    }
}
package app;

import jakarta.servlet.*;
import java.io.*;

public class RegisterPage extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        // Logic remains untouched
        if (user != null && pass != null) {
            ServletContext context = getServletContext();
            context.setAttribute("storedUser", user);
            context.setAttribute("storedPass", pass);

            RequestDispatcher dispatcher = req.getRequestDispatcher("login");
            dispatcher.forward(req, res);
            return;
        }

        // Restored High-Quality UI
        PrintWriter writer = res.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='en'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        writer.println("    <title>Join the Wellness Hub</title>");
        writer.println("    <style>");
        writer.println("        body { font-family: 'Inter', sans-serif; background-color: #f0f4f8; margin: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }");
        writer.println("        .auth-card { background: white; padding: 40px; border-radius: 20px; box-shadow: 0 15px 35px rgba(0,0,0,0.1); width: 100%; max-width: 400px; text-align: center; }");
        writer.println("        .brand-icon { font-size: 2.5rem; margin-bottom: 10px; }");
        writer.println("        h2 { color: #1a365d; margin-bottom: 30px; font-weight: 700; }");
        writer.println("        .form-group { margin-bottom: 20px; text-align: left; }");
        writer.println("        label { display: block; margin-bottom: 8px; font-size: 0.9rem; color: #4a5568; font-weight: 600; }");
        writer.println("        input { width: 100%; padding: 12px 15px; border: 2px solid #e2e8f0; border-radius: 10px; font-size: 1rem; box-sizing: border-box; transition: all 0.3s ease; }");
        writer.println("        input:focus { border-color: #3cb371; outline: none; box-shadow: 0 0 0 3px rgba(60, 179, 113, 0.1); }");
        writer.println("        .register-btn { background-color: #3cb371; color: white; border: none; padding: 14px; width: 100%; border-radius: 10px; font-size: 1.1rem; font-weight: 700; cursor: pointer; transition: background 0.3s, transform 0.2s; margin-top: 10px; }");
        writer.println("        .register-btn:hover { background-color: #2e8b57; transform: translateY(-1px); }");
        writer.println("        .footer-text { margin-top: 25px; font-size: 0.9rem; color: #718096; }");
        writer.println("        .footer-text a { color: #3cb371; text-decoration: none; font-weight: 600; }");
        writer.println("    </style>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("    <div class='auth-card'>");
        writer.println("        <div class='brand-icon'>🌱</div>");
        writer.println("        <h2>Create Account</h2>");

        writer.println("        <form method='POST' action='register'>");

        writer.println("            <div class='form-group'>");
        writer.println("                <label>Username</label>");
        writer.println("                <input type='text' name='username' placeholder='Choose a username' required>");
        writer.println("            </div>");

        writer.println("            <div class='form-group'>");
        writer.println("                <label>Password</label>");
        writer.println("                <input type='password' name='password' placeholder='Create a password' required>");
        writer.println("            </div>");

        writer.println("            <button type='submit' class='register-btn'>Sign Up</button>");

        writer.println("        </form>");

        writer.println("        <p class='footer-text'>Already have an account? <a href='login'>Login here</a></p>");
        writer.println("    </div>");

        writer.println("</body></html>");
    }
}
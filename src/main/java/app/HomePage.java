package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HomePage extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='en'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        writer.println("    <title>Wellness Hub</title>");

        // CSS Styling injected directly into the head
        writer.println("    <style>");
        writer.println("        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f0f8ff; color: #2c3e50; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }");
        writer.println("        .container { background-color: #ffffff; max-width: 600px; width: 90%; padding: 40px; border-radius: 12px; box-shadow: 0 8px 16px rgba(0,0,0,0.1); text-align: center; }");
        writer.println("        h1 { color: #2e8b57; margin-bottom: 10px; }");
        writer.println("        p { color: #555555; font-size: 1.1em; margin-bottom: 30px; }");
        writer.println("        .hero-image { width: 100%; height: auto; border-radius: 8px; margin-bottom: 25px; object-fit: cover; max-height: 250px; }");
        writer.println("        .nav-links { display: flex; flex-direction: column; gap: 15px; }");
        writer.println("        .btn { display: inline-block; padding: 12px 24px; background-color: #3cb371; color: white; text-decoration: none; font-size: 1.1em; font-weight: bold; border-radius: 6px; transition: background-color 0.3s ease, transform 0.2s ease; }");
        writer.println("        .btn:hover { background-color: #2e8b57; transform: translateY(-2px); }");
        writer.println("    </style>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("    <div class='container'>");

        // Adding a beautiful, calming placeholder image from Unsplash
        writer.println("        <img class='hero-image' src='https://images.unsplash.com/photo-1518241353330-0f7941c2d9b5?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80' alt='Calm nature scene with rocks and water'>");

        writer.println("        <h1>Student Wellness & Productivity Hub</h1>");
        writer.println("        <p>Helping you stay mentally strong and productive.</p>");

        writer.println("        <div class='nav-links'>");
        writer.println("            <a href='mood' class='btn'>Track Your Mood</a>");
        writer.println("            <a href='tips' class='btn'>View Tips</a>");
        writer.println("            <a href='about' class='btn'>About Us</a>");
        writer.println("        </div>");

        writer.println("    </div>");

        writer.println("</body>");
        writer.println("</html>");
    }
}
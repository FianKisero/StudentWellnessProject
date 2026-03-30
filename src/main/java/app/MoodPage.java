package app;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MoodPage extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();

        String mood = req.getParameter("mood");

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang='en'>");
        writer.println("<head>");
        writer.println("    <meta charset='UTF-8'>");
        writer.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        writer.println("    <title>Mood Tracker</title>");

        // CSS Styling
        writer.println("    <style>");
        writer.println("        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f9f4; color: #333; margin: 0; display: flex; justify-content: center; align-items: center; min-height: 100vh; }");
        writer.println("        .card { background: #ffffff; padding: 40px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.05); max-width: 450px; width: 90%; text-align: center; }");
        writer.println("        h1 { color: #2c3e50; font-size: 1.6em; margin-bottom: 25px; }");
        writer.println("        .hero-img { width: 100%; height: 180px; object-fit: cover; border-radius: 12px; margin-bottom: 25px; }");
        writer.println("        select { width: 100%; padding: 14px; border: 2px solid #e0e0e0; border-radius: 8px; font-size: 1em; margin-bottom: 20px; outline: none; transition: border-color 0.3s ease; appearance: none; cursor: pointer; }");
        writer.println("        select:focus { border-color: #3cb371; }");
        writer.println("        .submit-btn { background-color: #3cb371; color: white; border: none; padding: 14px; font-size: 1.1em; font-weight: bold; border-radius: 8px; cursor: pointer; width: 100%; transition: background 0.3s ease, transform 0.1s ease; }");
        writer.println("        .submit-btn:hover { background-color: #2e8b57; transform: translateY(-2px); }");
        writer.println("        .result-box { margin-top: 30px; padding: 25px; border-radius: 12px; animation: fadeIn 0.6s ease-out; }");
        writer.println("        .result-title { font-size: 1.3em; font-weight: bold; margin-bottom: 8px; }");
        writer.println("        .result-msg { font-size: 1.05em; line-height: 1.5; }");
        writer.println("        .back-link { display: inline-block; margin-top: 25px; color: #95a5a6; text-decoration: none; font-size: 0.95em; transition: color 0.3s; }");
        writer.println("        .back-link:hover { color: #34495e; text-decoration: underline; }");
        writer.println("        @keyframes fadeIn { from { opacity: 0; transform: translateY(-10px); } to { opacity: 1; transform: translateY(0); } }");
        writer.println("    </style>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("    <div class='card'>");

        // Beautiful calm sky image from Unsplash
        writer.println("        <img class='hero-img' src='https://images.unsplash.com/photo-1499209974431-9dddcece7f88?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80' alt='Peaceful sky and clouds'>");

        writer.println("        <h1>How are you feeling today?</h1>");

        // Improved Form Elements
        writer.println("        <form method='post' action='mood'>");
        writer.println("            <select name='mood' required>");
        writer.println("                <option value='' disabled selected>Select your current mood...</option>");
        writer.println("                <option value='happy'>Happy ☀️</option>");
        writer.println("                <option value='stressed'>Stressed 🌩️</option>");
        writer.println("                <option value='tired'>Tired 🌙</option>");
        writer.println("                <option value='motivated'>Motivated 🚀</option>");
        writer.println("            </select>");
        writer.println("            <button type='submit' class='submit-btn'>Log Mood</button>");
        writer.println("        </form>");

        // Dynamic Backend Logic for Styling
        if (mood != null) {
            String bgColor = "#f0f8ff"; // Default fallback
            String textColor = "#2c3e50";
            String message = "";
            String displayMood = mood.substring(0, 1).toUpperCase() + mood.substring(1);

            if (mood.equals("happy")) {
                bgColor = "#fff9c4"; // Soft Yellow
                textColor = "#f57f17";
                message = "Keep spreading positivity! 😊";
            } else if (mood.equals("stressed")) {
                bgColor = "#e8eaf6"; // Calming Lavender/Blue
                textColor = "#3f51b5";
                message = "Take a break, breathe, and relax. 🙏";
            } else if (mood.equals("tired")) {
                bgColor = "#f5f5f5"; // Gentle Gray
                textColor = "#616161";
                message = "Rest is important. Recharge yourself. 😴";
            } else if (mood.equals("motivated")) {
                bgColor = "#ffebee"; // Energetic Soft Red
                textColor = "#d32f2f";
                message = "Great! Use this energy to achieve your goals 💪";
            }

            // Outputting the dynamic result box
            writer.println("        <div class='result-box' style='background-color: " + bgColor + "; color: " + textColor + ";'>");
            writer.println("            <div class='result-title'>You feel: " + displayMood + "</div>");
            writer.println("            <div class='result-msg'>" + message + "</div>");
            writer.println("        </div>");
        }

        writer.println("        <a href='./' class='back-link'>&larr; Back Home</a>");
        writer.println("    </div>");

        writer.println("</body></html>");
    }
}
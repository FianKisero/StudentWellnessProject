package app.framework;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/app_page")
public class AppPage extends HttpServlet {

    @Inject
    private Cohort12Framework framework;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {



        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Student Wellness Hub</title>");

        /* GOOGLE FONTS – Outfit for headings, Inter for body */
        out.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
        out.println("<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Outfit:wght@400;500;600;700;800&family=Inter:wght@300;400;500;600;700&display=swap' rel='stylesheet'>");

        /* FONT AWESOME CDN */
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css'>");

        out.println("<style>");

        /* ── DESIGN TOKENS ─────────────────────────────── */
        out.println(":root {");
        out.println("  --primary: #3cb371;");
        out.println("  --primary-light: #4fd98b;");
        out.println("  --dark: #1a5c38;");
        out.println("  --darker: #0f3d25;");
        out.println("  --accent: #10b981;");
        out.println("  --surface: #ffffff;");
        out.println("  --surface-alt: #f8fdfb;");
        out.println("  --bg: #f0faf5;");
        out.println("  --text: #1e293b;");
        out.println("  --text-muted: #64748b;");
        out.println("  --border: #e2e8f0;");
        out.println("  --shadow-sm: 0 1px 3px rgba(0,0,0,0.06);");
        out.println("  --shadow-md: 0 4px 16px rgba(0,0,0,0.08);");
        out.println("  --shadow-lg: 0 12px 40px rgba(0,0,0,0.10);");
        out.println("  --radius: 14px;");
        out.println("  --radius-lg: 20px;");
        out.println("}");

        /* ── RESET & BASE ──────────────────────────────── */
        out.println("*, *::before, *::after { margin:0; padding:0; box-sizing:border-box; }");
        out.println("html { scroll-behavior: smooth; }");
        out.println("body {");
        out.println("  font-family: 'Inter', system-ui, -apple-system, sans-serif;");
        out.println("  font-weight: 400;");
        out.println("  font-size: 15px;");
        out.println("  line-height: 1.7;");
        out.println("  color: var(--text);");
        out.println("  background: var(--bg);");
        out.println("  background-image: radial-gradient(circle at 20% 50%, rgba(60,179,113,0.04) 0%, transparent 50%),");
        out.println("                    radial-gradient(circle at 80% 20%, rgba(16,185,129,0.05) 0%, transparent 50%);");
        out.println("  min-height: 100vh;");
        out.println("}");

        /* ── TYPOGRAPHY ────────────────────────────────── */
        out.println("h1, h2, h3, h4 { font-family: 'Outfit', sans-serif; font-weight: 700; line-height: 1.3; color: var(--darker); }");
        out.println("h1 { font-size: 2.2rem; letter-spacing: -0.02em; }");
        out.println("h2 { font-size: 1.6rem; letter-spacing: -0.01em; }");
        out.println("h3 { font-size: 1.15rem; }");
        out.println("p  { color: var(--text-muted); font-size: 1rem; }");

        /* ── NAVBAR ────────────────────────────────────── */
        out.println(".navbar {");
        out.println("  display: flex; justify-content: space-between; align-items: center;");
        out.println("  padding: 14px 32px;");
        out.println("  background: linear-gradient(135deg, var(--darker) 0%, var(--dark) 100%);");
        out.println("  color: #fff;");
        out.println("  position: sticky; top: 0; z-index: 100;");
        out.println("  box-shadow: 0 2px 20px rgba(0,0,0,0.15);");
        out.println("}");
        out.println(".logo {");
        out.println("  font-family: 'Outfit', sans-serif;");
        out.println("  font-size: 20px; font-weight: 700;");
        out.println("  display: flex; align-items: center; gap: 8px;");
        out.println("}");
        out.println(".logo::before { content: '🌿'; font-size: 22px; }");
        out.println(".nav-links { display: flex; gap: 6px; }");
        out.println(".nav-links a {");
        out.println("  color: rgba(255,255,255,0.85); text-decoration: none;");
        out.println("  padding: 8px 16px; border-radius: 8px;");
        out.println("  font-size: 14px; font-weight: 500;");
        out.println("  transition: all 0.25s ease;");
        out.println("}");
        out.println(".nav-links a:hover { background: rgba(255,255,255,0.15); color: #fff; }");

        /* ── HERO SECTION ──────────────────────────────── */
        out.println(".hero { padding: 60px 20px; text-align: center; background: #111827; color: #fff; }");
        out.println(".hero h1 { font-size: 36px; margin-bottom: 10px; color: #fff; }");
        out.println(".hero p  { font-size: 18px; color: #d1d5db; }");

        /* ── SECTION ───────────────────────────────────── */
        out.println(".section { padding: 40px 20px; max-width: 1000px; margin: auto; }");
        out.println(".section h2 { text-align: center; margin-bottom: 20px; }");
        out.println(".features { display: flex; flex-wrap: wrap; gap: 20px; justify-content: center; }");

        /* ── CONTAINER ─────────────────────────────────── */
        out.println(".container {");
        out.println("  width: 92%; max-width: 960px;");
        out.println("  margin: 0 auto;");
        out.println("  padding: 48px 0 60px;");
        out.println("  animation: fadeUp 0.5s ease-out;");
        out.println("}");

        /* ── CARD ──────────────────────────────────────── */
        out.println(".card {");
        out.println("  background: var(--surface);");
        out.println("  padding: 32px 36px;");
        out.println("  border-radius: var(--radius-lg);");
        out.println("  box-shadow: var(--shadow-md);");
        out.println("  border: 1px solid var(--border);");
        out.println("  transition: box-shadow 0.3s ease;");
        out.println("}");
        out.println(".card:hover { box-shadow: var(--shadow-lg); }");
        out.println(".card h3 { margin-top: 0; font-size: 1.15rem; }");
        out.println(".card p  { font-size: 0.95rem; color: var(--text-muted); }");

        /* ── TABLE ─────────────────────────────────────── */
        out.println("table { width: 100%; border-collapse: separate; border-spacing: 0; margin-top: 20px; border-radius: var(--radius); overflow: hidden; }");
        out.println("th, td { padding: 14px 16px; text-align: left; font-size: 0.93rem; }");
        out.println("th { background: linear-gradient(135deg, var(--dark), var(--primary)); color: #fff; font-weight: 600; letter-spacing: 0.02em; }");
        out.println("td { border-bottom: 1px solid var(--border); }");
        out.println("tr:nth-child(even) { background: var(--surface-alt); }");
        out.println("tr:hover td { background: rgba(60,179,113,0.04); }");
        out.println("tbody tr:last-child td { border-bottom: none; }");

        /* ── ACTION BUTTONS ────────────────────────────── */
        out.println(".actions { display: flex; gap: 8px; }");
        out.println(".icon-btn {");
        out.println("  display: inline-flex; align-items: center; justify-content: center;");
        out.println("  width: 34px; height: 34px; border-radius: 8px;");
        out.println("  text-decoration: none; color: #fff; font-size: 13px;");
        out.println("  transition: all 0.2s ease;");
        out.println("}");
        out.println(".edit-btn { background: var(--dark); }");
        out.println(".edit-btn:hover { background: var(--primary); transform: translateY(-1px); }");
        out.println(".delete-btn { background: #94a3b8; }");
        out.println(".delete-btn:hover { background: #ef4444; transform: translateY(-1px); }");

        /* ── FORM ──────────────────────────────────────── */
        out.println(".form-group { margin-bottom: 18px; }");
        out.println("label { display: block; margin-bottom: 6px; font-weight: 600; font-size: 0.9rem; color: var(--text); }");
        out.println("input, select {");
        out.println("  width: 100%; padding: 11px 14px;");
        out.println("  border: 2px solid var(--border); border-radius: 10px;");
        out.println("  font-size: 0.95rem; font-family: 'Inter', sans-serif;");
        out.println("  background: var(--surface);");
        out.println("  transition: all 0.25s ease;");
        out.println("}");
        out.println("input:focus, select:focus {");
        out.println("  outline: none; border-color: var(--primary);");
        out.println("  box-shadow: 0 0 0 3px rgba(60,179,113,0.12);");
        out.println("}");

        /* ── BUTTONS ───────────────────────────────────── */
        out.println(".btn {");
        out.println("  display: inline-flex; align-items: center; justify-content: center; gap: 8px;");
        out.println("  padding: 12px 28px;");
        out.println("  background: linear-gradient(135deg, var(--primary) 0%, var(--dark) 100%);");
        out.println("  color: #fff; border: none; border-radius: 10px;");
        out.println("  cursor: pointer; font-size: 0.95rem; font-weight: 600;");
        out.println("  font-family: 'Inter', sans-serif;");
        out.println("  letter-spacing: 0.01em;");
        out.println("  text-decoration: none;");
        out.println("  transition: all 0.3s ease;");
        out.println("  box-shadow: 0 4px 14px rgba(60,179,113,0.25);");
        out.println("}");
        out.println(".btn:hover {");
        out.println("  background: linear-gradient(135deg, var(--primary-light) 0%, var(--primary) 100%);");
        out.println("  transform: translateY(-2px);");
        out.println("  box-shadow: 0 6px 20px rgba(60,179,113,0.35);");
        out.println("}");
        out.println(".btn:active { transform: translateY(0); }");

        /* ── LINKS ─────────────────────────────────────── */
        out.println(".back-link {");
        out.println("  display: inline-flex; align-items: center; gap: 6px;");
        out.println("  margin-top: 22px; text-decoration: none;");
        out.println("  color: var(--primary); font-weight: 600; font-size: 0.95rem;");
        out.println("  transition: all 0.25s ease;");
        out.println("}");
        out.println(".back-link:hover { color: var(--dark); gap: 10px; }");

        /* ── WELCOME MSG ───────────────────────────────── */
        out.println(".welcome-msg {");
        out.println("  display: inline-block;");
        out.println("  background: linear-gradient(135deg, rgba(60,179,113,0.1), rgba(16,185,129,0.08));");
        out.println("  color: var(--dark);");
        out.println("  padding: 8px 20px; border-radius: 30px;");
        out.println("  font-size: 0.9rem; font-weight: 600;");
        out.println("  margin-bottom: 16px;");
        out.println("  border: 1px solid rgba(60,179,113,0.2);");
        out.println("}");

        /* ── STATUS BADGES ─────────────────────────────── */
        out.println(".status-badge {");
        out.println("  display: inline-flex; align-items: center; gap: 8px;");
        out.println("  font-size: 0.88rem; color: var(--text-muted); font-weight: 500;");
        out.println("}");
        out.println(".dot {");
        out.println("  width: 8px; height: 8px; border-radius: 50%;");
        out.println("  background: var(--accent);");
        out.println("  animation: pulse 2s infinite;");
        out.println("}");
        out.println(".support-text, .perf-text { font-size: 0.85rem; color: var(--text-muted); margin-top: 4px; }");

        /* ── HERO IMAGE ────────────────────────────────── */
        out.println(".hero-image {");
        out.println("  width: 100%; max-height: 360px; object-fit: cover;");
        out.println("  border-radius: var(--radius-lg);");
        out.println("  box-shadow: var(--shadow-lg);");
        out.println("}");

        /* ── NAV-LINKS (body buttons) ──────────────────── */
        out.println(".nav-cards { display: flex; gap: 16px; flex-wrap: wrap; justify-content: center; margin-top: 10px; }");

        /* ── FOOTER INFO ───────────────────────────────── */
        out.println(".footer-info {");
        out.println("  margin-top: 40px; padding-top: 24px;");
        out.println("  border-top: 1px solid var(--border);");
        out.println("  display: flex; flex-wrap: wrap; gap: 20px; align-items: center; justify-content: center;");
        out.println("}");

        /* ── ANIMATIONS ────────────────────────────────── */
        out.println("@keyframes fadeUp {");
        out.println("  from { opacity: 0; transform: translateY(16px); }");
        out.println("  to   { opacity: 1; transform: translateY(0); }");
        out.println("}");
        out.println("@keyframes pulse {");
        out.println("  0%, 100% { opacity: 1; }");
        out.println("  50%      { opacity: 0.4; }");
        out.println("}");

        /* ── RESPONSIVE ────────────────────────────────── */
        out.println("@media (max-width: 640px) {");
        out.println("  .navbar { padding: 12px 16px; flex-direction: column; gap: 10px; }");
        out.println("  .container { width: 95%; padding: 28px 0 40px; }");
        out.println("  h1 { font-size: 1.7rem; }");
        out.println("  .card { padding: 24px 20px; }");
        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */
        out.println("<div class='navbar'>");
        out.println("<div class='logo'>");
        out.println(request.getSession().getAttribute("username") != null ? request.getSession().getAttribute("username") : "Student");
        out.println("</div>");
        out.println("<div class='nav-links'>");
        out.println("<a href='./home'>Home</a>");
        out.println(framework.generateMenuItem());
        out.println("</div>");
        out.println("</div>");

        /* CONTENT */
        out.println(request.getAttribute(PageContent.CONTENT.name()));

        out.println("</body>");
        out.println("</html>");
    }
}

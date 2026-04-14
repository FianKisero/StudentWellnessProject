package app;

import jakarta.servlet.http.*;
import java.io.*;

public class FooterPage extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        PrintWriter out = res.getWriter();
        out.println("<hr><p>© Student Wellness Hub</p>");
    }
}
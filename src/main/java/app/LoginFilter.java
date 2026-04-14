package app;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String contextPath = req.getContextPath();

        // URIs to allow without a session
        String loginURI = contextPath + "/login";
        String registerURI = contextPath + "/register";
        String requestURI = req.getRequestURI();

        // FIXED: Check for "username" to match the LoginPage session attribute
        boolean loggedIn = session != null && session.getAttribute("username") != null;

        // Use endsWith or contains to be safer with context paths
        boolean isLoginRequest = requestURI.endsWith("/login");
        boolean isRegisterRequest = requestURI.endsWith("/register");

        // Allow static resources (CSS/Images) if you have any
        boolean isStaticResource = requestURI.contains("/css/") || requestURI.contains("/images/");

        if (loggedIn || isLoginRequest || isRegisterRequest || isStaticResource) {
            chain.doFilter(request, response);
        } else {
            // Redirect to login if not authenticated
            res.sendRedirect(loginURI);
        }
    }
}
package app.action;

import app.framework.Cohort12Framework;
import app.framework.PageContent;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class BaseListAction<T> extends BaseAction<T> {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(PageContent.CONTENT.name(), super.framework.htmlTable(getType(), returnData()));
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);
    }
}

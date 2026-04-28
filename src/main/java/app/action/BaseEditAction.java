package app.action;

import app.framework.Cohort12Table;
import app.framework.PageContent;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BaseEditAction<T> extends BaseAction<T> {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("./home");
            return;
        }

        Long id = Long.parseLong(idParam);
        T entity = genericDao.findById((Long) id);

        if (entity == null) {
            response.sendRedirect("./home");
            return;
        }

        request.setAttribute(PageContent.CONTENT.name(),
                framework.htmlEditForm(getType(), entity));
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            T entity = this.serializeForm(req.getParameterMap());
            genericDao.update(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (this.getType().isAnnotationPresent(Cohort12Table.class)) {
            resp.sendRedirect(this.getType()
                .getAnnotation(Cohort12Table.class).tableUrl());
        } else {
            resp.sendRedirect("./home");
        }
    }
}

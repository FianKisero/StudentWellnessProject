package app.action;

import app.framework.Cohort12Table;
import app.framework.PageContent;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BaseUpdateAction<T> extends BaseAction<T> {

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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to list if accessed via GET
        if (this.getType().isAnnotationPresent(Cohort12Table.class)) {
            response.sendRedirect(this.getType()
                .getAnnotation(Cohort12Table.class).tableUrl());
        } else {
            response.sendRedirect("./home");
        }
    }
}

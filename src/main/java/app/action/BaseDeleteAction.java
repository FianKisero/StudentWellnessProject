package app.action;

import app.framework.Cohort12Table;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BaseDeleteAction<T> extends BaseAction<T> {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("./home");
            return;
        }

        Long id = Long.parseLong(idParam);
        genericDao.delete(id);

        if (this.getType().isAnnotationPresent(Cohort12Table.class)) {
            response.sendRedirect(this.getType()
                .getAnnotation(Cohort12Table.class).tableUrl());
        } else {
            response.sendRedirect("./home");
        }
    }
}

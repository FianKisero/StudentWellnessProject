package app.framework;

import app.helper.ClassScanner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class Cohort12Framework {

    @Inject
    private ClassScanner clazzScanner;


    public String htmlForm(Class<?> clazz){

        if (!clazz.isAnnotationPresent(Cohort12Form.class))
            return "";

        Cohort12Form formAnnot = clazz.getAnnotation(Cohort12Form.class);

        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<div class='container'>");
        formBuilder.append("<div class='card'>");
        formBuilder.append("<h2>").append(formAnnot.label()).append("</h2>");
        formBuilder.append("<form method='").append(formAnnot.method()).append("' action='").append(formAnnot.actionUrl()).append("'>");

        formBuilder.append("<div class='form-group'>");
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12FormField.class))
                continue;

            Cohort12FormField fieldInfo = field.getAnnotation(Cohort12FormField.class);
            formBuilder.append("<label>").append(fieldInfo.label()).append(":</label>");
            formBuilder.append("<input type='text' name='").append(fieldInfo.name().isEmpty() ? field.getName() : fieldInfo.name()).append("' placeholder='Enter ").append(fieldInfo.placeholder()).append("' required />");
        }
        formBuilder.append("</div>");

        formBuilder.append("<button type='submit' class='btn'>Register</button>");
        formBuilder.append("</form>");

        if (clazz.isAnnotationPresent(Cohort12Table.class)) {
            Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);
            formBuilder.append("<a href=\"")
                .append(cohort12Table.tableUrl())
                .append("\"  class='back-link'>&larr; List Registered ").append(cohort12Table.label()).append(" </a>");
        }

        formBuilder.append("</div>");
        formBuilder.append("</div>");

        return formBuilder.toString();
    }

    public String htmlTable(Class<?> clazz,
         List<?> tableData) {

        if (!clazz.isAnnotationPresent(Cohort12Table.class))
            return "";

        Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);

        StringBuilder tableBuilder = new StringBuilder();

        tableBuilder.append("<div class='container'>");
        tableBuilder.append("<div class='card'>");
        tableBuilder.append("<h2>")
            .append(cohort12Table.label())
            .append(" Registered</h2>");

        tableBuilder.append("<table>");

        class ColMetaData {
            final String fieldName;
            final String columnName;

            ColMetaData(String fieldName, String columnName){
                this.fieldName = fieldName;
                this.columnName = columnName;
            }

        }

        List<ColMetaData> colsMedaData = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12TableCol.class))
                continue;

            Cohort12TableCol tableCol = field.getAnnotation(Cohort12TableCol.class);

            colsMedaData.add(new ColMetaData(field.getName(), tableCol.label()));
        }

        int colWidth = 96/colsMedaData.size();
        tableBuilder.append("<thead><tr>");
        for (ColMetaData colMedaData : colsMedaData)
            tableBuilder.append("<th style='width:")
                .append(colWidth).append("%;'>")
                .append(colMedaData.columnName).append("</th>");

        tableBuilder.append("<th style='width:2%;></th><th style='width:2%;></th>");
        tableBuilder.append("</tr></thead>");
        tableBuilder.append("<tbody>");

        for (Object data : tableData) {
            tableBuilder.append("<tr>");
            for (ColMetaData colMedaData : colsMedaData) {
                try {
                    Field field = data.getClass().getDeclaredField(colMedaData.fieldName);
                    field.setAccessible(true);
                    tableBuilder.append("<td style='border: 1px solid #000; padding: 8px;'>")
                        .append(field.get(data))
                        .append("</td>");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
            tableBuilder.append("<td class='actions'>");

            try {
                Field idField = clazz.getDeclaredField("id");
                idField.setAccessible(true);

                String editUrl = cohort12Table.editUrl().isEmpty()
                    ? "./" + cohort12Table.tableUrl().replace("./list_", "edit_").replace("list_", "edit_")
                    : cohort12Table.editUrl();
                String deleteUrl = cohort12Table.deleteUrl().isEmpty()
                    ? "./" + cohort12Table.tableUrl().replace("./list_", "delete_").replace("list_", "delete_")
                    : cohort12Table.deleteUrl();

                /* EDIT BUTTON */
                tableBuilder.append("<a href='").append(editUrl).append("?id=")
                    .append(idField.get(data))
                    .append("' class='icon-btn edit-btn' title='Edit'>");
                tableBuilder.append("<i class='fa-solid fa-pen'></i>");
                tableBuilder.append("</a>");

                /* DELETE BUTTON */
                tableBuilder.append("<a href='").append(deleteUrl).append("?id=")
                    .append(idField.get(data))
                    .append("' class='icon-btn delete-btn' title='Delete' onclick='return confirm(\"Delete this ")
                    .append(cohort12Table.label()).append("?\")'>");
                tableBuilder.append("<i class='fa-solid fa-trash'></i>");
                tableBuilder.append("</a>");

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            tableBuilder.append("</td>");

            tableBuilder.append("</tr>");

        }
        tableBuilder.append("</tbody>");
        tableBuilder.append("</table>");

        tableBuilder.append("<a href=\"")
            .append(cohort12Table.registerUrl())
            .append("\" class='back-link'>&larr; Register ")
            .append(cohort12Table.label()).append(" </a>");
        tableBuilder.append("</div>");
        tableBuilder.append("</div>");

        return tableBuilder.toString();
    }

    /**
     * Generates an HTML edit form pre-populated with existing entity data.
     */
    public String htmlEditForm(Class<?> clazz, Object entity) {

        if (!clazz.isAnnotationPresent(Cohort12Form.class))
            return "";

        Cohort12Form formAnnot = clazz.getAnnotation(Cohort12Form.class);

        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<div class='container'>");
        formBuilder.append("<div class='card'>");
        formBuilder.append("<h2>Edit ").append(formAnnot.label().replace("Register ", "").replace("Log a ", "")).append("</h2>");
        formBuilder.append("<form method='POST' action='").append(formAnnot.actionUrl().replace("register_", "update_").replace("register_", "update_")).append("'>");

        // Hidden ID field
        try {
            Field idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            formBuilder.append("<input type='hidden' name='id' value='").append(idField.get(entity)).append("' />");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        formBuilder.append("<div class='form-group'>");
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12FormField.class))
                continue;

            Cohort12FormField fieldInfo = field.getAnnotation(Cohort12FormField.class);
            String fieldName = fieldInfo.name().isEmpty() ? field.getName() : fieldInfo.name();

            // Get current value
            String currentValue = "";
            try {
                field.setAccessible(true);
                Object val = field.get(entity);
                if (val != null) currentValue = val.toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            formBuilder.append("<label>").append(fieldInfo.label()).append(":</label>");
            formBuilder.append("<input type='text' name='").append(fieldName)
                .append("' value='").append(currentValue)
                .append("' placeholder='Enter ").append(fieldInfo.placeholder()).append("' required />");
        }
        formBuilder.append("</div>");

        formBuilder.append("<button type='submit' class='btn'>Update</button>");
        formBuilder.append("</form>");

        if (clazz.isAnnotationPresent(Cohort12Table.class)) {
            Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);
            formBuilder.append("<a href=\"")
                .append(cohort12Table.tableUrl())
                .append("\"  class='back-link'>&larr; Back to ").append(cohort12Table.label()).append(" </a>");
        }

        formBuilder.append("</div>");
        formBuilder.append("</div>");

        return formBuilder.toString();

    }

    public String generateMenuItem(){
        Set<Class<?>> entities = clazzScanner.scanForMenuItem("app.model");

        return entities.stream()
            .filter(clazz -> clazz.isAnnotationPresent(PageMenuItem.class))
            .map(clazz -> {
                PageMenuItem annotation = clazz.getAnnotation(PageMenuItem.class);
                return "<a href='./" + annotation.url() + "'>" + annotation.label() + "</a>";
            })
            .collect(Collectors.joining("\n"));
    }
}

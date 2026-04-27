package app.db;

import app.framework.DbColumn;
import app.framework.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlGenerator {

    public static String getCreateTableSql(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(DbTable.class)) {
            return null;
        }

        String tableName = clazz.getAnnotation(DbTable.class).name();
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");

        Field[] fields = clazz.getDeclaredFields();
        List<String> columns = new ArrayList<>();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(DbColumn.class)) continue;

            DbColumn col = field.getAnnotation(DbColumn.class);
            String columnName = col.name();
            String sqlType = getSqlType(field.getType());

            if (col.primaryKey() && col.autoIncrement()) {
                columns.add(columnName + " " + sqlType + " AUTO_INCREMENT PRIMARY KEY");
            } else if (col.primaryKey()) {
                columns.add(columnName + " " + sqlType + " PRIMARY KEY");
            } else {
                columns.add(columnName + " " + sqlType + (col.nullable() ? "" : " NOT NULL"));
            }
        }

        sql.append(String.join(", ", columns));
        sql.append(")");
        return sql.toString();
    }

    private static String getSqlType(Class<?> javaType) {
        if (javaType == String.class) {
            return "VARCHAR(255)";
        } else if (javaType == int.class || javaType == Integer.class) {
            return "INT";
        } else if (javaType == long.class || javaType == Long.class) {
            return "BIGINT";
        } else if (javaType == double.class || javaType == Double.class) {
            return "DOUBLE";
        } else if (javaType == boolean.class || javaType == Boolean.class) {
            return "BOOLEAN";
        } else if (javaType == java.util.Date.class || javaType == java.sql.Date.class) {
            return "DATE";
        } else if (javaType == java.math.BigDecimal.class) {
            return "DECIMAL(10,2)";
        } else {
            return "VARCHAR(255)";
        }
    }
}

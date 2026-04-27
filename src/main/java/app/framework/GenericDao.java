package app.framework;


import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * Generic DAO for database operations.
 * Demonstrates: Generic pattern with reflection for ORM-like operations
 */
public class GenericDao<T, ID> {

    private final Class<T> entityClass;
    private final String tableName;
    private final List<Field> columns = new ArrayList<>();
    private Field idField;

    private final DataSource ds;

    public GenericDao(Class<T> entityClass, DataSource ds) {
        this.entityClass = entityClass;
        this.ds = ds;

        if (!entityClass.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Missing @DbTable on " + entityClass.getName());
        }

        this.tableName = entityClass.getAnnotation(DbTable.class).name();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DbColumn.class)) {
                field.setAccessible(true);
                columns.add(field);

                if (field.getAnnotation(DbColumn.class).primaryKey()) {
                    idField = field;
                }
            }
        }

        if (idField == null) {
            throw new RuntimeException("No primary key defined in " + entityClass.getName());
        }
    }

    // ---------------------------
    // CREATE - Insert a new entity
    // ---------------------------
    public void save(T entity) {
        try (Connection conn = ds.getConnection()) {

            List<String> colNames = new ArrayList<>();
            List<String> placeholders = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            for (Field field : columns) {
                DbColumn col = field.getAnnotation(DbColumn.class);

                if (col.autoIncrement()) continue;

                colNames.add(col.name());
                placeholders.add("?");
                values.add(field.get(entity));
            }

            String sql = "INSERT INTO " + tableName +
                    " (" + String.join(",", colNames) + ") VALUES (" +
                    String.join(",", placeholders) + ")";

            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < values.size(); i++) {
                ps.setObject(i + 1, values.get(i));
            }

            ps.executeUpdate();
            System.out.println("Saved entity to " + tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // READ - Find entity by ID
    // ---------------------------
    public T findById(ID id) {
        try (Connection conn = ds.getConnection()) {

            String idColumn = idField.getAnnotation(DbColumn.class).name();

            String sql = "SELECT * FROM " + tableName +
                    " WHERE " + idColumn + " = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToEntity(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ---------------------------
    // READ - Find all entities
    // ---------------------------
    public List<T> findAll() {
        List<T> results = new ArrayList<>();

        try (Connection conn = ds.getConnection()) {
            String sql = "SELECT * FROM " + tableName;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                results.add(mapResultSetToEntity(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    // ---------------------------
    // UPDATE - Update an entity
    // ---------------------------
    public void update(T entity) {
        try (Connection conn = ds.getConnection()) {

            List<String> setClauses = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            for (Field field : columns) {
                DbColumn col = field.getAnnotation(DbColumn.class);
                if (!col.primaryKey()) {
                    setClauses.add(col.name() + " = ?");
                    values.add(field.get(entity));
                }
            }

            String idColumn = idField.getAnnotation(DbColumn.class).name();
            Object idValue = idField.get(entity);

            String sql = "UPDATE " + tableName + " SET " +
                    String.join(",", setClauses) +
                    " WHERE " + idColumn + " = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < values.size(); i++) {
                ps.setObject(i + 1, values.get(i));
            }
            ps.setObject(values.size() + 1, idValue);

            ps.executeUpdate();
            System.out.println("Updated entity in " + tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // DELETE - Delete an entity by ID
    // ---------------------------
    public void delete(ID id) {
        try (Connection conn = ds.getConnection()) {

            String idColumn = idField.getAnnotation(DbColumn.class).name();

            String sql = "DELETE FROM " + tableName +
                    " WHERE " + idColumn + " = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);

            ps.executeUpdate();
            System.out.println("Deleted entity from " + tableName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // Helper method to map ResultSet to Entity
    // ---------------------------
    private T mapResultSetToEntity(ResultSet rs) throws Exception {
        T entity = entityClass.getDeclaredConstructor().newInstance();

        for (Field field : columns) {
            DbColumn col = field.getAnnotation(DbColumn.class);
            String columnName = col.name();

            Object value = rs.getObject(columnName);
            field.set(entity, value);
        }

        return entity;
    }

    public String getTableName() {
        return tableName;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
}
package app.bootstrap;

import app.db.DataSourceHelper;
import app.framework.DbTable;
import app.helper.ClassScanner;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Set;

/**
 * Database bootstrap for initializing the database.
 * Demonstrates: @Dependent scope, @Inject, @InitBootstrap qualifier
 */
@InitBootstrap
@Dependent
public class DatabaseBootstrap implements Bootstrap {

    @Inject
    private ClassScanner classScanner;

    @Override
    public void process() {
        try {
            // Ensure database exists
            createDatabaseIfNotExists();

            // Scan for entity classes
            Set<Class<?>> entities = classScanner.scanForDbTables("app.model");

            System.out.println("=== Database Bootstrap ===");
            System.out.println("Found " + entities.size() + " entity classes:");
            for (Class<?> entity : entities) {
                System.out.println("  - " + entity.getSimpleName());
                if (entity.isAnnotationPresent(DbTable.class)) {
                    DbTable table = entity.getAnnotation(DbTable.class);
                    System.out.println("    Table: " + table.name());
                }
            }
            System.out.println("=== Bootstrap Complete ===");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseIfNotExists() {
        System.out.println("Using H2 Database. No manual database creation needed.");
    }
}
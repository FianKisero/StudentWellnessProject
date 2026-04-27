package app.db;

import app.model.Student;
import app.model.MoodEntry;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.Statement;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Inject
    @Named("primaryDataSource")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Class<?>[] modelClasses = {Student.class, MoodEntry.class};

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            for (Class<?> clazz : modelClasses) {
                String createTableSql = SqlGenerator.getCreateTableSql(clazz);
                if (createTableSql != null) {
                    stmt.executeUpdate(createTableSql);
                    System.out.println("Created table for " + clazz.getSimpleName());
                }
            }
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}

package app.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import javax.sql.DataSource;

/**
 * DataSource helper class.
 * Demonstrates: Injecting DataSource using CDI
 */
@ApplicationScoped
public class DataSourceHelper {

    private HikariDataSource dataSource;

    private final String HOST = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
    private final int PORT = System.getenv("DB_PORT") != null ? Integer.parseInt(System.getenv("DB_PORT")) : 3306;
    private final String DB_NAME = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "student_wellness_db";

    @PostConstruct
    public void init() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:" + DB_NAME + ";DB_CLOSE_DELAY=-1");
        config.setDriverClassName("org.h2.Driver");
        config.setUsername("sa");
        config.setPassword("");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);

        dataSource = new HikariDataSource(config);
    }

    @Produces
    @Named("primaryDataSource")
    public DataSource produceDataSource() {
        return dataSource;
    }

    @PreDestroy
    public void cleanup() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    public String getBaseUrlWithoutDB() {
        return "jdbc:h2:mem:";
    }

    public String getDbName() {
        return DB_NAME;
    }


}
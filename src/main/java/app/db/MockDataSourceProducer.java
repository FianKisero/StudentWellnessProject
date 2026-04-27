package app.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import javax.sql.DataSource;

/**
 * Demonstrates the @Alternative CDI concept.
 * This can be activated in beans.xml to replace the primary DataSource.
 */
@ApplicationScoped
@Alternative
public class MockDataSourceProducer {

    @Produces
    @Named("primaryDataSource")
    @Alternative
    public DataSource produceMockDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:mock_wellness_db;DB_CLOSE_DELAY=-1");
        config.setDriverClassName("org.h2.Driver");
        config.setUsername("sa");
        config.setPassword("");
        
        System.out.println("Using MOCK DataSource Alternative!");
        return new HikariDataSource(config);
    }
}

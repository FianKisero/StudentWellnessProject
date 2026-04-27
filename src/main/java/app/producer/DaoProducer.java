package app.producer;

import app.framework.GenericDao;
import app.model.MoodEntry;
import app.model.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.inject.Named;
import javax.sql.DataSource;

/**
 * Producer for GenericDao instances.
 * Demonstrates: @Produces annotation for creating beans
 */
@ApplicationScoped
public class DaoProducer {

    /**
     * Produces a Student DAO.
     * Demonstrates: @Produces with @Singleton
     */
    @Produces
    @Singleton
    public GenericDao<Student, Long> produceStudentDao(@Named("primaryDataSource") DataSource dataSource) {
        return new GenericDao<>(Student.class, dataSource);
    }

    /**
     * Produces a MoodEntry DAO.
     * Demonstrates: @Produces annotation
     */
    @Produces
    @Singleton
    public GenericDao<MoodEntry, Long> produceMoodEntryDao(@Named("primaryDataSource") DataSource dataSource) {
        return new GenericDao<>(MoodEntry.class, dataSource);
    }
}
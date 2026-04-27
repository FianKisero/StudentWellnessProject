package app.helper;

import app.framework.DbTable;
import app.framework.PageMenuItem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

@Dependent
public class ClassScanner {

    public Set<Class<?>> scanForDbTables(String basePackage) {

        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> annotatedClasses =
                reflections.getTypesAnnotatedWith(DbTable.class);

        return new HashSet<>(annotatedClasses);
    }

    public Set<Class<?>> scanForMenuItem(String basePackage) {

        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> annotatedClasses =
                reflections.getTypesAnnotatedWith(PageMenuItem.class);

        return new HashSet<>(annotatedClasses);
    }
}
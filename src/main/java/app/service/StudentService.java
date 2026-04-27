package app.service;

import app.framework.GenericDao;
import app.model.MoodEntry;
import app.model.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * Student service for managing student data.
 * Demonstrates: Using GenericDao, constructor injection
 */
@ApplicationScoped
public class StudentService {

    // Constructor injection - demonstrates constructor injection point
    private final GenericDao<Student, Long> studentDao;
    private final GenericDao<MoodEntry, Long> moodEntryDao;

    // No-args constructor required for CDI proxying
    public StudentService() {
        this.studentDao = null;
        this.moodEntryDao = null;
    }

    // Constructor injection
    @Inject
    public StudentService(
            @jakarta.enterprise.inject.Default GenericDao<Student, Long> studentDao,
            @jakarta.enterprise.inject.Default GenericDao<MoodEntry, Long> moodEntryDao) {
        this.studentDao = studentDao;
        this.moodEntryDao = moodEntryDao;
    }

    public void saveStudent(Student student) {
        if (studentDao != null) {
            studentDao.save(student);
        } else {
            System.out.println("StudentDao not available - running in demo mode");
            System.out.println("Would save student: " + student);
        }
    }

    public Student findStudent(Long id) {
        if (studentDao != null) {
            return studentDao.findById(id);
        }
        return null;
    }

    public List<Student> findAllStudents() {
        if (studentDao != null) {
            return studentDao.findAll();
        }
        return List.of();
    }

    public void updateStudent(Student student) {
        if (studentDao != null) {
            studentDao.update(student);
        }
    }

    public void deleteStudent(Long id) {
        if (studentDao != null) {
            studentDao.delete(id);
        }
    }

    public void saveMoodEntry(MoodEntry entry) {
        if (moodEntryDao != null) {
            moodEntryDao.save(entry);
        } else {
            System.out.println("MoodEntryDao not available - running in demo mode");
            System.out.println("Would save mood entry: " + entry);
        }
    }

    public List<MoodEntry> findMoodEntries(Long studentId) {
        if (moodEntryDao != null) {
            return moodEntryDao.findAll();
        }
        return List.of();
    }

    public void logMood(String studentName, String moodType, int score) {
        System.out.println("=== Logging Mood for Student Wellness ===");
        System.out.println("Student: " + studentName);
        System.out.println("Mood: " + moodType + " (Score: " + score + ")");
        
        // Create a demo mood entry
        MoodEntry entry = new MoodEntry();
        entry.setMoodType(moodType);
        entry.setMoodScore(score);
        entry.setEntryDate(java.time.LocalDate.now().toString());
        
        saveMoodEntry(entry);
    }
}
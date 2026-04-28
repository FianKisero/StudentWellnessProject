package app.model;

import app.framework.DbColumn;
import app.framework.DbTable;
import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;
import app.framework.PageMenuItem;

/**
 * MoodEntry model for tracking student moods.
 * Demonstrates: Using @DbTable and @DbColumn annotations
 */
@DbTable(name = "mood_entries")
@Cohort12Form(label = "Log a Mood", actionUrl = "./register_mood")
@Cohort12Table(label = "Mood Logs", tableUrl = "./list_moods", registerUrl = "./register_mood", editUrl = "./edit_mood", deleteUrl = "./delete_mood")
@PageMenuItem(label = "Mood Logs", url = "list_moods")
public class MoodEntry {

    @DbColumn(name = "id", primaryKey = true, autoIncrement = true)
    private Long id;

    @DbColumn(name = "student_id", nullable = false)
    @Cohort12FormField(label = "Student ID", placeholder = "your student ID")
    @Cohort12TableCol(label = "Student ID")
    private Long studentId;

    @DbColumn(name = "mood_type", nullable = false)
    @Cohort12FormField(label = "Mood Type", placeholder = "e.g., Happy, Sad, Stressed")
    @Cohort12TableCol(label = "Mood")
    private String moodType;

    @DbColumn(name = "mood_score")
    @Cohort12FormField(label = "Mood Score (1-100)", placeholder = "e.g., 80")
    @Cohort12TableCol(label = "Score")
    private int moodScore;

    @DbColumn(name = "notes")
    @Cohort12FormField(label = "Notes", placeholder = "how was your day?", required = false)
    @Cohort12TableCol(label = "Notes")
    private String notes;

    @DbColumn(name = "entry_date")
    private String entryDate;

    public MoodEntry() {}

    public MoodEntry(Long studentId, String moodType, int moodScore) {
        this.studentId = studentId;
        this.moodType = moodType;
        this.moodScore = moodScore;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getMoodType() {
        return moodType;
    }

    public void setMoodType(String moodType) {
        this.moodType = moodType;
    }

    public int getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(int moodScore) {
        this.moodScore = moodScore;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return "MoodEntry{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", moodType='" + moodType + '\'' +
                ", moodScore=" + moodScore +
                ", notes='" + notes + '\'' +
                ", entryDate='" + entryDate + '\'' +
                '}';
    }
}
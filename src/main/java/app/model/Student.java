package app.model;

import app.framework.DbColumn;
import app.framework.DbTable;
import app.framework.Cohort12Form;
import app.framework.Cohort12FormField;
import app.framework.Cohort12Table;
import app.framework.Cohort12TableCol;
import app.framework.PageMenuItem;

/**
 * Student model representing a student in the wellness system.
 * Demonstrates: Using @DbTable and @DbColumn annotations
 */
@DbTable(name = "students")
@Cohort12Form(label = "Register Student", actionUrl = "./register_student")
@Cohort12Table(label = "Students", tableUrl = "./list_students", registerUrl = "./register_student", editUrl = "./edit_student", deleteUrl = "./delete_student")
@PageMenuItem(label = "Students", url = "list_students")
public class Student {

    @DbColumn(name = "id", primaryKey = true, autoIncrement = true)
    private Long id;

    @DbColumn(name = "name", nullable = false)
    @Cohort12FormField(label = "Full Name", placeholder = "your full name")
    @Cohort12TableCol(label = "Name")
    private String name;

    @DbColumn(name = "email", nullable = false)
    @Cohort12FormField(label = "Email Address", placeholder = "your email")
    @Cohort12TableCol(label = "Email")
    private String email;

    @DbColumn(name = "student_number")
    @Cohort12FormField(label = "Student Number", placeholder = "your student ID")
    @Cohort12TableCol(label = "Student #")
    private String studentNumber;

    @DbColumn(name = "mood_score")
    private int moodScore;

    @DbColumn(name = "wellness_tips_viewed")
    private int wellnessTipsViewed;

    public Student() {}

    public Student(String name, String email, String studentNumber) {
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
        this.moodScore = 50;
        this.wellnessTipsViewed = 0;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(int moodScore) {
        this.moodScore = moodScore;
    }

    public int getWellnessTipsViewed() {
        return wellnessTipsViewed;
    }

    public void setWellnessTipsViewed(int wellnessTipsViewed) {
        this.wellnessTipsViewed = wellnessTipsViewed;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", moodScore=" + moodScore +
                ", wellnessTipsViewed=" + wellnessTipsViewed +
                '}';
    }
}
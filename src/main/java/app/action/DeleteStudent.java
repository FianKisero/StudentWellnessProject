package app.action;

import app.model.Student;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/delete_student")
public class DeleteStudent extends BaseDeleteAction<Student> {
}

package app.action;

import app.model.Student;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/update_student")
public class UpdateStudent extends BaseUpdateAction<Student> {
}

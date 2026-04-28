package app.action;

import app.model.Student;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/edit_student")
public class EditStudent extends BaseEditAction<Student> {
}

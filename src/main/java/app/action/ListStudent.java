package app.action;

import app.model.Student;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/list_students")
public class ListStudent extends BaseListAction<Student> {
}

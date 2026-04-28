package app.action;

import app.model.MoodEntry;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/edit_mood")
public class EditMood extends BaseEditAction<MoodEntry> {
}

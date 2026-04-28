package app.action;

import app.model.MoodEntry;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/update_mood")
public class UpdateMood extends BaseUpdateAction<MoodEntry> {
}

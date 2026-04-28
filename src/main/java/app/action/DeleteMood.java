package app.action;

import app.model.MoodEntry;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/delete_mood")
public class DeleteMood extends BaseDeleteAction<MoodEntry> {
}

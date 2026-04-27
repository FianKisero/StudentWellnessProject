package app.action;

import app.model.MoodEntry;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/list_moods")
public class ListMood extends BaseListAction<MoodEntry> {
}

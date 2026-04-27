package app.action;

import app.model.MoodEntry;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/register_mood")
public class RegisterMood extends BaseAction<MoodEntry> {
}

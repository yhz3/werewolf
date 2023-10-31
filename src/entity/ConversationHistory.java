package entity;

import java.util.ArrayList;

public class ConversationHistory {
    private final ArrayList<String> history;

    public ConversationHistory() {
        this.history = new ArrayList<String>();
    }

    public void addGPTMessage(String message) {
        this.history.add("AI: " + message);
    }

    public void addUserMessage(String message) {
        this.history.add("User: " + message);
    }

    public String getConversationHistory() {
        StringBuilder output = new StringBuilder();
        for (String message : this.history) {
            output.append(message);
            output.append("\n\n");
        }
        return output.toString();
    }
}

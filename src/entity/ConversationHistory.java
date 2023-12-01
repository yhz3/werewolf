package entity;

import java.util.ArrayList;

public class ConversationHistory {
    private final ArrayList<String> history;

    public ConversationHistory() {
        this.history = new ArrayList<String>();
    }

    public void addGPTMessage(String message) {
        this.history.add("ChatGPT: " + message);
    }

    public void addUserMessage(String message) {
        this.history.add("User: " + message);
    }

    public String getConversationHistory() {
        StringBuilder output = new StringBuilder();
        for (String message : this.history) {
            output.append(message);
            output.append(" ");
        }
        return output.toString();
    }

    public String getConversationToCompress() {
        int historyLength = history.size();

        // If the history length is more than 3 add them to messageToCompress, and then remove them
        if (historyLength > 3) {
            StringBuilder messageToCompress = new StringBuilder();
            for (int i = 0; i < historyLength - 1; i++) {
                messageToCompress.append(history.get(i));
                messageToCompress.append(" ");
            }
            history.subList(0, 3).clear();
            return messageToCompress.toString();
        }
        return null;
    }

    public void addCompressedConversation(String compressedConversation) {
        history.add(0, compressedConversation);
    }
}

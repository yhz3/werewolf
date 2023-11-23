package entity;

import java.util.ArrayList;

/*
Idea for compression algorithm
- Use ChatGPT-3.5 for compression
1. Prompt: Summarize the following so that it can be used on ChatGPT for context.
2. Prompt: Summarize the following conversation so that it can be used on ChatGPT for context.

1. If message is more than 1 turn old, compress using ChatGPT-3.5
    - Thus, prompts to ChatGPT will only have the most recent reply that is uncompressed
    - getConversationHistory right before generating a prompt, so most recent message should be AI
    - Following prompt1, reply1 scheme, it should be that the last prompt1
      (and the invisible reply0 before) messages are compressed
    - Alternative is to compress last 2 after saving what we will return
2. If length of history is 6 or more, compress oldest 3 into 1. So 6 -> 4
    - Thus, it will be: prompt1, reply1, prompt2, reply2, prompt3, fresh_reply3
    - Right before sending prompt4, it becomes summary1, reply2, prompt3, fresh_reply3
    - And then fresh_reply3 is compressed to reply3 by
    - This should be performed before returning conversation history
 */

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

    public String getConversationToCompress() {
        int historyLength = history.size();

        // If the history length is more than 3, we will remove the messages until there remains only one message and
        // return the history
        if (historyLength > 3) {
            StringBuilder messageToCompress = new StringBuilder();
            for (int i = 0; i < historyLength - 1; i++) {
                messageToCompress.append(history.remove(0));
            }
            return messageToCompress.toString();
        }
        return null;
    }

    public void addCompressedConversation(String compressedConversation) {
        history.add(0, compressedConversation);
    }
}

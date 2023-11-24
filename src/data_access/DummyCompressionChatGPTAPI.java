package data_access;

import use_case.data_access_interface.ChatAPIAccessInterface;

public class DummyCompressionChatGPTAPI implements ChatAPIAccessInterface {

    @Override
    public String getResponse(String prompt) {
        String compressed = prompt;

        // Check if the prompt is long enough
        if (prompt.length() >= 100) {
            compressed = prompt.substring(0, 100);
        }
        return "Successfully compressed length of: " + compressed.length();
    }
}

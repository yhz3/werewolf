package data_access;

import use_case.data_access.ChatAPIAccessInterface;

public class DummyChatGPTAPI implements ChatAPIAccessInterface {
    @Override
    public String getResponse(String prompt) {
        return "Successfully received this prompt: " + prompt;
    }
}

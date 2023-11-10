package use_case.begin_intro;

import entity.Game;
import entity.PromptGenerator;

public class BeginIntroOutputData {
    private final String GPTMessage;

    public BeginIntroOutputData(String GPTMessage) {
        this.GPTMessage = GPTMessage;
    }

    public String getGPTMessage() {
        return GPTMessage;
    }
}

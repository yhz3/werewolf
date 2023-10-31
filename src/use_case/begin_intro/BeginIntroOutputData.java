package use_case.begin_intro;

import entity.Game;
import entity.PromptGenerator;

public class BeginIntroOutputData {
    private final String GPTMessage;
    private final Game game;
    private final PromptGenerator promptGenerator;

    public BeginIntroOutputData(String GPTMessage, Game game, PromptGenerator promptGenerator) {
        this.GPTMessage = GPTMessage;
        this.game = game;
        this.promptGenerator = promptGenerator;
    }

    public PromptGenerator getPromptGenerator() {
        return promptGenerator;
    }

    public Game getGame() {
        return game;
    }

    public String getGPTMessage() {
        return GPTMessage;
    }
}

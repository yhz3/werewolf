package use_case.begin_intro;

import entity.PromptGenerator;
import entity.Game;
import use_case.ChatAPIAccessInterface;

public class BeginIntroInteractor implements BeginIntroInputBoundary {
    private final ChatAPIAccessInterface ChatGPTAPI;
    private final BeginIntroOutputBoundary beginIntroPresenter;

    public BeginIntroInteractor(ChatAPIAccessInterface chatAPIAccessInterface,
                                BeginIntroOutputBoundary beginIntroOutputBoundary) {
        this.ChatGPTAPI = chatAPIAccessInterface;
        this.beginIntroPresenter = beginIntroOutputBoundary;
    }

    @Override
    public void execute(BeginIntroInputData beginIntroInputData) {
        Game game = beginIntroInputData.getGame();
        PromptGenerator promptGenerator = new PromptGenerator(game.getVillagerNames(), game.getWerewolfNames());
        String prompt = promptGenerator.generateIntroPrompt();
        String GPTMessage = ChatGPTAPI.getResponse(prompt);

        // Keep track of the ChatGPT response. User prompt is automatically stored when generating prompt.
        promptGenerator.conversationHistory.addGPTMessage(GPTMessage);

        BeginIntroOutputData response = new BeginIntroOutputData(GPTMessage, game, promptGenerator);
        beginIntroPresenter.prepareSuccessView(response);
    }
}

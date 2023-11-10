package use_case.begin_intro;

import data_access.GameDataAccessObject;
import entity.ConversationHistory;
import entity.PromptGenerator;
import entity.Game;
import use_case.ChatAPIAccessInterface;

public class BeginIntroInteractor implements BeginIntroInputBoundary {
    private final ChatAPIAccessInterface ChatGPTAPI;
    private final GameDataAccessObject GameData;
    private final BeginIntroOutputBoundary beginIntroPresenter;

    public BeginIntroInteractor(ChatAPIAccessInterface chatAPIAccessInterface,
                                GameDataAccessObject gameData, BeginIntroOutputBoundary beginIntroOutputBoundary) {
        this.ChatGPTAPI = chatAPIAccessInterface;
        this.GameData = gameData;
        this.beginIntroPresenter = beginIntroOutputBoundary;
    }

    @Override
    public void execute(BeginIntroInputData beginIntroInputData) {
        Game game = GameData.getGame();
        PromptGenerator promptGenerator = new PromptGenerator(game.getVillagerNames(), game.getWerewolfNames(), new ConversationHistory());
        String prompt = promptGenerator.generateIntroPrompt();
        String GPTMessage = ChatGPTAPI.getResponse(prompt);

        // Keep track of the ChatGPT response. User prompt is automatically stored when generating prompt.
        promptGenerator.conversationHistory.addGPTMessage(GPTMessage);

        // Save Game and PromptGenerator Data
        GameData.saveGame(game);
        GameData.savePromptGenerator(promptGenerator);

        BeginIntroOutputData response = new BeginIntroOutputData(GPTMessage, game, promptGenerator);
        beginIntroPresenter.prepareSuccessView(response);
    }
}

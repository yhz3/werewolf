package use_case.begin_intro;

import entity.ConversationHistory;
import entity.PromptGenerator;
import entity.Game;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;


public class BeginIntroInteractor implements BeginIntroInputBoundary {
    private final ConversationDataAccessInterface conversationDataAccessObject;
    private final GameDataAccessInterface gameDataAccessObject;
    private final ChatAPIAccessInterface gptDataAccessObject;
    private final BeginIntroOutputBoundary beginIntroPresenter;
    private final PromptGenerator promptGenerator;


    public BeginIntroInteractor(ConversationDataAccessInterface conversationDataAccessObject, GameDataAccessInterface gameDataAccessObject, ChatAPIAccessInterface gptDataAccessObject, BeginIntroOutputBoundary beginIntroOutputBoundary) {
        this.gptDataAccessObject = gptDataAccessObject;
        this.gameDataAccessObject = gameDataAccessObject;
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.beginIntroPresenter = beginIntroOutputBoundary;

        // Create a new promptGenerator Object with an empty conversation history
        this.promptGenerator = new PromptGenerator(new ConversationHistory());
    }

    @Override
    public void execute() {
        // Reconstruct the game from the database
        Game game = this.gameDataAccessObject.getGame();

        String prompt = this.promptGenerator.generateIntroPrompt(game.getPlayerNames());
        String introStory = this.gptDataAccessObject.getResponse(prompt);
        String[] villagers = game.getVillagerNames();

        // Keep track of the ChatGPT response. User prompt is automatically stored when generating prompt.
        this.promptGenerator.getConversationHistory().addGPTMessage(introStory);

        // Save Game and PromptGenerator Data
        this.gameDataAccessObject.save(game);
        this.conversationDataAccessObject.save(this.promptGenerator);

        BeginIntroOutputData beginIntroOutputData = new BeginIntroOutputData(introStory, villagers);
        this.beginIntroPresenter.prepareSuccessView(beginIntroOutputData);
    }
}

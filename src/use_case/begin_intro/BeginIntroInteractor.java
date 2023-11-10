package use_case.begin_intro;

import data_access.GameDataAccessObject;
import entity.ConversationHistory;
import entity.PromptGenerator;
import entity.Game;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;
import use_case.vote_out.VoteOutOutputBoundary;


public class BeginIntroInteractor implements BeginIntroInputBoundary {
    private final ConversationDataAccessInterface conversationDataAccessObject;
    private final GameDataAccessInterface gameDataAccessObject;
    private final ChatAPIAccessInterface gptDataAccessObject;
    private final BeginIntroOutputBoundary beginIntroPresenter;
    private final Game game;
    private final PromptGenerator promptGenerator;


    public BeginIntroInteractor(ConversationDataAccessInterface conversationDataAccessObject, GameDataAccessInterface gameDataAccessObject, ChatAPIAccessInterface gptDataAccessObject, BeginIntroOutputBoundary beginIntroOutputBoundary) {
        this.gptDataAccessObject = gptDataAccessObject;
        this.gameDataAccessObject = gameDataAccessObject;
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.beginIntroPresenter = beginIntroOutputBoundary;

        // Reconstruct the game from the database
        this.game = this.gameDataAccessObject.getGame();

        // Create a new promptGenerator Object with an empty conversation history
        this.promptGenerator = new PromptGenerator(new ConversationHistory());
    }

    @Override
    public void execute(BeginIntroInputData beginIntroInputData) {
        String prompt = this.promptGenerator.generateIntroPrompt(this.game.getVillagerNames(), this.game.getWerewolfNames());
        String GPTMessage = this.gptDataAccessObject.getResponse(prompt);

        // Keep track of the ChatGPT response. User prompt is automatically stored when generating prompt.
        this.promptGenerator.getConversationHistory().addGPTMessage(GPTMessage);

        // Save Game and PromptGenerator Data
        this.gameDataAccessObject.save(this.game);
        this.conversationDataAccessObject.save(this.promptGenerator);

        BeginIntroOutputData response = new BeginIntroOutputData(GPTMessage);
        this.beginIntroPresenter.prepareSuccessView(response);
    }
}

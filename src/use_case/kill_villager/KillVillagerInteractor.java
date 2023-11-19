package use_case.kill_villager;

import entity.Game;
import entity.PromptGenerator;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;

public class KillVillagerInteractor implements KillVillagerInputBoundary{

    private final ConversationDataAccessInterface conversationDataAccessObject;
    private final GameDataAccessInterface gameDataAccessObject;
    private final KillVillagerOutputBoundary killVillagerPresenter;
    private final PromptGenerator promptGenerator;
    private final ChatAPIAccessInterface gptDataAccessObject;
    // This is named storyDataAccessObject in VotePlayer use case, should probably choose one name or the other for
    // uniformity.

    public KillVillagerInteractor(ConversationDataAccessInterface conversationDataAccessObject,
                                  GameDataAccessInterface gameDataAccessObject,
                                  KillVillagerOutputBoundary killVillagerPresenter,
                                  ChatAPIAccessInterface gptDataAccessObject) {
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.gameDataAccessObject = gameDataAccessObject;
        this.killVillagerPresenter = killVillagerPresenter;
        // TODO: Figure out how to talk to the Output Boundary without violating CA, i.e. where the output boundary is
        //  being passed in from.
        // Notice how we are getting the prompt generator and game from the killVillagerDataAccessInterface,
        // adhering to the CA Engine.
        this.promptGenerator = conversationDataAccessObject.getPromptGenerator();
        this.gptDataAccessObject = gptDataAccessObject;
        // TODO: Figure out how to talk to ChatGPT without violating CA, since passing in gptDAO as a parameter means it
        //  is coming in as an input from the controller, which is not supposed to be able to talk to the DAO.
    }

    @Override
    public void killVillager(KillVillagerInputData killVillagerInputData) {
        Game game = gameDataAccessObject.getGame();
        // TODO: Edit method so both the game and promptGenerator are saved using their respective data access
        //  interfaces.
        // TODO: Edit
        // Getting the villager name
        String villager = killVillagerInputData.getVillager();

        if (game.getAliveVillagers().containsKey(villager)) {
            // Get the story
            String villagerDeathPrompt = promptGenerator.generatePlayerKilledPrompt(villager);
            // Once prompt is generated, the prompt is automatically saved by promptGenerator into ConversationHistory.
            String villagerDeathStory = gptDataAccessObject.getResponse(villagerDeathPrompt);
            promptGenerator.getConversationHistory().addGPTMessage(villagerDeathStory);
            // Saves the villagerDeathStory to the conversationHistory.
            // Kill villager
            game.killPlayer(villager);
            // Here I am assuming that the game entity will properly update the aliveVillagers hashmap and properly
            // update the deadVillagers hashmap.
            // Switch to day
            game.changeGameState();
            gameDataAccessObject.save(game);
            conversationDataAccessObject.save(promptGenerator);
            // Saves both the game and the conversation history.
            KillVillagerOutputData killVillagerOutputData = new KillVillagerOutputData(villagerDeathStory);
            killVillagerPresenter.prepareSuccessView(killVillagerOutputData);
        } else {
            killVillagerPresenter.prepareFailView("Person is not a live villager.");
        }
    }
}
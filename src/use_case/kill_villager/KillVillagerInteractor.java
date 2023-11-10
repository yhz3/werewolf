package use_case.kill_villager;

import entity.Game;
import entity.PromptGenerator;
import use_case.ChatAPIAccessInterface;

public class KillVillagerInteractor implements KillVillagerInputBoundary{

    private final KillVillagerDataAccessInterface killVillagerDataAccessInterface;
    private final KillVillagerOutputBoundary killVillagerPresenter;
    private final PromptGenerator promptGenerator;
    private final ChatAPIAccessInterface gptDataAccessObject;
    // This is named storyDataAccessObject in VotePlayer use case, should probably choose one name or the other for
    // uniformity.
    private final Game game;

    public KillVillagerInteractor(KillVillagerDataAccessInterface killVillagerDataAccessInterface,
                                  KillVillagerOutputBoundary killVillagerPresenter,
                                  ChatAPIAccessInterface gptDataAccessObject) {
        this.killVillagerDataAccessInterface = killVillagerDataAccessInterface;
        this.killVillagerPresenter = killVillagerPresenter;
        // TODO: Figure out how to talk to the Output Boundary without violating CA, i.e. where the output boundary is
        //  being passed in from.
        // Notice how we are getting the prompt generator and game from the killVillagerDataAccessInterface,
        // adhering to the CA Engine.
        this.promptGenerator = killVillagerDataAccessInterface.getPromptGenerator();
        this.game = killVillagerDataAccessInterface.getGame();
        this.gptDataAccessObject = gptDataAccessObject;
        // TODO: Figure out how to talk to ChatGPT without violating CA, since passing in gptDAO as a parameter means it
        //  is coming in as an input from the controller, which is not supposed to be able to talk to the DAO.
    }

    @Override
    public void killVillager(KillVillagerInputData killVillagerInputData) {
        // Getting the villager name
        String villager = killVillagerInputData.getVillager();

        if (game.getAliveVillagers().containsKey(villager)) {
            // Get the story
            String villagerDeathPrompt = promptGenerator.generatePlayerKilledPrompt(villager);
            String villagerDeathStory = gptDataAccessObject.getResponse(villagerDeathPrompt);
            // Kill villager
            game.killPlayer(villager);
            // Here I am assuming that the game entity will properly update the aliveVillagers hashmap and properly
            // update the deadVillagers hashmap.
            // Switch to day
            game.changeGameState();
            killVillagerDataAccessInterface.save(game);
            KillVillagerOutputData killVillagerOutputData = new KillVillagerOutputData(villagerDeathStory);
            killVillagerPresenter.prepareSuccessView(killVillagerOutputData);
            // If we follow Daniyaal's implementation, then we would also pass the name along as output data in case
            // we add extra features later on.
        } else {
            killVillagerPresenter.prepareFailView();
            // TODO: Daniyaal has a different approach for this in his code, bring this up in the meeting
        }
    }
}
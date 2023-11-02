package use_case.kill_villager;

import entity.Game;
import entity.PromptGenerator;

public class KillVillagerInteractor implements KillVillagerInputBoundary{

    final KillVillagerOutputBoundary killPresenter;
    final PromptGenerator gpt;
    // We need to have this as one of the attributes since the PromptGenerator used throughout the application must
    // contain the same conversation history, and whenever we use the PromptGenerator, we are adding to the conversation
    // history.
    final Game game;
    // Originally put it as a parameter for killVillager, but it seems to make more sense to put it here since the
    // KillVillagerOutputBoundary should only be handling one Game entity at a time.
    final String villager;
    // Yeah it should be here because the input data should handle everything.

    public KillVillagerInteractor(KillVillagerOutputBoundary killPresenter,
                                  KillVillagerInputData killVillagerInputData) {
        this.killPresenter = killPresenter;
        this.gpt = killVillagerInputData.getGpt();
        this.game = killVillagerInputData.getGame();
        this.villager = killVillagerInputData.getVillager();
    }

    @Override
    public void killVillager() {
        if (game.getAliveVillagers().containsKey(villager)) {
            game.killPlayer(villager);
            // Here I am assuming that the game entity will properly update the aliveVillagers hashmap and properly
            // update the deadVillagers hashmap
            game.changeGameState();
            String villagerDeathStory = gpt.generatePlayerKilledPrompt(villager);
            killPresenter.prepareSuccessView(villagerDeathStory);
        } else {
            killPresenter.prepareFailView(villager);
        }
    }
}
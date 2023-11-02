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

    public KillVillagerInteractor(KillVillagerOutputBoundary killPresenter, PromptGenerator gpt, Game game) {
        this.killPresenter = killPresenter;
        this.gpt = gpt;
        this.game = game;
    }

    @Override
    public void killVillager(String villager) {
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
//Note: Instead of employing an input boundary for the KillVillager use case, we decided to just pass a string
// instead since writing an entire class just for the sake of storing the name of the killed villager seemed
// unnecessary.
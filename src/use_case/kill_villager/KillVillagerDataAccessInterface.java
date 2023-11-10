package use_case.kill_villager;

import entity.Game;
import entity.PromptGenerator;

public interface KillVillagerDataAccessInterface {
    void save(Game game);
    Game getGame();

    PromptGenerator getPromptGenerator();
    // getPromptGenerator in DAO should be pulling the ConversationHistory to then construct a PromptGenerator off of
}

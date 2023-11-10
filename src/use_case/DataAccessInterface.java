package use_case;

import entity.Game;
import entity.PromptGenerator;

public interface DataAccessInterface {
    void save(Game game);
    Game getGame();

    PromptGenerator getPromptGenerator();
    // getPromptGenerator in DAO should be pulling the ConversationHistory to then construct a PromptGenerator off of
}
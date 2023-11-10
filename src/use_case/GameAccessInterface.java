package use_case;

import entity.Game;
import entity.PromptGenerator;

public interface GameAccessInterface {
    void saveGame(Game game);
    Game getGame();
}

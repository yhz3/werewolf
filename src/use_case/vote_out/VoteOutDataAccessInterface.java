package use_case.vote_out;

import entity.Game;
import entity.PromptGenerator;

public interface VoteOutDataAccessInterface {
    void save(Game game);
    Game getGame();

    PromptGenerator getPromptGenerator();
}

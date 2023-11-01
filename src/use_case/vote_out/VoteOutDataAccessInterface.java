package use_case.vote_out;

import entity.Game;

public interface VoteOutDataAccessInterface {
    void save(Game game);
}

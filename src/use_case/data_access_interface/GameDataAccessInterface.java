package use_case.data_access_interface;

import entity.Game;

public interface GameDataAccessInterface {
    void save(Game game);
    Game getGame();
}
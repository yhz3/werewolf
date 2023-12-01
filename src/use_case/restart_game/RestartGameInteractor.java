package use_case.restart_game;

import entity.Game;
import use_case.data_access_interface.GameDataAccessInterface;

public class RestartGameInteractor implements RestartGameInputBoundary {
    private Game game;
    private GameDataAccessInterface gameData;
    private final RestartGameOutputBoundary userPresenter;


    public RestartGameInteractor(GameDataAccessInterface gameData, RestartGameOutputBoundary userPresenter) {
        this.gameData = gameData;
        this.game = gameData.getGame();
        this.userPresenter = userPresenter;
    }

    public void execute() {
        game.clearAll();
        userPresenter.prepareNewGameView();
    }


}

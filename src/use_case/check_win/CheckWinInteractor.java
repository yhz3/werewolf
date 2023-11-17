package use_case.check_win;

import entity.Game;
import use_case.data_access_interface.GameDataAccessInterface;

public class CheckWinInteractor implements CheckWinInputBoundary{

    private Game game;

    private final CheckWinOutputBoundary userPresenter;

    public CheckWinInteractor(GameDataAccessInterface gameData, CheckWinOutputBoundary userPresenter){
        this.userPresenter = userPresenter;
        this.game = gameData.getGame();
    }

    public void execute(){
        if (game.checkGameOver()){
            if (game.checkVillagerWin()){
                userPresenter.prepareVillagerWinView();
            } else {
                userPresenter.prepareWerewolfWinView();
            }
        } else {
            userPresenter.prepareGameContinuesView();
        }
    }
}

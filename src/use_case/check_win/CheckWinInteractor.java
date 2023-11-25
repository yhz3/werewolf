package use_case.check_win;

import entity.Game;
import use_case.data_access_interface.GameDataAccessInterface;

public class CheckWinInteractor implements CheckWinInputBoundary{
    private final GameDataAccessInterface gameDataAccessObject;

    private final CheckWinOutputBoundary userPresenter;

    public CheckWinInteractor(GameDataAccessInterface gameDataAccessObject, CheckWinOutputBoundary userPresenter){
        this.gameDataAccessObject = gameDataAccessObject;
        this.userPresenter = userPresenter;
    }

    public void execute(){
        Game game = gameDataAccessObject.getGame();
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

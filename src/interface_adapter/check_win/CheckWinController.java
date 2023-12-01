package interface_adapter.check_win;

import use_case.check_win.CheckWinInputBoundary;
import use_case.restart_game.RestartGameInputBoundary;

public class CheckWinController {
    private final CheckWinInputBoundary checkWinInteractor;
    private final RestartGameInputBoundary restartGameInteractor;

    public CheckWinController(CheckWinInputBoundary checkWinInteractor, RestartGameInputBoundary restartGameInteractor){
        this.checkWinInteractor = checkWinInteractor;
        this.restartGameInteractor = restartGameInteractor;
    }

    public void execute(){
        checkWinInteractor.execute();
    }

    public void restartGame() { restartGameInteractor.execute(); }
}

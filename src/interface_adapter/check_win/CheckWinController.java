package interface_adapter.check_win;

import use_case.check_win.CheckWinInputBoundary;

public class CheckWinController {
    private final CheckWinInputBoundary checkWinInteractor;

    public CheckWinController(CheckWinInputBoundary checkWinInteractor){
        this.checkWinInteractor = checkWinInteractor;
    }

    public void execute(){
        checkWinInteractor.execute();
    }
}

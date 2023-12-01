package interface_adapter.vote_out;

import interface_adapter.check_win.CheckWinController;
import use_case.check_win.CheckWinInputBoundary;
import use_case.restart_game.RestartGameInteractor;
import use_case.vote_out.VoteOutInputBoundary;
import use_case.vote_out.VoteOutInputData;

public class VoteOutController extends CheckWinController {
    final VoteOutInputBoundary userVoteOutUseCaseInteractor;

    public VoteOutController(CheckWinInputBoundary checkWinInteractor, VoteOutInputBoundary userVoteOutUseCaseInteractor, RestartGameInteractor restartGameInteractor) {
        super(checkWinInteractor, restartGameInteractor);
        this.userVoteOutUseCaseInteractor = userVoteOutUseCaseInteractor;
    }

    public void voteOutPlayer(String votedName) {
        VoteOutInputData voteOutInputData = new VoteOutInputData(votedName);

        userVoteOutUseCaseInteractor.voteOutPlayer(voteOutInputData);
        super.execute();
    }
}

package interface_adapter.vote_out;

import interface_adapter.check_win.CheckWinController;
import use_case.check_win.CheckWinInputBoundary;
import use_case.check_win.CheckWinInteractor;
import use_case.vote_out.VoteOutInputBoundary;
import use_case.vote_out.VoteOutInputData;

public class VoteOutController extends CheckWinController {
    final VoteOutInputBoundary userVoteOutUseCaseInteractor;

    public VoteOutController(CheckWinInputBoundary checkWinInteractor, VoteOutInputBoundary userVoteOutUseCaseInteractor) {
        super(checkWinInteractor);
        this.userVoteOutUseCaseInteractor = userVoteOutUseCaseInteractor;
    }

    public void voteOutPlayer(String votedName) {
        VoteOutInputData voteOutInputData = new VoteOutInputData(votedName);

        userVoteOutUseCaseInteractor.voteOutPlayer(voteOutInputData);
        super.execute();
    }
}

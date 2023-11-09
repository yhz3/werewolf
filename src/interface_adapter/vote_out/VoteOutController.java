package interface_adapter.vote_out;

import use_case.vote_out.VoteOutInputBoundary;
import use_case.vote_out.VoteOutInputData;

public class VoteOutController {
    final VoteOutInputBoundary userVoteOutUseCaseInteractor;

    public VoteOutController(VoteOutInputBoundary userVoteOutUseCaseInteractor) {
        this.userVoteOutUseCaseInteractor = userVoteOutUseCaseInteractor;
    }

    public void voteOutPlayer(String votedName) {
        VoteOutInputData voteOutInputData = new VoteOutInputData(votedName);

        userVoteOutUseCaseInteractor.voteOutPlayer(voteOutInputData);
    }
}

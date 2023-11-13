package interface_adapter.vote_out;

import use_case.vote_out.VoteOutOutputBoundary;
import use_case.vote_out.VoteOutOutputData;

public class VoteOutPresenter implements VoteOutOutputBoundary {

    private final VoteOutViewModel voteOutViewModel;

    public VoteOutPresenter(VoteOutViewModel voteOutViewModel) {
        this.voteOutViewModel = voteOutViewModel;
    }

    @Override
    public void prepareSuccessView(VoteOutOutputData player) {
        String playerVotedOut = player.getPlayerVotedOut();
        String playerRole = player.getPlayerRole();
        String story = player.getStory();
        this.voteOutViewModel.setPlayerVotedOut(playerVotedOut);
        this.voteOutViewModel.setPlayerRole(playerRole);
        this.voteOutViewModel.setStory(player.getStory());
        // fire property changed
    }

    @Override
    public void prepareFailView(String error) {
        this.voteOutViewModel.setError(error);
        // fire property changed
    }

}

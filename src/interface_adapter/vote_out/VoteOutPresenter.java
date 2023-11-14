package interface_adapter.vote_out;

import interface_adapter.ViewManagerModel;
import use_case.vote_out.VoteOutOutputBoundary;
import use_case.vote_out.VoteOutOutputData;

public class VoteOutPresenter implements VoteOutOutputBoundary {

    private final VoteOutViewModel voteOutViewModel;
    private ViewManagerModel viewManagerModel;


    public VoteOutPresenter(VoteOutViewModel voteOutViewModel) {
        this.voteOutViewModel = voteOutViewModel;
    }

    @Override
    public void prepareSuccessView(VoteOutOutputData player) {
        String playerVotedOut = player.getPlayerVotedOut();
        String playerRole = player.getPlayerRole();
        String story = player.getStory();

        VoteOutState voteOutState = voteOutViewModel.getState();
        voteOutState.setPlayerVotedOut(playerVotedOut);
        voteOutState.setPlayerRole(playerRole);
        voteOutState.setStory(story);
        // fire property changed
        this.voteOutViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        VoteOutState voteOutState = voteOutViewModel.getState();
        voteOutState.setError(error);
        this.voteOutViewModel.firePropertyChanged();
    }

}

package interface_adapter.vote_out;

import interface_adapter.ViewManagerModel;
import use_case.vote_out.VoteOutOutputBoundary;
import use_case.vote_out.VoteOutOutputData;

public class VoteOutPresenter implements VoteOutOutputBoundary {

    private final VoteOutStoryViewModel voteOutStoryViewModel;
    private final VoteOutViewModel voteOutViewModel;
    private ViewManagerModel viewManagerModel;


    public VoteOutPresenter(VoteOutStoryViewModel voteOutStoryViewModel, VoteOutViewModel voteOutViewModel) {
        this.voteOutStoryViewModel = voteOutStoryViewModel;
        this.voteOutViewModel = voteOutViewModel;
    }

    @Override
    public void prepareSuccessView(VoteOutOutputData player) {
        // Get attributes from output data
        String playerVotedOut = player.getPlayerVotedOut();
        String playerRole = player.getPlayerRole();
        String story = player.getStory();
        // Get the current state from the view model
        VoteOutStoryState voteOutStoryState = voteOutStoryViewModel.getState();
        // Change state's attributes
        voteOutStoryState.setPlayerVotedOut(playerVotedOut);
        voteOutStoryState.setPlayerRole(playerRole);
        voteOutStoryState.setStory(story);
        // Set the state in the view model again to update it
        voteOutStoryViewModel.setState(voteOutStoryState);
        // Set a new view
        this.viewManagerModel.setActiveView(voteOutStoryViewModel.getViewName());
        // indicate that we have changed the view
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        VoteOutState voteOutState = voteOutViewModel.getState();
        voteOutState.setError(error);
        this.voteOutViewModel.firePropertyChanged();
    }

}

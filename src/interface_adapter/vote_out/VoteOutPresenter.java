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
        // Get attributes from output data
        String playerVotedOut = player.getPlayerVotedOut();
        String playerRole = player.getPlayerRole();
        String story = player.getStory();
        // Get the current state from the view model
        VoteOutState voteOutState = voteOutViewModel.getState();
        // Change state's attributes
        voteOutState.setPlayerVotedOut(playerVotedOut);
        voteOutState.setPlayerRole(playerRole);
        voteOutState.setStory(story);
        // Set the state in the view model again to update it
        voteOutViewModel.setState(voteOutState);
        // Set a new view
        /** Use the view manager model to switch to a new view?**/
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

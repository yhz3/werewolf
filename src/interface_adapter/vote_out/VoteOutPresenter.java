package interface_adapter.vote_out;

import interface_adapter.ViewManagerModel;
import interface_adapter.kill_villager.KillVillagerViewModel;
import use_case.vote_out.VoteOutOutputBoundary;
import use_case.vote_out.VoteOutOutputData;

public class VoteOutPresenter implements VoteOutOutputBoundary {

    private final VoteOutViewModel voteOutViewModel;
    private final KillVillagerViewModel killVillagerViewModel;
    private final ViewManagerModel viewManagerModel;


    public VoteOutPresenter(VoteOutViewModel voteOutViewModel, KillVillagerViewModel killVillagerViewModel, ViewManagerModel viewManagerModel) {
        this.killVillagerViewModel = killVillagerViewModel;
        this.voteOutViewModel = voteOutViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(VoteOutOutputData voteOutOutputData) {
        VoteOutState voteOutState = voteOutViewModel.getState();
        voteOutState.setVillagerDeathStory(voteOutOutputData.getStory());
        voteOutViewModel.setState(voteOutState);
        voteOutViewModel.firePropertyChanged();

        // On success, switch to KillVillagerView
        this.viewManagerModel.setActiveView(killVillagerViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        VoteOutState voteOutState = voteOutViewModel.getState();
        voteOutState.setError(error);
        this.voteOutViewModel.firePropertyChanged();
    }

}

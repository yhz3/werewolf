package interface_adapter.kill_villager;

import interface_adapter.ViewManagerModel;
import interface_adapter.vote_out.VoteOutState;
import interface_adapter.vote_out.VoteOutViewModel;
import use_case.kill_villager.KillVillagerOutputBoundary;
import use_case.kill_villager.KillVillagerOutputData;

public class KillVillagerPresenter implements KillVillagerOutputBoundary {

    private final KillVillagerViewModel killVillagerViewModel;
    private final VoteOutViewModel voteOutViewModel;
    private final ViewManagerModel viewManagerModel;

    public KillVillagerPresenter(KillVillagerViewModel killVillagerViewModel, VoteOutViewModel voteOutViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.killVillagerViewModel = killVillagerViewModel;
        this.voteOutViewModel = voteOutViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(KillVillagerOutputData villagerDeathStory) {
        // On success, switch to voteVillagerView.
        VoteOutState voteOutState =voteOutViewModel.getState();
        voteOutState.setVillagerDeathStory(villagerDeathStory.getVillagerDeathStory());
        voteOutViewModel.setState(voteOutState);
        voteOutViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(voteOutViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        KillVillagerState killVillagerState = killVillagerViewModel.getState();
        killVillagerState.setError(error);
        killVillagerViewModel.firePropertyChanged();
    }
}

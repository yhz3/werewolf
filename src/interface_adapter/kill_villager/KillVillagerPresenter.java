package interface_adapter.kill_villager;

import interface_adapter.ViewManagerModel;
import interface_adapter.vote_out.VoteOutState;
import interface_adapter.vote_out.VoteOutViewModel;
import use_case.kill_villager.KillVillagerOutputBoundary;
import use_case.kill_villager.KillVillagerOutputData;

public class KillVillagerPresenter implements KillVillagerOutputBoundary {

    public final String TITLE_LABEL = "Kill Villager View";

    public final String PLAYER_LABEL = "Enter villager";

    public final String CONFIRM_LABEL = "Confirm";

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
        KillVillagerState killVillagerState = killVillagerViewModel.getState();
        killVillagerState.setPlayerDeathStory(villagerDeathStory.getVillagerDeathStory());
        killVillagerViewModel.setState(killVillagerState);
        killVillagerViewModel.firePropertyChanged();
        // On success, switch to voteVillagerView.

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

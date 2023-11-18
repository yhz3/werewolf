package interface_adapter.begin_intro;

import interface_adapter.ViewManagerModel;
import interface_adapter.kill_villager.KillVillagerViewModel;
import use_case.begin_intro.BeginIntroOutputBoundary;
import use_case.begin_intro.BeginIntroOutputData;

public class BeginIntroPresenter implements BeginIntroOutputBoundary {
    private final BeginIntroViewModel beginIntroViewModel;
    private final ViewManagerModel viewManagerModel;
    private final KillVillagerViewModel killVillagerViewModel;

    public BeginIntroPresenter(BeginIntroViewModel beginIntroViewModel, ViewManagerModel viewManagerModel, KillVillagerViewModel killVillagerViewModel) {
        this.beginIntroViewModel = beginIntroViewModel;
        this.viewManagerModel = viewManagerModel;
        this.killVillagerViewModel = killVillagerViewModel;
    }

    @Override
    public void prepareSuccessView(BeginIntroOutputData beginIntroOutputData) {
        beginIntroViewModel.setIntroStory(beginIntroOutputData.getIntroStory());
        beginIntroViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(killVillagerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

package interface_adapter.check_win;

import interface_adapter.ViewManagerModel;
import use_case.check_win.CheckWinOutputBoundary;

public class CheckWinPresenter implements CheckWinOutputBoundary {

    private final VillagerWinViewModel villagerWinViewModel;

    private final WerewolfWinViewModel werewolfWinViewModel;

    private ViewManagerModel viewManagerModel;

    public CheckWinPresenter(VillagerWinViewModel villagerWinViewModel, WerewolfWinViewModel werewolfWinViewModel, ViewManagerModel viewManagerModel){
        this.villagerWinViewModel = villagerWinViewModel;
        this.werewolfWinViewModel = werewolfWinViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareGameContinuesView(){}

    public void prepareVillagerWinView(){
        villagerWinViewModel.firePropertyChanged();
    }

    public void prepareWerewolfWinView(){
        werewolfWinViewModel.firePropertyChanged();
    }
}

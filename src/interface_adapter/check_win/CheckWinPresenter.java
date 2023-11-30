package interface_adapter.check_win;

import data_access.GameDataAccessObject;
import entity.Game;
import interface_adapter.ViewManagerModel;
import interface_adapter.kill_villager.KillVillagerViewModel;
import interface_adapter.vote_out.VoteOutState;
import interface_adapter.vote_out.VoteOutViewModel;
import use_case.check_win.CheckWinOutputBoundary;
import use_case.check_win.VillagerWinOutputData;
import use_case.check_win.WerewolfWinOutputData;
import use_case.data_access_interface.GameDataAccessInterface;

public class CheckWinPresenter implements CheckWinOutputBoundary {

    private final VillagerWinViewModel villagerWinViewModel;

    private final WerewolfWinViewModel werewolfWinViewModel;

    private final ViewManagerModel viewManagerModel;

    private final GameDataAccessInterface gameDataAccessObject;

    private final KillVillagerViewModel killVillagerViewModel;

    private final VoteOutViewModel voteOutViewModel;

    public CheckWinPresenter(VillagerWinViewModel villagerWinViewModel, WerewolfWinViewModel werewolfWinViewModel, ViewManagerModel viewManagerModel, GameDataAccessInterface gameDataAccessObject, VoteOutViewModel voteOutViewModel, KillVillagerViewModel killVillagerViewModel){
        this.villagerWinViewModel = villagerWinViewModel;
        this.werewolfWinViewModel = werewolfWinViewModel;
        this.viewManagerModel = viewManagerModel;
        this.gameDataAccessObject = gameDataAccessObject;
        this.killVillagerViewModel = killVillagerViewModel;
        this.voteOutViewModel = voteOutViewModel;
    }

    public void prepareGameContinuesView(){
        Game game = gameDataAccessObject.getGame();
        if (game.isDay()){
            // this.viewManagerModel.setActiveView(killVillagerViewModel.getViewName());
            this.viewManagerModel.setActiveView(voteOutViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else {
            // this.viewManagerModel.setActiveView(voteOutViewModel.getViewName());
            this.viewManagerModel.setActiveView(killVillagerViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }

    public void prepareVillagerWinView(VillagerWinOutputData villagerWinStory){
        VillagerWinState villagerWinState = villagerWinViewModel.getState();
        villagerWinState.setVillagerWinStory(villagerWinStory.getVillagerWinStory());
        villagerWinViewModel.setState(villagerWinState);
        villagerWinViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(villagerWinViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareWerewolfWinView(WerewolfWinOutputData werewolfWinStory){
        WerewolfWinState werewolfWinState = werewolfWinViewModel.getState();
        werewolfWinState.setWerewolfWinStory(werewolfWinStory.getWerewolfWinStory());
        werewolfWinViewModel.setState(werewolfWinState);
        werewolfWinViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(werewolfWinViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

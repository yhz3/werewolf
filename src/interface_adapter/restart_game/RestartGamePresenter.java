package interface_adapter.restart_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_game.NewGameViewModel;
import use_case.restart_game.RestartGameOutputBoundary;

public class RestartGamePresenter implements RestartGameOutputBoundary {

    private final NewGameViewModel newGameViewModel;

    private final ViewManagerModel viewManagerModel;

    public RestartGamePresenter(NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel) {
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareNewGameView() {
        viewManagerModel.setActiveView(newGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

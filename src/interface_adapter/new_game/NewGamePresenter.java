package interface_adapter.new_game;

import interface_adapter.ViewManagerModel;
import use_case.new_game.NewGameOutputBoundary;
import use_case.new_game.NewGameOutputData;

public class NewGamePresenter implements NewGameOutputBoundary {

    private final NewGameViewModel newGameViewModel;

    private final ViewManagerModel viewManagerModel;

    public NewGamePresenter(NewGameViewModel newGameViewModel, ViewManagerModel viewManagerModel){
        this.newGameViewModel = newGameViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareFailView(){}
    public void prepareSuccessView(){
        newGameViewModel.firePropertyChanged();
    }
}

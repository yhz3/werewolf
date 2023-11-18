package interface_adapter.new_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.begin_intro.BeginIntroViewModel;
import use_case.new_game.NewGameOutputBoundary;

public class NewGamePresenter implements NewGameOutputBoundary {

    private final NewGameViewModel newGameViewModel;
    private final BeginIntroViewModel beginIntroViewModel;

    private final ViewManagerModel viewManagerModel;

    public NewGamePresenter(NewGameViewModel newGameViewModel, BeginIntroViewModel beginIntroViewModel, ViewManagerModel viewManagerModel){
        this.newGameViewModel = newGameViewModel;
        this.beginIntroViewModel = beginIntroViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareFailView(){}
    public void prepareSuccessView(){
        viewManagerModel.setActiveView(beginIntroViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

package interface_adapter.new_game;

import use_case.new_game.NewGameOutputBoundary;
import use_case.new_game.NewGameOutputData;

public class NewGamePresenter implements NewGameOutputBoundary {

    public void prepareFailView(){}
    public void prepareSuccessView(NewGameOutputData newGameOutputData){}
}

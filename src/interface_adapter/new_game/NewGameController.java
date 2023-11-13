package interface_adapter.new_game;

import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInputData;

import java.util.ArrayList;

public class NewGameController {

    private final NewGameInputBoundary newGameInteractor;

    public NewGameController(NewGameInputBoundary newGameInteractor){
        this.newGameInteractor = newGameInteractor;
    }

    public void execute(ArrayList<String> PlayerNames){
        NewGameInputData newGameInputData = new NewGameInputData(PlayerNames);
        newGameInteractor.execute(newGameInputData);
    }
}

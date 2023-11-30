package interface_adapter.new_game;

import use_case.new_game.NewGameInputBoundary;
import use_case.new_game.NewGameInputData;

import java.util.ArrayList;

public class NewGameController {

    private final NewGameInputBoundary newGameInteractor;

    public NewGameController(NewGameInputBoundary newGameInteractor){
        this.newGameInteractor = newGameInteractor;
    }

    public void execute(ArrayList<String> playerNames){
        NewGameInputData newGameInputData = new NewGameInputData(playerNames);
        newGameInteractor.execute(newGameInputData);
    }
}

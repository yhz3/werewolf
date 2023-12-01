package use_case.new_game;
import java.util.ArrayList;

import entity.*;

import java.util.*;

import use_case.data_access_interface.GameDataAccessInterface;
import use_case.vote_out.VoteOutOutputData;

public class NewGameInteractor implements NewGameInputBoundary{

    private Game game;
    private GameDataAccessInterface gameData;
    private final NewGameOutputBoundary userPresenter;

    public NewGameInteractor(GameDataAccessInterface gameData, NewGameOutputBoundary userPresenter){
        this.game = new Game();
        this.gameData = gameData;
        this.userPresenter = userPresenter;
    }

    public void execute(NewGameInputData newGameInputData){
        Random random = new Random();
        ArrayList<String> playerNames = newGameInputData.getUserNames();
        Set<String> checkDuplicates = new HashSet<String>(playerNames);
        if (playerNames.size() < 4 || checkDuplicates.size() != playerNames.size()){
            userPresenter.prepareFailView("You must have at least four players, no duplicate names, and no blank names.");
        }
        else {
            // Make a player factory
            PlayerFactory playerFactory = new PlayerFactory();
            int numWerewolves = playerNames.size() / 3;

//            playerFactory.assignRoles(playerNames, game);             Old player-role assignment call

            boolean correctNumWerewolves = false;
            while(!correctNumWerewolves) {
                // Create players (assigns role in player factory)
                for (String playerName : playerNames) {
                    Player player = playerFactory.createPlayer(playerName);
                    game.addPlayer(player);
                }
                // Check that we got the correct number of werewolves
                if (game.getAliveWerewolves().size() == numWerewolves) {
                    correctNumWerewolves = true;
                }
                // Clear the game attribute otherwise and regenerate player objects
                else {
                    game.clearAll();
                }
            }

            gameData.save(game);
            NewGameOutputData newGameOutputData = new NewGameOutputData(game);
            userPresenter.prepareSuccessView(newGameOutputData);
        }
    }
}

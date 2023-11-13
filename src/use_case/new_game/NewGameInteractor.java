package use_case.new_game;
import java.util.ArrayList;
import entity.Game;
import java.util.*;
import entity.Werewolf;
import entity.Villager;
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
        ArrayList<String> PlayerNames = newGameInputData.getUserNames();
        int numWerewolves = PlayerNames.size() / 3;
        for (int i = 0; i < numWerewolves; i++){
            String name = PlayerNames.get(random.nextInt(PlayerNames.size()));
            Werewolf werewolf = new Werewolf(name);
            game.addPlayer(werewolf);
            PlayerNames.remove(name);
        }
        for (int i = 0; i < PlayerNames.size(); i++){
            String name = PlayerNames.get(i);
            Villager villager = new Villager(name);
            game.addPlayer(villager);
            PlayerNames.remove(name);
        }
        gameData.save(game);
        NewGameOutputData newGameOutputData = new NewGameOutputData(game);
        userPresenter.prepareSuccessView(newGameOutputData);
    }
}

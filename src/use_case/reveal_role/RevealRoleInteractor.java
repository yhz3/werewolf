package use_case.reveal_role;

import entity.Game;
import entity.Player;
import use_case.data_access_interface.GameDataAccessInterface;

import java.util.HashMap;

public class RevealRoleInteractor implements RevealRoleInputBoundary{

    private Game game;

    private HashMap villagers;

    private HashMap werewolves;

    private final RevealRoleOutputBoundary userPresenter;

    public RevealRoleInteractor(GameDataAccessInterface gameData, RevealRoleOutputBoundary userPresenter){
        this.game = gameData.getGame();
        this.villagers = game.getAliveVillagers();
        this.werewolves = game.getAliveWerewolves();
        this.userPresenter = userPresenter;
    }

    public void execute(RevealRoleInputData userName){
        if (villagers.containsKey(userName)){
            Player villager = (Player) villagers.get(userName);
            RevealRoleOutputData outputData = new RevealRoleOutputData(villager.getRole());
            userPresenter.prepareDisplayRoleView(outputData);
        } else if (werewolves.containsKey(userName)){
            Player werewolf = (Player) werewolves.get(userName);
            RevealRoleOutputData outputData = new RevealRoleOutputData(werewolf.getRole());
            userPresenter.prepareDisplayRoleView(outputData);
        } else {
            userPresenter.prepareFailView();
        }
    }
}

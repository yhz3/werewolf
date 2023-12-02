package entity;

import java.util.ArrayList;
import java.util.Random;

public class PlayerFactory {

    final double WEREWOLF_PROBABILITY = 0.33;

    // Math.random() returns a double from 0.0 to 1.0 (inclusive)
    // 1/3 chance of getting werewolf, 2/3 chance of getting villager
    public Player createPlayer(String playerName) {
        double rand = Math.random();
        if (rand <= WEREWOLF_PROBABILITY) {
            return new Werewolf(playerName);
        }
        // Yes this else if would always be true but I'll leave it in case we add
        // more complex probability checking or more roles
        else if (rand > WEREWOLF_PROBABILITY){
            return new Villager(playerName);
        }
        return null;
    }

    // This method includes the old code that we used to assign roles
    // If we continued to use this code, all we'd have to do in NewGameInteractor is
    // write the expression 'playerFactory.assignRoles(playerNames, game)
    // However, this method isn't indicative of how a factory should actually work
    // Rather, the goal is to have a method here that creates a villager or a werewolf and returns it
    // The method createPlayer above achieves our goal
    public void assignRoles(ArrayList<String> playerNames, Game game) {
        Random random = new Random();
        int numWerewolves = playerNames.size() / 3;
        for (int i = 0; i < numWerewolves; i++){
            String name = playerNames.get(random.nextInt(playerNames.size()));
            Werewolf werewolf = new Werewolf(name);
            game.addPlayer(werewolf);
            playerNames.remove(name);
        }
        int numVillagers = playerNames.size();
        for (int i = 0; i < numVillagers; i++){
            String name = playerNames.get(0);
            Villager villager = new Villager(name);
            game.addPlayer(villager);
            playerNames.remove(name);
        }
    }
}

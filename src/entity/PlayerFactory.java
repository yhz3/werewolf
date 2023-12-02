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

}

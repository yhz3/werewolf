package use_case.new_game;
import entity.Game;

import java.util.HashMap;

public class NewGameOutputData {

    private final HashMap werewolves;
    private final HashMap villagers;

    public NewGameOutputData(Game newGame){
        this.werewolves = newGame.getAliveWerewolves();
        this.villagers = newGame.getAliveVillagers();
    }

    public HashMap getWerewolves(){
        return werewolves;
    }

    public HashMap getVillagers() {
        return villagers;
    }
}

package use_case.new_game;

import entity.Game;
import entity.Player;

import java.util.HashMap;

public class NewGameOutputData {

    private final String[] werewolves;
    private final String[] villagers;

    public NewGameOutputData(Game newGame){
        this.werewolves = newGame.getWerewolfNames();
        this.villagers = newGame.getVillagerNames();
    }

    public String[] getWerewolves(){
        return werewolves;
    }

    public String[] getVillagers() {
        return villagers;
    }
}
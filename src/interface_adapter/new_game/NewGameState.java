package interface_adapter.new_game;

import entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class NewGameState {
    private final String[] players;
    private String[] werewolves;
    private String[] villagers;


    public NewGameState(int numberOfPlayers) {
        players = new String[numberOfPlayers];
    }

    public void setPlayer(int number, String player) {
        if (number >= 0 && number <= players.length) {
            players[number] = player;
        } else {
            throw new IllegalArgumentException("Invalid player number");
        }
    }
    public String getPlayer(int number) {
        if (number >= 0 && number <= players.length) {
            return players[number];
        } else {
            throw new IllegalArgumentException("Invalid player number");
        }
    }

    public void setVillagers(String[] villagers) {
        this.villagers = villagers;
    }

    public String[] getVillagers() {
        return villagers;
    }

    public void setWerewolves(String[] werewolves) {
        this.werewolves = werewolves;
    }

    public String[] getWerewolves() {
        return werewolves;
    }
}

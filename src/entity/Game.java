package entity;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import entity.Player;


public class Game {

    private final HashMap<String, Player> aliveVillagers;
    private final HashMap<String, Player> deadVillagers;
    private final HashMap<String, Player> aliveWerewolves;
    private final HashMap<String, Player> deadWerewolves;
    private boolean day;

    public Game() {
        this.aliveVillagers = new HashMap<String, Player>();
        this.deadVillagers = new HashMap<String, Player>();
        this.aliveWerewolves = new HashMap<String, Player>();
        this.deadWerewolves = new HashMap<String, Player>();
        this.day = false;
    }

    public HashMap<String, Player> getAliveVillagers() {
        return aliveVillagers;
    }

    public HashMap<String, Player> getDeadVillagers() {
        return deadVillagers;
    }

    public HashMap<String, Player> getAliveWerewolves() {
        return aliveWerewolves;
    }

    public HashMap<String, Player> getDeadWerewolves() {
        return deadWerewolves;
    }

    public void addPlayer(Player player) {
        String name = player.getName();
        String role = player.getRole();
        if (role.equals("werewolf")) {
            aliveWerewolves.put(name, player);
        } else {
            aliveVillagers.put(name, player);
        }
    }

    public boolean isDay() {
        return day;
    }

    public void changeGameState() {
        day = !day;
    }

    public void killPlayer(String name) {
        if (aliveVillagers.containsKey(name)) {
            Player Villager = aliveVillagers.get(name);
            Villager.setAlive(false);
            aliveVillagers.remove(name);
            deadVillagers.put(name, Villager);
        } else {
            Player Werewolf = aliveWerewolves.get(name);
            Werewolf.setAlive(false);
            aliveWerewolves.remove(name);
            deadWerewolves.put(name, Werewolf);
        }
    }

    public boolean checkGameOver() {
        return aliveVillagers.isEmpty() || aliveWerewolves.isEmpty();
    }

    public boolean checkVillagerWin() {
        return aliveWerewolves.isEmpty();
    }

    public String[] getVillagerNames() {
        return this.aliveVillagers.keySet().toArray(new String[0]);
    }

    public String[] getWerewolfNames() {
        return this.aliveWerewolves.keySet().toArray(new String[0]);
    }

    public String[] getPlayerNames() {
        // Get list of names as ArrayLists
        ArrayList<String> villagerNames = new ArrayList<>(Arrays.asList(this.getVillagerNames()));
        ArrayList<String> werewolfNames = new ArrayList<>(Arrays.asList(this.getWerewolfNames()));

        // Add the werewolfNames to villagerNames and sort
        villagerNames.addAll(werewolfNames);
        Collections.sort(villagerNames);

        // Return as a String[]
        return villagerNames.toArray(new String[0]);
    }
}
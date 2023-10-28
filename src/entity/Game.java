package entity;
import java.util.HashMap;
import entity.Player;


public class Game {

    private HashMap<String, Player> aliveVillagers;
    private HashMap<String, Player> deadVillagers;
    private HashMap<String, Player> aliveWerewolves;
    private HashMap<String, Player> deadWerewolves;
    private boolean day;
    private boolean gameOver;

    public Game(){
        this.aliveVillagers = new HashMap<String, Player>();
        this.deadVillagers = new HashMap<String, Player>();
        this.aliveWerewolves = new HashMap<String, Player>();
        this.deadWerewolves = new HashMap<String, Player>();
        this.day = false;
        this.gameOver = false;
    }

    public HashMap<String, Player> getAliveVillagers(){
        return aliveVillagers;
    }

    public HashMap<String, Player> getDeadVillagers(){
        return deadVillagers;
    }

    public HashMap<String, Player> getAliveWerewolves(){
        return aliveWerewolves;
    }

    public HashMap<String, Player> getDeadWerewolves(){
        return deadWerewolves;
    }

    public void addPlayer(Player player){
        String name = player.getName();
        String role = player.getRole();
        if (role.equals("werewolf")){
            aliveWerewolves.put(name, player);
        } else {
            aliveVillagers.put(name, player);
        }
    }

    public boolean isDay(){
        return day;
    }

    public boolean getGameOver(){
        return gameOver;
    }

    public void changeGameState(){
        day = !day;
    }

    public void killPlayer(String name){
        if (aliveVillagers.containsKey(name)){
            Player Villager = aliveVillagers.get(name);
            aliveVillagers.remove(name);
            deadVillagers.put(name, Villager);
        } else {
            Player Werewolf = aliveWerewolves.get(name);
            aliveWerewolves.remove(name);
            deadWerewolves.put(name, Werewolf);
        }
    }

    public void checkGameOver(){
        if (aliveVillagers.isEmpty() || aliveWerewolves.isEmpty()){
            gameOver = true;
            endGame();
        }
    }

    public void endGame(){}


}

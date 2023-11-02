package use_case.kill_villager;

import entity.Game;
import entity.PromptGenerator;

public class KillVillagerInputData {
    final private Game game;
    final private String villager;
    final private PromptGenerator gpt;

    public KillVillagerInputData(Game game, String villager, PromptGenerator gpt) {
        this.game = game;
        this.villager = villager;
        this.gpt = gpt;
    }

    public Game getGame() {
        return game;
    }

    public String getVillager() {
        return villager;
    }

    public PromptGenerator getGpt() {
        return gpt;
    }
}


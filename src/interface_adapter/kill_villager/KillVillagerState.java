package interface_adapter.kill_villager;

public class KillVillagerState {

    private String villager = "";
    private String error = null;

    private String playerDeathStory = "";
    // This will be set by the voteOut use case

    public KillVillagerState() {}

    public void setVillager(String villager) {
        this.villager = villager;
    }

    public String getVillager() {
        return villager;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setPlayerDeathStory(String playerDeathStory) {
        this.playerDeathStory = playerDeathStory;
    }

    public String getPlayerDeathStory() {
        return playerDeathStory;
    }
}

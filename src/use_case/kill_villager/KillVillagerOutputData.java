package use_case.kill_villager;

public class KillVillagerOutputData {
    private final String villagerDeathStory;

    public KillVillagerOutputData(String villagerDeathStory) {
        this.villagerDeathStory = villagerDeathStory;
    }

    public String getVillagerDeathStory() {
        return villagerDeathStory;
    }
}

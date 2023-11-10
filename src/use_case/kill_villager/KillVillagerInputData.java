package use_case.kill_villager;

public class KillVillagerInputData {
    private final String villager;

    public KillVillagerInputData(String villager) {
        this.villager = villager;
    }

    public String getVillager() {
        return villager;
    }
}


package use_case.kill_villager;

public class KillVillagerInputData {
    final private String villager;

    public KillVillagerInputData(String villager) {
        this.villager = villager;
    }

    public String getVillager() {
        return villager;
    }
}


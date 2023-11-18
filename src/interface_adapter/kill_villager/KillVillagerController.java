package interface_adapter.kill_villager;

import use_case.kill_villager.KillVillagerInputBoundary;
import use_case.kill_villager.KillVillagerInputData;

public class KillVillagerController {
    private final KillVillagerInputBoundary killVillagerInteractor;

    public KillVillagerController(KillVillagerInputBoundary killVillagerInteractor) {
        this.killVillagerInteractor = killVillagerInteractor;
    }

    public void killVillager(String villager) {
        KillVillagerInputData killVillagerInputData = new KillVillagerInputData(villager);
        killVillagerInteractor.killVillager(killVillagerInputData);
    }

}

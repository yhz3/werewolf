package interface_adapter.kill_villager;

import interface_adapter.check_win.CheckWinController;
import use_case.check_win.CheckWinInputBoundary;
import use_case.kill_villager.KillVillagerInputBoundary;
import use_case.kill_villager.KillVillagerInputData;
import use_case.restart_game.RestartGameInputBoundary;

public class KillVillagerController extends CheckWinController {
    private final KillVillagerInputBoundary killVillagerInteractor;

    public KillVillagerController(CheckWinInputBoundary checkWinInteractor, KillVillagerInputBoundary killVillagerInteractor, RestartGameInputBoundary restartGameInteractor) {
        super(checkWinInteractor, restartGameInteractor);
        this.killVillagerInteractor = killVillagerInteractor;
    }

    public void killVillager(String villager) {
        KillVillagerInputData killVillagerInputData = new KillVillagerInputData(villager);
        killVillagerInteractor.killVillager(killVillagerInputData);
        super.execute();
    }

}

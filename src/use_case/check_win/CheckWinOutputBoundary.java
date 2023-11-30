package use_case.check_win;

public interface CheckWinOutputBoundary {
    public void prepareGameContinuesView();

    public void prepareVillagerWinView(VillagerWinOutputData villagerWinOutputData);

    public void prepareWerewolfWinView(WerewolfWinOutputData werewolfWinOutputData);
}

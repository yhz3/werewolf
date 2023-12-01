package use_case.check_win;

public interface CheckWinOutputBoundary {
    public void prepareGameContinuesView(CheckWinOutputData checkWinOutputData);

    public void prepareVillagerWinView(VillagerWinOutputData villagerWinOutputData);

    public void prepareWerewolfWinView(WerewolfWinOutputData werewolfWinOutputData);
}

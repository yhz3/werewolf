package interface_adapter.check_win;

public class VillagerWinState {

    private String villagerWinStory = "";
    // #TODO change use case CheckWin so that it sets VillagerWinStory and WerewwolfWinStory

    public VillagerWinState(){}

    public void setVillagerWinStory(String villagerWinStory){
        this.villagerWinStory = villagerWinStory;
    }

    public String getVillagerWinStory(){
        return villagerWinStory;
    }
}

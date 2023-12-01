package interface_adapter.check_win;

public class WerewolfWinState {

    private String werewolfWinStory="";
    // #TODO change use case CheckWin so that it sets VillagerWinStory and WerewwolfWinStory

    public WerewolfWinState(){}

    public void setWerewolfWinStory(String werewolfWinStory){
        this.werewolfWinStory = werewolfWinStory;
    }

    public String getWerewolfWinStory(){
        return werewolfWinStory;
    }
}

package interface_adapter.vote_out;

public class VoteOutState {
    private String playerToVoteOut;
    private String error;
    private String deathStory;

    public String getPlayerToVoteOut() {
        return playerToVoteOut;
    }

    public void setPlayerToVoteOut(String playerToVoteOut) {
        this.playerToVoteOut = playerToVoteOut;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setVillagerDeathStory(String villagerDeathStory) {
        this.deathStory = villagerDeathStory;
    }

    public String getPlayerDeathStory(){
        return deathStory;
    }
}

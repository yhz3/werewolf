package interface_adapter.vote_out;

public class VoteOutState {
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

    private String playerToVoteOut;
    private String error;

    private String deathStory;

    public void setVillagerDeathStory(String villagerDeathStory) {
        this.deathStory = villagerDeathStory;
    }

    public String getPlayerDeathStory(){
        return deathStory;
    }
}

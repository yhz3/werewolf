package interface_adapter.vote_out;

public class VoteOutStoryState {
    private String playerVotedOut;
    private String playerRole;
    private String story;

    public void setPlayerVotedOut(String playerVotedOut) {
        this.playerVotedOut = playerVotedOut;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getPlayerVotedOut() {
        return playerVotedOut;
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public String getStory() {
        return story;
    }
    
}

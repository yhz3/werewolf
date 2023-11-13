package interface_adapter.vote_out;

public class VoteOutViewModel {
    private String playerVotedOut;
    private String playerRole;
    private String story;
    private String error;

    public void setPlayerVotedOut(String playerVotedOut) {
        this.playerVotedOut = playerVotedOut;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setError(String error) {
        this.error = error;
    }
}

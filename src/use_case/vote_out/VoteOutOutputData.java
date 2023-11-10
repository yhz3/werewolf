package use_case.vote_out;

public class VoteOutOutputData {
    // playerVotedOut and playerRole are here in case we use them in future views
    // Can take them out later if we don't end up using them
    private String playerVotedOut;
    private String playerRole;
    private String story;

    public VoteOutOutputData(String playerVotedOut, String playerRole, String story) {
        this.playerVotedOut = playerVotedOut;
        this.playerRole = playerRole;
        this.story = story;
    }

    public String getPlayerVotedOut() {
        return playerVotedOut;
    }

    public String getPlayerRole() { return playerRole; }

    public String getStory() { return story; }

}

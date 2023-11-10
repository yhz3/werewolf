package use_case.vote_out;

public class VoteOutInputData {
    private final String playerVotedOut;

    public VoteOutInputData(String playerVotedOut) {
        this.playerVotedOut = playerVotedOut;
    }

    public String getPlayerVotedOut() {
        return playerVotedOut;
    }


}

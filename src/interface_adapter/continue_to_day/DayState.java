package interface_adapter.continue_to_day;

// TODO: This may need error variables in case name entered is not correct
public class DayState {
    private String playerVotedOut;
    private String[] players;

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public String getPlayerVotedOut() {
        return playerVotedOut;
    }

    public void setPlayerVotedOut(String playerVotedOut) {
        this.playerVotedOut = playerVotedOut;
    }
}

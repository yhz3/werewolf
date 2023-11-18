package interface_adapter.new_game;

public class NewGameState {
    private final String[] players;

    public NewGameState(int numberOfPlayers) {
        players = new String[numberOfPlayers];
    }

    public void setPlayer(int number, String player) {
        if (number >= 0 && number <= players.length) {
            players[number] = player;
        } else {
            throw new IllegalArgumentException("Invalid player number");
        }
    }
    public String getPlayer(int number) {
        if (number >= 0 && number <= players.length) {
            return players[number];
        } else {
            throw new IllegalArgumentException("Invalid player number");
        }
    }
}

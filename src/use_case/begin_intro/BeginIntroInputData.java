package use_case.begin_intro;
import entity.Game;

public class BeginIntroInputData {
    final private Game game;

    public BeginIntroInputData(Game game) {
        this.game = game;
    }
    public Game getGame() {
        return this.game;
    }
}

package use_case.new_game;
import entity.Game;

public class NewGameOutputData {

    private final Game newGame;

    public NewGameOutputData(Game newGame){
        this.newGame = newGame;
    }

    public Game getNewGame(){
        return newGame;
    }
}

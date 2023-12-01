package use_case.restart_game;

import entity.Game;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;

public class RestartGameInteractor implements RestartGameInputBoundary {
    private Game game;
    private GameDataAccessInterface gameData;
    private final RestartGameOutputBoundary userPresenter;
    private final ConversationDataAccessInterface conversationDataAccessObject;


    public RestartGameInteractor(GameDataAccessInterface gameData, RestartGameOutputBoundary userPresenter, ConversationDataAccessInterface conversationDataAccessObject) {
        this.gameData = gameData;
        this.game = gameData.getGame();
        this.userPresenter = userPresenter;
        this.conversationDataAccessObject = conversationDataAccessObject;
    }

    public void execute() {
        game.clearAll();
        conversationDataAccessObject.getPromptGenerator().getConversationHistory().clearConversationHistory();
        userPresenter.prepareNewGameView();
    }


}

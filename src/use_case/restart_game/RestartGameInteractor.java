package use_case.restart_game;

import entity.Game;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;

public class RestartGameInteractor implements RestartGameInputBoundary {
    private final RestartGameOutputBoundary userPresenter;


    public RestartGameInteractor(RestartGameOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
    }

    public void execute() {
        // A new game object is made in new game interactor, new conversation history in begin intro
        userPresenter.prepareNewGameView();
    }


}

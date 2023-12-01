package use_case.new_game;

import data_access.GameDataAccessObject;
import entity.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NewGameInteractorTest {

        private GameDataAccessObject gameDataAccessObject;

    @BeforeEach
    void init() {
    }

    @Test
    void newGameSuccess() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Gold Experience");
        names.add("Stick Fingers");
        names.add("Sex Pistols");
        names.add("Purple Haze");
        names.add("Moody Blues");
        names.add("Aerosmith");
        NewGameInputData newGameInputData = new NewGameInputData(names);
        NewGameOutputBoundary newGamePresenter = new NewGameOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                fail("New game should not fail.");
            }

            @Override
            public void prepareSuccessView(NewGameOutputData newGameOutputData) {
                Game game = gameDataAccessObject.getGame();
                assertEquals(6, game.getAliveVillagers().size() + game.getAliveWerewolves().size());
                assertEquals(4, game.getAliveVillagers().size());
                assertEquals(2, game.getAliveWerewolves().size());
                assertTrue(newGameOutputData.getVillagers().length > 0);
                assertTrue(newGameOutputData.getWerewolves().length > 0);
            }
        };
        gameDataAccessObject = new GameDataAccessObject(new Game());
        NewGameInteractor newGameInteractor = new NewGameInteractor(gameDataAccessObject, newGamePresenter);
        newGameInteractor.execute(newGameInputData);
    }

    @Test
    void newGameFail() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("Giorno");
        names.add("Bruno");
        NewGameInputData newGameInputData = new NewGameInputData(names);
        NewGameOutputBoundary newGamePresenter = new NewGameOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
            }

            @Override
            public void prepareSuccessView(NewGameOutputData newGameOutputData) {
                fail("New game should fail.");
            }
        };
        gameDataAccessObject = new GameDataAccessObject(new Game());
        NewGameInteractor newGameInteractor = new NewGameInteractor(gameDataAccessObject, newGamePresenter);
        newGameInteractor.execute(newGameInputData);

    }
}
package use_case.check_win;

import data_access.GameDataAccessObject;
import entity.Game;
import entity.Villager;
import entity.Werewolf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckWinInteractorTest {

    private GameDataAccessObject gameDataAccessObject;
    private CheckWinOutputBoundary checkWinPresenter;
    private Villager itadori;
    private Villager nobara;
    private Werewolf mahito;

    @BeforeEach
    void init() {
        itadori = new Villager("Itadori");
        nobara = new Villager("Nobara");
        mahito = new Werewolf("Mahito");
    }

    @Test
    void villagerWin() {
        Game game = new Game();
        game.addPlayer(itadori);
        game.addPlayer(nobara);
        gameDataAccessObject = new GameDataAccessObject(game);
        checkWinPresenter = new CheckWinOutputBoundary() {
            @Override
            public void prepareGameContinuesView() {
                fail("Game should not continue.");
            }

            @Override
            public void prepareVillagerWinView() {
            }

            @Override
            public void prepareWerewolfWinView() {
                fail("Werewolves should not win.");
            }
        };
        CheckWinInteractor checkWinInteractor = new CheckWinInteractor(gameDataAccessObject, checkWinPresenter);
        checkWinInteractor.execute();
    }

    @Test
    void werewolfWin() {
        Game game = new Game();
        game.addPlayer(mahito);
        gameDataAccessObject = new GameDataAccessObject(game);
        checkWinPresenter = new CheckWinOutputBoundary() {
            @Override
            public void prepareGameContinuesView() {
                fail("Game should not continue.");
            }

            @Override
            public void prepareVillagerWinView() {
                fail("Villagers should not win.");
            }

            @Override
            public void prepareWerewolfWinView() {
            }
        };
        CheckWinInteractor checkWinInteractor = new CheckWinInteractor(gameDataAccessObject, checkWinPresenter);
        checkWinInteractor.execute();
    }

    @Test
    void gameContinue() {
        Game game = new Game();
        game.addPlayer(itadori);
        game.addPlayer(nobara);
        game.addPlayer(mahito);
        gameDataAccessObject = new GameDataAccessObject(game);
        checkWinPresenter = new CheckWinOutputBoundary() {
            @Override
            public void prepareGameContinuesView() {
            }

            @Override
            public void prepareVillagerWinView() {
                fail("Villagers should not win.");
            }

            @Override
            public void prepareWerewolfWinView() {
                fail("Werewolves should not win.");
            }
        };
        CheckWinInteractor checkWinInteractor = new CheckWinInteractor(gameDataAccessObject, checkWinPresenter);
        checkWinInteractor.execute();
    }
}
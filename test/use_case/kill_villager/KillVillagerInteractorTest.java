package use_case.kill_villager;

import data_access.ConversationDataAccessObject;
import data_access.DummyChatGPTAPI;
import data_access.GameDataAccessObject;
import entity.ConversationHistory;
import entity.Game;
import entity.PromptGenerator;
import entity.Villager;
import interface_adapter.kill_villager.KillVillagerPresenter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

class KillVillagerInteractorTest {

    private ConversationDataAccessInterface conversationDataAccessInterface;
    private GameDataAccessInterface gameDataAccessInterface;
    private ChatAPIAccessInterface gptDataAccessObject;
    private KillVillagerInputData killVillagerInputData;
    private KillVillagerOutputBoundary killVillagerPresenter;

    @BeforeEach
    void init() {
        conversationDataAccessInterface = new ConversationDataAccessObject(new ConversationHistory());
        Game game = new Game();
        Villager villager = new Villager("Player1");
        game.addPlayer(villager);
        gameDataAccessInterface = new GameDataAccessObject(game);
        gptDataAccessObject = new DummyChatGPTAPI();
    }

    @Test
    void successTest() {
        killVillagerInputData = new KillVillagerInputData("Player1");
        killVillagerPresenter = new KillVillagerOutputBoundary() {
            @Override
            public void prepareSuccessView(KillVillagerOutputData villagerDeathStory) {
                PromptGenerator promptGenerator = conversationDataAccessInterface.getPromptGenerator();
                ConversationHistory conversationHistory = promptGenerator.getConversationHistory();
                assertTrue(conversationHistory.getConversationHistory().contains("One night has passed and this " +
                        "player has been killed: Player1."));
                // Checks the prompt we fed to ChatGPT has been stored.
                assertTrue(conversationHistory.getConversationHistory().contains("Successfully received prompt " +
                        "length of"));
                // Checks the response ChatGPT provided was returned.
                Game game = gameDataAccessInterface.getGame();
                assertEquals(game.getAliveVillagers().size(), 0);
                // Checks the villager has been removed from the aliveVillagers hashmap.
                assertEquals(game.getDeadVillagers().size(), 1);
                // Checks the villager has been added to the deadVillagers hashmap.
                assertTrue(game.isDay());
                // Checks the time of day has been changed.
                assertTrue(villagerDeathStory.getVillagerDeathStory().contains("Successfully received prompt "));
                // Checks the output data is properly stored.
            }

            @Override
            public void prepareFailView(String error) {
                fail("The interactor is not supposed to fail.");
            }
        };
    }

    @Test
    void failTest() {
        killVillagerInputData = new KillVillagerInputData("Player2");
        killVillagerPresenter = new KillVillagerOutputBoundary() {
            @Override
            public void prepareSuccessView(KillVillagerOutputData villagerDeathStory) {
                fail("The interactor is supposed to fail.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Person is not a live villager.", error);
            }
        };
    }

    @AfterEach
    void teardown() {
        KillVillagerInteractor killVillagerInteractor = new KillVillagerInteractor(conversationDataAccessInterface,
                gameDataAccessInterface, killVillagerPresenter, gptDataAccessObject);
        killVillagerInteractor.killVillager(killVillagerInputData);
    }
}
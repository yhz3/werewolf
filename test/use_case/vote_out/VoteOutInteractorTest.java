package use_case.vote_out;

import data_access.ConversationDataAccessObject;
import data_access.DummyChatGPTAPI;
import data_access.GameDataAccessObject;
import entity.ConversationHistory;
import entity.Game;
import entity.Villager;
import entity.Werewolf;
import org.junit.jupiter.api.Test;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoteOutInteractorTest {

    @Test
    void successTest() {
        // TEST CASE: Werewolf is voted out, 2 villagers remain, villagers win.
        String werewolfName = "Werewolf";
        // Initializing werewolf player and game object, and creating variables used in testing
        Game game = new Game();
        // The game is in night time at default but since we're voting out it must be day
        game.changeGameState();
        Werewolf werewolf = new Werewolf(werewolfName);
        Villager villager1 = new Villager("Dinnerbone");
        Villager villager2 = new Villager("Burple");
        game.addPlayer(werewolf);
        game.addPlayer(villager1);
        game.addPlayer(villager2);
        int numVillagers = game.getAliveVillagers().size();
        // Input data
        VoteOutInputData inputData = new VoteOutInputData(werewolfName);
        // Creating parameters the interactor needs
        ConversationHistory history = new ConversationHistory();
        ConversationDataAccessInterface conversationObject = new ConversationDataAccessObject(history);
        GameDataAccessInterface gameObject = new GameDataAccessObject(game);
        ChatAPIAccessInterface chatObject = new DummyChatGPTAPI();
        // Getting the size of the conversation history for testing
        int conversationHistorySize = history.getConversationHistory().length();
        // Assertions are done within the "presenter"
        VoteOutOutputBoundary successPresenter = new VoteOutOutputBoundary() {
            @Override
            public void prepareSuccessView(VoteOutOutputData player) {
                // Test that the correct player was voted out (the one with name "Werewolf")
                assertEquals(werewolfName, player.getPlayerVotedOut());
                // Test that the player is no longer in the playerList
                List<String> playerList = Arrays.stream(game.getPlayerNames()).toList();
                assertFalse(playerList.contains(werewolfName));
                // Check that it's night now
                assertFalse(game.isDay());
                // Check that the villagers won
                assertTrue(game.checkGameOver());
                // Check that the same amount of villagers remain
                assertEquals(numVillagers, game.getAliveVillagers().size());
                // Check that the conversation history is larger now; hence, a story was generated
                assertTrue(conversationObject.getPromptGenerator().getConversationHistory().
                        getConversationHistory().length() > conversationHistorySize);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case should not fail for this test case.");
            }
        };

        VoteOutInteractor interactor = new VoteOutInteractor(conversationObject, gameObject, chatObject, successPresenter);
        interactor.voteOutPlayer(inputData);
    }

    @Test
    void failTest() {
        // TEST CASE: Attempt to vote out a player who isn't in the game
        String playerName = "Mariah Carey";
        Game game = new Game();
        Werewolf werewolf = new Werewolf("Hectique");
        Villager villager1 = new Villager("Towel");
        Villager villager2 = new Villager("COVID20");
        // Input data has a name of a player who doesn't exist (Mariah Carey)
        VoteOutInputData inputData = new VoteOutInputData(playerName);
        ConversationHistory history = new ConversationHistory();
        ConversationDataAccessInterface conversationObject = new ConversationDataAccessObject(history);
        GameDataAccessInterface gameObject = new GameDataAccessObject(game);
        ChatAPIAccessInterface chatObject = new DummyChatGPTAPI();

        VoteOutOutputBoundary successPresenter = new VoteOutOutputBoundary() {
            @Override
            public void prepareSuccessView(VoteOutOutputData player) {
                fail("Use case should not pass for this test case");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("No such player exists", error);
            }
        };

        VoteOutInteractor interactor = new VoteOutInteractor(conversationObject, gameObject, chatObject, successPresenter);
        interactor.voteOutPlayer(inputData);
    }
}
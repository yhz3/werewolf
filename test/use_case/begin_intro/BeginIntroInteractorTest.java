package use_case.begin_intro;

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
import use_case.vote_out.VoteOutInputData;
import use_case.vote_out.VoteOutInteractor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeginIntroInteractorTest {


    // We only need a success test; we just want to check that the story is being generated and that's it
    @Test
    void successTest() {
        // TEST: We want to make sure an intro story was generated, and that the villagers are in the output data
        Game game = new Game();
        Villager villager1 = new Villager("Radiohead");
        Villager villager2 = new Villager("Constantinople");
        Villager villager3 = new Villager("Asriel");
        Werewolf werewolf = new Werewolf("Ivyoary");
        game.addPlayer(villager1);
        game.addPlayer(villager2);
        game.addPlayer(villager3);
        game.addPlayer(werewolf);
        // Input data
        BeginIntroInputData inputData = new BeginIntroInputData();
        // Creating parameters the interactor needs
        ConversationHistory history = new ConversationHistory();
        ConversationDataAccessInterface conversationObject = new ConversationDataAccessObject(history);
        GameDataAccessInterface gameObject = new GameDataAccessObject(game);
        ChatAPIAccessInterface chatObject = new DummyChatGPTAPI();
        BeginIntroOutputBoundary successPresenter = new BeginIntroOutputBoundary() {
            @Override
            public void prepareSuccessView(BeginIntroOutputData beginIntroOutputData) {
                List<String> villagersList = Arrays.stream(beginIntroOutputData.getVillagers()).toList();
                // Make sure it has each villager
                assertTrue(villagersList.contains(villager1.getName()));
                assertTrue(villagersList.contains(villager2.getName()));
                assertTrue(villagersList.contains(villager3.getName()));
                // Make sure it only has villagers
                assertFalse(villagersList.contains(werewolf.getName()));
                // Make sure a story was generated
                assertFalse(beginIntroOutputData.getIntroStory().isEmpty());
            }
        };

        BeginIntroInteractor interactor = new BeginIntroInteractor(conversationObject, gameObject, chatObject, successPresenter);
        interactor.execute(inputData);

    }


}
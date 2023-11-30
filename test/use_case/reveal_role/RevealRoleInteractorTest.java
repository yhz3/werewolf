package use_case.reveal_role;

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

import static org.junit.jupiter.api.Assertions.*;

class RevealRoleInteractorTest {

    @Test
    void successTest() {
        // TEST: Does it reveal the werewolf role correctly?
        // Setup
        Game game = new Game();
        Villager villager1 = new Villager("Samsung");
        Villager villager2 = new Villager("Undyne");
        Villager villager3 = new Villager("Papryus");
        Villager villager4 = new Villager("Monster Kid");
        Werewolf werewolf1 = new Werewolf("Mrs. Beast");
        Werewolf werewolf2 = new Werewolf("Wumper");
        game.addPlayer(villager1);
        game.addPlayer(villager2);
        game.addPlayer(villager3);
        game.addPlayer(villager4);
        game.addPlayer(werewolf1);
        game.addPlayer(werewolf2);
        // Input Data
        RevealRoleInputData inputData = new RevealRoleInputData("Mrs. Beast");
        // Creating parameters the interactor needs
        GameDataAccessInterface gameObject = new GameDataAccessObject(game);

        RevealRoleOutputBoundary successPresenter = new RevealRoleOutputBoundary() {
            // TODO: This currently fails because of a bug in reveal role, but the use case is correct (see RevealRoleInteractor)
            @Override
            public void prepareDisplayRoleView(RevealRoleOutputData revealRoleOutputData) {
                // Check that the role was equal to the role of werewolf1 (obviously werewolf)
                assertEquals(revealRoleOutputData.getRole(), werewolf1.getRole());
            }

            @Override
            public void prepareFailView() {
                fail("Use case shouldn't fail for this test");
            }

        };

        RevealRoleInteractor interactor = new RevealRoleInteractor(gameObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {

    }

}
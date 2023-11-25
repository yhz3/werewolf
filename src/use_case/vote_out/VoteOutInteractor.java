package use_case.vote_out;

import entity.Game;
import entity.PromptGenerator;
import entity.Werewolf;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;

public class VoteOutInteractor implements VoteOutInputBoundary {
    private final ConversationDataAccessInterface conversationDataAccessObject;
    private final GameDataAccessInterface gameDataAccessObject;
    private final ChatAPIAccessInterface gptDataAccessObject;
    private final VoteOutOutputBoundary userPresenter;


    public VoteOutInteractor(ConversationDataAccessInterface conversationDataAccessObject, GameDataAccessInterface gameDataAccessObject, ChatAPIAccessInterface gptDataAccessObject, VoteOutOutputBoundary userPresenter) {
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.gameDataAccessObject = gameDataAccessObject;
        this.gptDataAccessObject = gptDataAccessObject;
        this.userPresenter = userPresenter;
    }

    // This method prepares success view if a werewolf or villager is voted out
    // Meanwhile, if an incorrect username is entered (it doesn't exist) the fail view is prepared
    @Override
    public void voteOutPlayer(VoteOutInputData voteOutInputData) {
        // Get the game
        Game game = gameDataAccessObject.getGame();
        // Get the prompt generator
        PromptGenerator promptGenerator = conversationDataAccessObject.getPromptGenerator();

        // Get name of player voted out from input data
        String playerVotedOut = voteOutInputData.getPlayerVotedOut();
        // This case is specifically when the name isn't a werewolf nor is it a player, so the name doesn't exist
        if (!(game.getAliveVillagers().containsKey(playerVotedOut) || game.getAliveWerewolves().containsKey(playerVotedOut))) {
            userPresenter.prepareFailView("No such player exists");
        }
        else {
            // Get the player's role
            String playerRole = "";
            if (game.getAliveVillagers().containsKey(playerVotedOut)) {
                playerRole = "villager";
            }
            else if (game.getAliveWerewolves().containsKey(playerVotedOut)) {
                playerRole = "werewolf";
            }
            // Get the story
            String prompt = promptGenerator.generatePlayerVotedOutPrompt(playerVotedOut, playerRole);
            String story = gptDataAccessObject.getResponse(prompt);
            // Save the gpt response
            promptGenerator.getConversationHistory().addGPTMessage(story);
            // Kill that Player
            game.killPlayer(playerVotedOut);
            // Switch to night
            game.changeGameState();
            // Save game
            gameDataAccessObject.save(game);
            // Save prompt generator
            conversationDataAccessObject.save(promptGenerator);
            // Create the output data
            VoteOutOutputData voteOutOutputData = new VoteOutOutputData(playerVotedOut, playerRole, story);
            // Success View
            userPresenter.prepareSuccessView(voteOutOutputData);
        }
    }
}

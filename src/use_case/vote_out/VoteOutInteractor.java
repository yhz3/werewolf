package use_case.vote_out;

import entity.Game;
import entity.PromptGenerator;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.PromptGameDataAccessInterface;

public class VoteOutInteractor implements VoteOutInputBoundary {
    private final PromptGameDataAccessInterface gameDataAccessObject;
    private final ChatAPIAccessInterface storyDataAccessObject;
    private final VoteOutOutputBoundary userPresenter;
    private final Game game;
    private final PromptGenerator promptGenerator;


    public VoteOutInteractor(PromptGameDataAccessInterface gameDataAccessObject, ChatAPIAccessInterface storyDataAccessObject, VoteOutOutputBoundary userPresenter) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.storyDataAccessObject = storyDataAccessObject;
        this.userPresenter = userPresenter;
        // Get the game
        game = gameDataAccessObject.getGame();
        // Get the prompt generator
        promptGenerator = gameDataAccessObject.getPromptGenerator();
    }

    // This method prepares success view if a werewolf or villager is voted out
    // Meanwhile, if an incorrect username is entered (it doesn't exist) the fail view is prepared
    @Override
    public void voteOutPlayer(VoteOutInputData voteOutInputData) {
        // Get name of player voted out from input data
        String playerVotedOut = voteOutInputData.getVotedName();
        // This case is specifically when the name isn't a werewolf nor is it a player, so the name doesn't exist
        if (!(game.getAliveVillagers().containsKey(playerVotedOut) || game.getAliveWerewolves().containsKey(playerVotedOut))) {
            // TODO: we can make "no such player exists" a constant or something later but not important right now
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
            String story = storyDataAccessObject.getResponse(prompt);
            // Kill that Player
            game.killPlayer(playerVotedOut);
            // Switch to night
            game.changeGameState();
            // Save game
            gameDataAccessObject.save(game);
            // Save prompt generator
            gameDataAccessObject.save(promptGenerator);
            // Create the output data
            VoteOutOutputData voteOutOutputData = new VoteOutOutputData(playerVotedOut, playerRole, story);
            // Success View
            userPresenter.prepareSuccessView(voteOutOutputData);
        }
    }
}

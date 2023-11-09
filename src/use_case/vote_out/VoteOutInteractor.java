package use_case.vote_out;

import entity.Game;

public class VoteOutInteractor implements VoteOutInputBoundary {
    final VoteOutDataAccessInterface gameDataAccessObject;
    final VoteOutOutputBoundary userPresenter;

    public VoteOutInteractor(VoteOutDataAccessInterface gameDataAccessObject, VoteOutOutputBoundary userPresenter) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.userPresenter = userPresenter;
    }

    // This method prepares success view if a werewolf or villager is voted out
    // Meanwhile, if an incorrect username is entered (it doesn't exist) the fail view is prepared
    @Override
    public void voteOutPlayer(VoteOutInputData voteOutInputData) {
        // Get the game
        Game game = gameDataAccessObject.getGame();
        // Get name of player voted out from input data
        String votedName = voteOutInputData.getVotedName();
        // This case is specifically when the name isn't a werewolf nor is it a player, so the name doesn't exist
        if (!(game.getAliveVillagers().containsKey(votedName) || game.getAliveWerewolves().containsKey(votedName))) {
            userPresenter.prepareFailView();
        }
        // kill player
        userPresenter.prepareSuccessView();
        gameDataAccessObject.save(game);
        // Kill that Player
        game.killPlayer(votedName);
        // Switch to night
        game.changeGameState();
    }
}

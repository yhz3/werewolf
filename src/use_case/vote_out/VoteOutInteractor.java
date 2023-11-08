package use_case.vote_out;

import entity.Game;

public class VoteOutInteractor implements VoteOutInputBoundary {
    final VoteOutDataAccessInterface userDataAccessObject;
    final VoteOutOutputBoundary userPresenter;
    final Game game;

    public VoteOutInteractor(VoteOutDataAccessInterface userDataAccessObject, VoteOutOutputBoundary userPresenter, Game game) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.game = game;
    }

    // This method returns true if a werewolf is voted out and returns false otherwise
    // Thus, it is false if a villager is voted out OR if an invalid name is entered
    @Override
    public boolean voteOutPlayer(VoteOutInputData voteOutInputData) {
        boolean werewolfVotedOut = false;
        String votedName = voteOutInputData.getVotedName();
        if (this.game.getAliveWerewolves().containsKey(votedName)){
            werewolfVotedOut = true;
        }
        // This case is specifically when the name isn't a werewolf nor is it a player, so the name doesn't exist
        else if (!this.game.getAliveVillagers().containsKey(votedName)) {
            userPresenter.prepareFailView();
            return false;
        }
        // kill player
        userPresenter.prepareSuccessView();
        userDataAccessObject.save(game);
        // Kill that Player
        this.game.killPlayer(votedName);
        // Switch to night TODO: figure out how the hell this works (should my use case even do this)
        this.game.changeGameState();
        return werewolfVotedOut;
    }
}

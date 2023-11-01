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
    @Override
    public boolean execute(VoteOutInputData voteOutInputData) {
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
        this.game.killPlayer(votedName);
        return werewolfVotedOut;
    }
}

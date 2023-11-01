package use_case.vote_out;

import entity.Game;
import entity.Player;

public class VoteOutInputData {
    private String votedName;

    public VoteOutInputData(String votedName) {
        this.votedName = votedName;
    }

    public String getVotedName() {
        return votedName;
    }

    public void setVotedName(String votedName) {
        this.votedName = votedName;
    }


}

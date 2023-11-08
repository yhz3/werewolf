package use_case.vote_out;

public class VoteOutInputData {
    private String votedName;

    public VoteOutInputData(String votedName) {
        this.votedName = votedName;
    }

    public String getVotedName() {
        return votedName;
    }

    // TODO: re-evaluate if we need the setter
    public void setVotedName(String votedName) {
        this.votedName = votedName;
    }


}

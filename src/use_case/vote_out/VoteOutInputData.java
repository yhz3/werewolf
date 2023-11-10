package use_case.vote_out;

public class VoteOutInputData {
    private final String votedName;

    public VoteOutInputData(String votedName) {
        this.votedName = votedName;
    }

    public String getVotedName() {
        return votedName;
    }


}

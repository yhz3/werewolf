package use_case.vote_out;

public interface VoteOutOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(VoteOutOutputData playerData);
}

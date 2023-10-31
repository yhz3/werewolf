package use_case.vote_out;

public class VoteOutInteractor implements VoteOutInputBoundary {
    final VoteOutDataAccessInterface userDataAccessObject;
    final VoteOutOutputBoundary userPresenter;

    public VoteOutInteractor(VoteOutDataAccessInterface userDataAccessObject, VoteOutOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }


    @Override
    public void voteOut(VoteOutInputData voteOutInputData) {

    }
}

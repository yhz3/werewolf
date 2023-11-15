package use_case.continue_to_night;

public class ContinueToNightInteractor implements ContinueToNightInputBoundary {
    private final ContinueToNightOutputBoundary continueToNightPresenter;

    public ContinueToNightInteractor(ContinueToNightOutputBoundary continueToNightPresenter) {
        this.continueToNightPresenter = continueToNightPresenter;
    }

    @Override
    public void execute() {
        this.continueToNightPresenter.prepareSuccessView();
    }
}

package use_case.continue_to_day;

public class ContinueToDayInteractor implements ContinueToDayInputBoundary{
    private final ContinueToDayOutputBoundary continueToDayPresenter;

    public ContinueToDayInteractor(ContinueToDayOutputBoundary continueToDayPresenter) {
        this.continueToDayPresenter = continueToDayPresenter;
    }

    @Override
    public void execute() {
        this.continueToDayPresenter.prepareSuccessView();
    }
}

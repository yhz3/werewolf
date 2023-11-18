package interface_adapter.continue_to_day;

import use_case.continue_to_day.ContinueToDayInputBoundary;

public class ContinueToDayController {
    private final ContinueToDayInputBoundary continueToDayInteractor;

    public ContinueToDayController(ContinueToDayInputBoundary continueToDayInteractor) {
        this.continueToDayInteractor = continueToDayInteractor;
    }
    public void execute() {
        this.continueToDayInteractor.execute();
    }
}

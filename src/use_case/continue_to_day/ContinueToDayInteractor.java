package use_case.continue_to_day;

import use_case.data_access_interface.GameDataAccessInterface;

public class ContinueToDayInteractor implements ContinueToDayInputBoundary{
    private final GameDataAccessInterface gameDataAccessObject;

    private final ContinueToDayOutputBoundary continueToDayPresenter;

    public ContinueToDayInteractor(GameDataAccessInterface gameDataAccessObject, ContinueToDayOutputBoundary continueToDayPresenter) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.continueToDayPresenter = continueToDayPresenter;
    }

    @Override
    public void execute() {
        ContinueToDayOutputData continueToDayOutputData = new ContinueToDayOutputData(
                gameDataAccessObject.getGame().getPlayerNames());
        this.continueToDayPresenter.prepareSuccessView(continueToDayOutputData);
    }
}

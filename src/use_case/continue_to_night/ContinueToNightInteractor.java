package use_case.continue_to_night;

import use_case.continue_to_day.ContinueToDayOutputData;
import use_case.data_access_interface.GameDataAccessInterface;

public class ContinueToNightInteractor implements ContinueToNightInputBoundary {
    private final GameDataAccessInterface gameDataAccessObject;
    private final ContinueToNightOutputBoundary continueToNightPresenter;

    public ContinueToNightInteractor(GameDataAccessInterface gameDataAccessObject, ContinueToNightOutputBoundary continueToNightPresenter) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.continueToNightPresenter = continueToNightPresenter;
    }

    @Override
    public void execute() {
        ContinueToNightOutputData continueToNightOutputData = new ContinueToNightOutputData(
                gameDataAccessObject.getGame().getVillagerNames());
        this.continueToNightPresenter.prepareSuccessView(continueToNightOutputData);
    }
}

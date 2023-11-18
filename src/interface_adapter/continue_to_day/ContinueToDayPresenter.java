package interface_adapter.continue_to_day;

import interface_adapter.ViewManagerModel;
import use_case.continue_to_day.ContinueToDayOutputBoundary;
import use_case.continue_to_day.ContinueToDayOutputData;

public class ContinueToDayPresenter implements ContinueToDayOutputBoundary {
    private final DayViewModel dayViewModel;
    private final ViewManagerModel viewManagerModel;

    public ContinueToDayPresenter(DayViewModel dayViewModel, ViewManagerModel viewManagerModel) {
        this.dayViewModel = dayViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ContinueToDayOutputData continueToDayOutputData) {
        DayState dayState = dayViewModel.getState();
        dayState.setPlayers(continueToDayOutputData.getPlayers());

        // Switch to the dayViewModel
        viewManagerModel.setActiveView(dayViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

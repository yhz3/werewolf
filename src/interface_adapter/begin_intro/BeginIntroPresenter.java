package interface_adapter.begin_intro;

import interface_adapter.ViewManagerModel;
import use_case.begin_intro.BeginIntroOutputBoundary;
import use_case.begin_intro.BeginIntroOutputData;

import javax.swing.text.View;

public class BeginIntroPresenter implements BeginIntroOutputBoundary {
    private final BeginIntroViewModel beginIntroViewModel;
    private ViewManagerModel viewManagerModel;

    public BeginIntroPresenter(BeginIntroViewModel beginIntroViewModel, ViewManagerModel viewManagerModel) {
        this.beginIntroViewModel = beginIntroViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(BeginIntroOutputData beginIntroOutputData) {
        beginIntroViewModel.setIntroStory(beginIntroOutputData.getIntroStory());
        beginIntroViewModel.firePropertyChanged();
    }
}

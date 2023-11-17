package interface_adapter.reveal_role;

import interface_adapter.ViewManagerModel;
import use_case.reveal_role.RevealRoleOutputBoundary;
import use_case.reveal_role.RevealRoleOutputData;

public class RevealRolePresenter implements RevealRoleOutputBoundary{

    private final RevealRoleViewModel revealRoleViewModel;

    private final ViewManagerModel viewManagerModel;

    public RevealRolePresenter(RevealRoleViewModel revealRoleViewModel, ViewManagerModel viewManagerModel){
        this.revealRoleViewModel = revealRoleViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    // #TODO implement prepareFailView
    public void prepareFailView(){}

    public void prepareDisplayRoleView(RevealRoleOutputData revealRoleOutputData){
        revealRoleViewModel.setRole(revealRoleOutputData.getRole());
        revealRoleViewModel.firePropertyChanged();
    }
}

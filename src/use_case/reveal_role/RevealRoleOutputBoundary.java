package use_case.reveal_role;

public interface RevealRoleOutputBoundary {
    public void prepareFailView();

    public void prepareDisplayRoleView(RevealRoleOutputData revealRoleOutputData);
}

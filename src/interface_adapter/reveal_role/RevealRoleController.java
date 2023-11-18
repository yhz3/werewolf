package interface_adapter.reveal_role;

import use_case.reveal_role.RevealRoleInputBoundary;
import use_case.reveal_role.RevealRoleInputData;

public class RevealRoleController {
    private final RevealRoleInputBoundary revealRoleInteractor;

    public RevealRoleController(RevealRoleInputBoundary revealRoleInteractor){
        this.revealRoleInteractor = revealRoleInteractor;
    }

    public void execute(String userName){
        RevealRoleInputData inputData = new RevealRoleInputData(userName);
        revealRoleInteractor.execute(inputData);
    }
}

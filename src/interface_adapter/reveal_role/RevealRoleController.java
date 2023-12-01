package interface_adapter.reveal_role;

import use_case.reveal_role.RevealRoleInputBoundary;
import use_case.reveal_role.RevealRoleInputData;

public class RevealRoleController {
    private final RevealRoleInputBoundary revealRoleInteractor;

    public RevealRoleController(RevealRoleInputBoundary revealRoleInteractor){
        this.revealRoleInteractor = revealRoleInteractor;
    }

    public void execute(String playerName){
        RevealRoleInputData inputData = new RevealRoleInputData(playerName);
        revealRoleInteractor.execute(inputData);
    }
}

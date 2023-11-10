package use_case.kill_villager;

public interface KillVillagerOutputBoundary {
    void prepareSuccessView(KillVillagerOutputData villagerDeathStory);
    // Presenter should give the story written by GPT-4.

    void prepareFailView();
    // Presenter should give a message like "Entered name is not a valid live villager". We are leaving the actual error
    // message up to the KillVillagerPresenter to come up with.
}
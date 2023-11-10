package use_case.kill_villager;

public interface KillVillagerOutputBoundary {
    void prepareSuccessView(String villagerDeathStory);
    // Presenter should give the story written by GPT-4.

    void prepareFailView(String villager);
    // Presenter should give a message like "[Villager] is not a valid live villager". Leaving the actual error message
    // up to the KillVillagerPresenter to come up with, KillVillagerInteractor only provides a name.
}
package use_case.kill_villager;

public interface KillVillagerOutputBoundary {
    void prepareSuccessView(String villagerDeathStory);
    // Presenter should give the story written by GPT-4.

    void prepareFailView(String villager);
    // Presenter should give a message like "[Villager] is not a valid live villager". Leaving the actual error message
    // up to the KillVillagerPresenter to come up with, KillVillagerInteractor only provides a name.

    //Note: Instead of employing an output boundary for the KillVillager use case, we decided to just pass a string
    // instead since writing an entire class just for the sake of storing the story of the killed villager seemed
    // unnecessary.
}
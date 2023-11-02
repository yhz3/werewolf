package use_case.kill_villager;

public interface KillVillagerOutputBoundary {
    void prepareSuccessView(String villagerDeathStory);

    void prepareFailView(String villager);

    //Note: Instead of employing an output boundary for the KillVillager use case, we decided to just pass a string
    // instead since writing an entire class just for the sake of storing the story of the killed villager seemed
    // unnecessary.
}
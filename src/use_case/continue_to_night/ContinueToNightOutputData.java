package use_case.continue_to_night;

public class ContinueToNightOutputData {
    private final String[] villagers;

    public ContinueToNightOutputData(String[] villagers) {
        this.villagers = villagers;
    }

    public String[] getVillagers() {
        return villagers;
    }
}

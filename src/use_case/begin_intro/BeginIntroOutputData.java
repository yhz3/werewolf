package use_case.begin_intro;

public class BeginIntroOutputData {
    private final String introStory;
    private final String[] villagers;

    public BeginIntroOutputData(String introStory, String[] villagers) {
        this.introStory = introStory;
        this.villagers = villagers;
    }

    public String getIntroStory() {
        return introStory;
    }

    public String[] getVillagers() {
        return villagers;
    }
}

package use_case.begin_intro;

import entity.Game;
import entity.PromptGenerator;

public class BeginIntroOutputData {
    private final String introStory;

    public BeginIntroOutputData(String introStory) {
        this.introStory = introStory;
    }

    public String getIntroStory() {
        return introStory;
    }
}

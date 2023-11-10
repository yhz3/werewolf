package use_case;

import entity.PromptGenerator;

public interface PromptAccessInterface {
    void savePromptGenerator(PromptGenerator promptGenerator);
    PromptGenerator getPromptGenerator();
}

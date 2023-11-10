package use_case.data_access_interface;

import entity.PromptGenerator;

public interface PromptGameDataAccessInterface extends GameDataAccessInterface {
    PromptGenerator getPromptGenerator();
    // getPromptGenerator in DAO should be pulling the ConversationHistory to then construct a PromptGenerator off of
    void save(PromptGenerator promptGenerator);
}

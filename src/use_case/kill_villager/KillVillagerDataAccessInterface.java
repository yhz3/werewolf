<<<<<<< HEAD:src/use_case/kill_villager/KillVillagerDataAccessInterface.java
package use_case.kill_villager;
=======
package use_case.data_access_interface;
>>>>>>> origin/daniyaal:src/use_case/data_access_interface/ConversationDataAccessInterface.java

import entity.PromptGenerator;

<<<<<<< HEAD:src/use_case/kill_villager/KillVillagerDataAccessInterface.java
public interface KillVillagerDataAccessInterface {
    void save(Game game);
    Game getGame();

    PromptGenerator getPromptGenerator();
    // getPromptGenerator in DAO should be pulling the ConversationHistory to then construct a PromptGenerator off of
=======
public interface ConversationDataAccessInterface {
    PromptGenerator getPromptGenerator();
    // getPromptGenerator in DAO should be pulling the ConversationHistory to then construct a PromptGenerator off of
    void save(PromptGenerator promptGenerator);
>>>>>>> origin/daniyaal:src/use_case/data_access_interface/ConversationDataAccessInterface.java
}

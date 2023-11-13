package data_access;

import entity.ConversationHistory;
import entity.PromptGenerator;
import use_case.data_access_interface.ConversationDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConversationDataAccessObject implements ConversationDataAccessInterface {

    // We are calling it the ConversationDataAccessObject because we originally only wanted the DAO to save
    // ConversationHistory but then people forgot and treated the DAO like a PromptGenerator DAO so now we are keeping
    // the name without refactoring other code because we have bigger issues to deal with for now.
    // We recognize that this is contributing to the technical debt of the project.
    private PromptGenerator promptGenerator;
    // Not making this method final so that the save() works.

    public ConversationDataAccessObject(ConversationHistory conversationHistory) throws IOException {
        this.promptGenerator = new PromptGenerator(conversationHistory);
    }

    public PromptGenerator getPromptGenerator() {
        return promptGenerator;
    }

    @Override
    public void save(PromptGenerator promptGenerator) {
        this.promptGenerator = promptGenerator;
    }
}

package data_access;

import entity.ConversationHistory;
import entity.PromptGenerator;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;

public class ConversationDataAccessObject implements ConversationDataAccessInterface {

    // We are calling it the ConversationDataAccessObject because we originally only wanted the DAO to save
    // ConversationHistory but then people forgot and treated the DAO like a PromptGenerator DAO so now we are keeping
    // the name without refactoring other code because we have bigger issues to deal with for now.
    // We recognize that this is contributing to the technical debt of the project.
    private PromptGenerator promptGenerator;
    private ChatAPIAccessInterface compressionAPI;
    // Not making this method final so that the save() works.

    public ConversationDataAccessObject(ConversationHistory conversationHistory, ChatAPIAccessInterface compressionAPI) {
        this.promptGenerator = new PromptGenerator(conversationHistory);
        this.compressionAPI = compressionAPI;
    }

    public PromptGenerator getPromptGenerator() {
        return promptGenerator;
    }

    @Override
    public void save(PromptGenerator promptGenerator) {
        ConversationHistory conversationHistory = promptGenerator.getConversationHistory();

        // Compress old conversation before saving.
        String conversationToCompress = conversationHistory.getConversationToCompress();
        String prompt = "Summarize the following conversation between the User and ChatGPT so that it can be used for context: "
                + conversationToCompress;
        if (conversationToCompress != null) {
            String compressedConversation = compressionAPI.getResponse(prompt);
            conversationHistory.addCompressedConversation(compressedConversation);
        }
        this.promptGenerator = promptGenerator;
    }
}

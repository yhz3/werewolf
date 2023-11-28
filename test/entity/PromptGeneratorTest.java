package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromptGeneratorTest {

    private PromptGenerator promptGenerator;
    private Integer before;

    @BeforeEach
    void init() {
        ConversationHistory conversationHistory = new ConversationHistory();
        promptGenerator = new PromptGenerator(conversationHistory);
        before = promptGenerator.getConversationHistory().getConversationHistory().length();
    }

    @Test
    void generateIntroPrompt() {
        String str = promptGenerator.generateIntroPrompt(new String[]{"Carm"}, new String[] {"Richie"});
        assertTrue(str.contains("Carm") && str.contains("Richie"));
        Integer after = promptGenerator.getConversationHistory().getConversationHistory().length();
        assertTrue(before < after);
    }

    @Test
    void generatePlayerKilledPrompt() {
        String str = promptGenerator.generatePlayerKilledPrompt("Fak");
        assertTrue(str.contains("Fak"));
        Integer after = promptGenerator.getConversationHistory().getConversationHistory().length();
        assertTrue(before < after);
    }

    @Test
    void generatePlayerVotedOutPrompt() {
        String str = promptGenerator.generatePlayerVotedOutPrompt("Sydney", "Chef");
        assertTrue(str.contains("Sydney") && str.contains("Chef"));
        Integer after = promptGenerator.getConversationHistory().getConversationHistory().length();
        assertTrue(before < after);
    }
}
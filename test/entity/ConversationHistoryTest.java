package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConversationHistoryTest {

    private ConversationHistory conversationHistory;

    @BeforeEach
    void init() {
        conversationHistory = new ConversationHistory();
    }

    @Test
    void getConversationHistory() {
        conversationHistory.addUserMessage("Who is the GOAT?");
        conversationHistory.addGPTMessage("Jordan > LeMickey.");
        assertEquals("User: Who is the GOAT? AI: Jordan > LeMickey. ",
                conversationHistory.getConversationHistory());
    }

    @Test
    void getConversationToCompressNull() {
        conversationHistory.addUserMessage("Who is the GOAT?");
        conversationHistory.addGPTMessage("Jordan > LeMickey.");
        conversationHistory.addUserMessage("That's wrong.");
        assertNull(conversationHistory.getConversationToCompress());
    }

    @Test
    void getConversationToCompressTrue() {
        conversationHistory.addUserMessage("Who is the GOAT?");
        conversationHistory.addGPTMessage("Jordan > LeMickey.");
        conversationHistory.addUserMessage("That's wrong.");
        conversationHistory.addGPTMessage("You're wrong.");
        assertEquals("User: Who is the GOAT? AI: Jordan > LeMickey. User: That's wrong. ",
                conversationHistory.getConversationToCompress());
    }

    @Test
    void addCompressedConversation() {
        conversationHistory.addGPTMessage("You're wrong.");
        conversationHistory.addCompressedConversation("User and AI argue over if Jordan is the GOAT or LeBron.");
        assertEquals("User and AI argue over if Jordan is the GOAT or LeBron. AI: You're wrong. ",
                conversationHistory.getConversationHistory());
    }
}
package entity;

import java.util.Arrays;

public class PromptGenerator {
    private final ConversationHistory conversationHistory;
    public PromptGenerator(ConversationHistory conversationHistory) {
        this.conversationHistory = conversationHistory;
        // Conversation history needs to be passed in as an argument since for each use case, we need to pull data
        // from the database and rebuild the PromptGenerator entity.
    }

    public String generateIntroPrompt(String[] players, String[] werewolves) {
        String preamble = "I am playing a game of Werewolf (aka Mafia) with my friends. I want you to be the narrator " +
                "of the game. There are werewolves and villagers, and here is the list of players and werewolves. " +
                "Players who are not werewolves are villagers. ";
        String instruction = " Give a creative intro for me to read out to the players. " +
                "Do not reveal the players' identities. No one has been killed yet.";
        String prompt =  preamble + "Players: " + Arrays.toString(players) + " Werewolves: " +
                Arrays.toString(werewolves) + instruction;
        conversationHistory.addUserMessage(prompt);
        return prompt;
    }

    public String generatePlayerKilledPrompt(String playerKilled) {
        String preamble = "One night has passed and this player has been killed: ";
        String instruction = ". Describe in detail how they were killed. Reveal the victim's name only at the end of " +
                "the story.";
        String prompt = conversationHistory.getConversationHistory() + " User: " + preamble + playerKilled + instruction;
        // We don't want to add conversationHistory to the conversation history again, so we cannot just add prompt
        conversationHistory.addUserMessage(preamble + playerKilled + instruction);
        return prompt;
    }

    public String generatePlayerVotedOutPrompt(String playerVotedOut, String playerRole) {
        String preamble = "The villagers have decided to vote out: ";
        String instruction = ". Continue the story, describing the player's last words, and reveal their true identity" +
                " at the end. Remember that they are a ";

        String prompt = conversationHistory.getConversationHistory() + " User: " + preamble + playerVotedOut + instruction + playerRole;
        // We don't want to add conversationHistory to the conversation history again, so we cannot just add prompt
        conversationHistory.addUserMessage(preamble + playerVotedOut + instruction + playerRole);
        return prompt;
    }

    public ConversationHistory getConversationHistory() {
        return conversationHistory;
    }
}

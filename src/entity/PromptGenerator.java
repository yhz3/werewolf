package entity;

import java.util.Arrays;
import java.util.HashMap;

public class PromptGenerator {
    private final ConversationHistory conversationHistory;
    public PromptGenerator(ConversationHistory conversationHistory) {
        this.conversationHistory = conversationHistory;
        // Conversation history needs to be passed in as an argument since for each use case, we need to pull data
        // from the database and rebuild the PromptGenerator entity.
    }

    public String generateIntroPrompt(String[] players) {
        String preamble = "I am playing a game of Werewolf (aka Mafia) with my friends. I want you to be the narrator " +
                "of the game. There are werewolves and villagers. Here is everyone: ";
        String instruction = " Give a creative intro for me to read out to the players. " +
                "No one has been killed yet.";
        String prompt =  preamble + Arrays.toString(players) + instruction;
        conversationHistory.addUserMessage(prompt);
        return prompt;
    }

    public String generatePlayerKilledPrompt(String playerKilled) {
        String preamble = "One night has passed and this player has been killed: ";
        String instruction = ". Describe in detail how they were killed. Reveal the victim's name only at the end of " +
                "the story. Keep it under 250 words.";
        String prompt = conversationHistory.getConversationHistory() + " User: " + preamble + playerKilled + instruction;
        // We don't want to add conversationHistory to the conversation history again, so we cannot just add prompt
        conversationHistory.addUserMessage(preamble + playerKilled + instruction);
        return prompt;
    }

    public String generatePlayerVotedOutPrompt(String playerVotedOut, String playerRole) {
        String preamble = "The villagers have decided to vote out: ";
        String instruction = ". Continue the story, describing the player's last words, and reveal their true identity" +
                " at the end. Keep it under 250 words. Importantly, this player is a ";

        String prompt = conversationHistory.getConversationHistory() + " User: " + preamble + playerVotedOut + instruction + playerRole;
        // We don't want to add conversationHistory to the conversation history again, so we cannot just add prompt
        conversationHistory.addUserMessage(preamble + playerVotedOut + instruction + playerRole);
        return prompt;
    }

    public String generateVillagerWinPrompt(HashMap<String, Player> players){
        String playerString = players.keySet().toString();
        String preamble = "The villagers have won the game: ";
        String instruction = ". Finish the story by describing how the villagers celebrate their win. Keep it under 250 words.";
        String prompt = conversationHistory.getConversationHistory() + preamble + playerString + instruction;
        conversationHistory.addUserMessage(preamble +  playerString + instruction);
        return prompt;
    }

    public String generateWerewolfWinPrompt(HashMap<String, Player> werewolves){
        String werewolfString = werewolves.keySet().toString();
        String preamble = "The werewolves have won the game: ";
        String instruction = ". Finish the story by describing how the werewolves celebrate their win. Keep it under 250 words.";
        String prompt = conversationHistory.getConversationHistory() + preamble + werewolfString + instruction;
        conversationHistory.addUserMessage(preamble +  werewolfString + instruction);
        return prompt;
    }

    public ConversationHistory getConversationHistory() {
        return conversationHistory;
    }
}

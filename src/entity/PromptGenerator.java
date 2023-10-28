package entity;

import java.util.Arrays;

public class PromptGenerator {
    ConversationHistory conversationHistory;
    String[] players;
    String[] werewolves;

    public PromptGenerator(String[] players, String[] werewolves) {
        this.players = players;
        this.werewolves = werewolves;
        this.conversationHistory = new ConversationHistory();
    }

    public String generateIntroPrompt() {
        String preamble = "I am playing a game of Werewolf (aka Mafia) with my friends. I want you to be the narrator " +
                "of the game. There are werewolves and villagers, and here is the list of players and werewolves. " +
                "Players who are not werewolves are villagers.\n";
        String instruction = "\nCome up with a setting, and give a creative intro for me to read out to the players. " +
                "In the intro, describe the players' character. Do not reveal the players' identities. No one has " +
                "been killed yet.";
        String prompt =  preamble + "Players: " + Arrays.toString(this.players) + "\n" + "Werewolves: " +
                Arrays.toString(this.werewolves) + instruction;
        conversationHistory.addUserMessage(prompt);
        return prompt;
    }

    public String generatePlayerKilledPrompt(String playerKilled) {
        String preamble = "One night has passed and this player has been killed: ";
        String instruction = ". Describe in detail how they were killed. Reveal the victim's name only at the end of " +
                "the story. Hidden in the story, include a misleading hint of who the murderer " +
                "might be. Do not point out the hint explicitly, it should be buried deep inside like a mystery story.";
        String prompt = conversationHistory.getConversationHistory() + preamble + playerKilled + instruction;
        conversationHistory.addUserMessage(prompt);
        return prompt;
    }

    public String generatePlayerVotedOutPrompt(String playerVotedOut, String playerRole) {
        String preamble = "The villagers have decided to vote out: ";
        String instruction = ". Continue the story, describing the player's last words, and reveal their true identity" +
                " at the end. Remember that they are a ";

        String prompt = conversationHistory.getConversationHistory() + preamble + playerVotedOut + instruction + playerRole;
        conversationHistory.addUserMessage(prompt);
        return prompt;
    }
}

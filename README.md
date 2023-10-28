Project Specification for Group #167

USE CASE IDEAS:
- New game
  - Input Data: Player names
  - Create Player Entities (Assign Roles Randomly)
  - Create Game Object
  - Add Players to Game
  - Output Data: Game Object
- Begin Intro
  - Input Data: (n/a for now, but might be needed in future to adjust gpt story settings etc.), Game Object
  - Create PromptGenerator Object using constructor
  - Call PromptGenerator.generateIntroPrompt
  - Feed Prompt to ChatAPIAccessInterface
  - Save GPT Ouput to PromptGenerator
  - Output Data: GPT Output, Game Object, PromptGeneratorObject
- Kill player (in night)
  - Input Data: Player name, Game Object, PromptGeneratorObject
  - Call PromptGenerator.generatePlayerKilledPrompt
  - Feed Prompt to ChatAPIAccessInterface
  - Save GPT Output to PromptGenerator
  - Check for win condition
  - Change Game.day to true
  - Output Data: GPT Output, Game Object, PromptGeneratorObject
- Vote out player (in day)
  - Input Data: Player name, Game Object, PromptGeneratorObject
  - Call PromptGenerator.generatePlayerVotedOutPrompt
  - Feed Prompt to ChatAPIAccessInterface
  - Save GPT Output to PromptGenerator
  - Check for win condition
  - Change Game.day to false
  - Output Data: GPT Output, Game Object, PromptGeneratorObject


Team Name: Los Pollos Hermanos
Domain: Werewolf Game with AI Narrator
The application will allow people to play a game of Werewolf/Mafia offline. On top of a functional interface for playing Werewolf on one computer, This interface will assist the typical narrator role in the Mafia game. It will take user input and game state data to give prompts to Chat-GPT to narrate the story.
The game of "Werewolf" is a social deduction party game where players are secretly assigned roles by the gamemaster as either werewolves or villagers. Players are secretly assigned roles of whether you are a villager or a werewolf. If there are more than one werewolf, they know who else are werewolves. During the "Night Phase", werewolves covertly choose a player to eliminate while all players' eyes are closed. The gamemaster gives an improvised story to dramatically reveal who was killed. During the "Day Phase", players discuss and debate the identity of the werewolves and vote to eliminate a suspected player. The game alternates between these phases until either all werewolves are eliminated, leading to a villagers' win, or the number of werewolves equals or exceeds the number of villagers, resulting in a werewolf victory. The game thrives on deception, strategy, and intuition.
Software Specification:
Allow the gamemaster to choose how many players are in the game.
Allow the gamemaster to enter the names of players
Randomly select roles for players
4-5: 1 Werewolf
6+: 2 Werewolves
Allow the user to view their roles discreetly (Villager or Werewolf)
Anytime during the game
Werewolves are shown the names of the other werewolves
Input which player was murdered
Reveals role if they are voted out
Input which player was voted out
Reveals role if they are voted out
Display the ChatGPT story for a intro and subsequent rounds when a villager is killed
Taking in game state info, names, etc. as inputs
Give ChatGPT a fixed theme, background, and location
End the game when there are insufficient number of Villages/Werewolves are left
Display which team won, and the roles of the players

Other features (not part of MVP) (In order of priority)
Personalize Chat-GPT story
Change length and tone of story
Add details to Chat-GPT story (location, background, theme etc.)
Improved gameplay
More roles (Guardian, seers etc.)
Keep track of scores (how many times each user has won)
Skip and/or abstain voting
Restart game with same players or new players, same or new story background
How to play tutorial, and instructions of rules and roles
Computer to computer interaction (online)
Voting system
Voting system for villagers to vote out werewolves
Voting system for werewolves to choose a villager to kill
Somehow entirely replace need for gamemaster
User Stories:
Team: I am the gamemaster. Someone in the game forgets their role in the middle of the game. I use the application to reveal their role and show it to them on the screen. The game continues where it left off.
Emily: I gathered two of my friends to play werewolf. We open the app and see a screen asking us to enter the number of players. I enter ‘3’ and get a notification, telling me that we do not have enough players. It tells me that we need at least 4 players to play the game.
Daniyaal: I gather five friends to play Werewolf. After the player selection screens, I discover that I am a werewolf. Since we have 6 players in total, I can see who the other werewolf is on the screen.
Kevin: I’m a gamemaster. The werewolves have selected one player to murder. I enter the player who has been murdered. The computer outputs the next part of the story to be displayed based on the events that happened for the gamemaster to read.
Ethan: There is only one werewolf left and the villagers are now deciding who to vote off. After the villagers have all chosen who to vote out, I enter the voted person’s name in. It is revealed they are a werewolf, and since there are no more werewolves, the game ends, indicating the villagers won.
Proposed Entities for the Domain:
Game State
ArrayList<Werewolf> werewolf_list
ArrayList<Villager> villager_list
ArrayList<T> last_actions
Boolean daytime
String context

Player
bool alive
string name

Werewolf (Player)
bool alive
string name

Villager (Player)
bool alive
string name

Scheduled Meeting Times + Mode of Communication:
Meeting time outside of lab: Saturdays 3:00pm
Mode of Communication: Discord server


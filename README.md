# Werewolf AI Narrative Assistant

## Project Description

### How does Werewolf work?
The game of Werewolf is a social party game. A minimum of 4 players comes together and each will be randomly assigned the role of werewolf or villager (players can choose to include certain special roles, such as girl or magician). The game alternates between day at night. At night the werewolves come to live and collectively decide which villager they want to „kill“. During the day, it will be revealed, which villager got „killed“ and then all players have to decide on one player they want to vote out. Players will need to come up with creative reasoning on why they should not be the ones to be voted out. The goal of the game is for one group (either villagers or werewolves) to eliminate all the players of the other group. The whole game is organized by a narrator, whose job it is to keep track of players role, let players now when the daytime changes between day and night and to make the game more interesting by coming up with stories to set a scene for the game and explain what happened to players that got „killed“ or voted out.

### Why did we want to create this app?
The narration of Werewolf is an important part of the game. Players need to be invested in the story to make the game feel real and for it to be more fun. However, we have noticed that when a human comes up with narrations, those usually have a medieval setting and are stale and repetitive. Thus, our goal was to create a game that outputs a novel story each time and allows the game to take place in different settings.

### Why use this application?
This application was created as an assistant to the narrator of a Werewolf game. The narrator will be able to input the names of the player and the application will randomly assign roles to the players and displaying those on the screen. The game will automatically switch between day and night and provide corresponding views. During the night, the narrator will be able to input the name of the villager that got „killed“ by the werewolves and during the day they will be able to input the name of the player that got voted out. If the player inputted does not exist or is not an alive player anymore, the application will let the narrator know and ask them to input a different name. If a name has been inputted successfully, the game will display an interesting and player-specific story of how the player died. The application will automatically recognize when one of the teams has won and end the game, while displaying a story explaining how the game came to and end. Lastly, the narrator can choose to start a new game, whereas the names of the players are already inputted to allow restarting the game more smoothly.

### Technologies used:

### Features to implement in the future:
- add special roles, such as Magician or Girl
- allow users to view their role during the game (make the code more accessible)
- improve the UI
- allow users to decide on a setting for the ChatGPT narration and personalize the stories


## How to install and run the project:

Clone the project and open it in an IDE. 


## How to use the project:

While playing a game of Werewolf, use this project as an assistant to the narrator. Before starting, enter the names of all of the players in the first View and click new Game. The application will show the roles assigned to each player, whereas it is the narrators task to let the players know what their roles are. Afterwards, the game will supply a story to begin the game, which has to be read out to the players.
The game will alternate between day and night. During the night the narrator will have to let the werewolves decide which villager they want to "kill" and enter the selected villagers name into the view. During the day, the narrator will let all players discuss which player to vote out. After a player has been selected, the narrator again will enter the person's name into the view. Everytime a villager is killed or a player is voted out the application will supply a story that has to be read out to the players.
Once there are no villagers or werewolves left the game will end automatically and show a final story to be read out by the narrator. The narrator can then choose whether to end the application or start a new game.

### User Stories:
Team: I am the gamemaster. Someone in the game forgets their role in the middle of the game. I use the application to reveal their role and show it to them on the screen. The game continues where it left off.

Emily: I gathered two of my friends to play werewolf. We open the app and see a screen asking us to enter the number of players. I enter ‘3’ and get a notification, telling me that we do not have enough players. It tells me that we need at least 4 players to play the game.

Daniyaal: I gather five friends to play Werewolf. After the player selection screens, I discover that I am a werewolf. Since we have 6 players in total, I can see who the other werewolf is on the screen.

Kevin: I’m a gamemaster. The werewolves have selected one player to murder. I enter the player who has been murdered. The computer outputs the next part of the story to be displayed based on the events that happened for the gamemaster to read.

Ethan: There is only one werewolf left and the villagers are now deciding who to vote off. After the villagers have all chosen who to vote out, I enter the voted person’s name in. It is revealed they are a werewolf, and since there are no more werewolves, the game ends, indicating the villagers won.


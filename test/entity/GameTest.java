package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void init() {
        game = new Game();
        Player Reiner = new Werewolf("Reiner");
        Player Bertolt = new Werewolf("Bertolt");
        Player Eren = new Villager("Eren");
        Player Mikasa = new Villager("Mikasa");
        game.addPlayer(Reiner);
        game.addPlayer(Bertolt);
        game.addPlayer(Eren);
        game.addPlayer(Mikasa);
    }

    @Test
    void changeGameState() {
        game.changeGameState();
        assertTrue(game.isDay());
    }

    @Test
    void killVillager() {
        game.killPlayer("Eren");
        assertFalse(game.getAliveVillagers().containsKey("Eren"));
        assertTrue(game.getDeadVillagers().containsKey("Eren"));
        assertTrue(game.getAliveVillagers().containsKey("Mikasa"));
        assertFalse(game.getDeadVillagers().containsKey("Mikasa"));
        assertEquals(game.getAliveWerewolves().keySet().size(), 2);
        assertEquals(game.getDeadWerewolves().keySet().size(), 0);
    }

    @Test
    void killWerewolf() {
        game.killPlayer("Reiner");
        assertFalse(game.getAliveWerewolves().containsKey("Reiner"));
        assertTrue(game.getDeadWerewolves().containsKey("Reiner"));
        assertTrue(game.getAliveWerewolves().containsKey("Bertolt"));
        assertFalse(game.getDeadWerewolves().containsKey("Bertolt"));
        assertEquals(game.getAliveVillagers().keySet().size(), 2);
        assertEquals(game.getDeadVillagers().keySet().size(), 0);
    }

    @Test
    void checkGameOver() {
        assertFalse(game.checkGameOver());
        game.killPlayer("Reiner");
        assertFalse(game.checkGameOver());
        game.killPlayer("Eren");
        assertFalse(game.checkGameOver());
        game.killPlayer("Bertolt");
        assertTrue(game.checkGameOver());
    }

    @Test
    void checkVillagerWin() {
        assertFalse(game.checkVillagerWin());
        assertFalse(game.checkGameOver());
        game.killPlayer("Eren");
        game.killPlayer("Mikasa");
        assertFalse(game.checkVillagerWin());
        assertTrue(game.checkGameOver());
    }

    @Test
    void getPlayerNames() {
        String[] players = {"Bertolt", "Eren", "Mikasa", "Reiner"};
        String players_string = Arrays.toString(players);
        System.out.println(Arrays.toString(game.getPlayerNames()));
        assertEquals(players_string, Arrays.toString(game.getPlayerNames()));
    }
}
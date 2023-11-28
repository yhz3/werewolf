package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player werewolf;

    @BeforeEach
    void init() {
        werewolf = new Werewolf("Sukuna");
    }

    @Test
    void setName() {
        assertEquals("Sukuna", werewolf.getName());
        werewolf.setName("Jogoat");
        assertEquals("Jogoat", werewolf.getName());
    }

    @Test
    void setAlive() {
        assertTrue(werewolf.getAlive());
        werewolf.setAlive(false);
        assertFalse(werewolf.getAlive());
    }

    @Test
    void getRole() {
        assertEquals(werewolf.getRole(), "werewolf");
    }

    @Test
    void setRole() {
        werewolf.setRole("Special Grade Curse");
        assertEquals("Special Grade Curse", werewolf.getRole());
    }
}
package server;

import card.BasicCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void receiveCommand() {
    }

    @Test
    void rollDice() {
    }

    @Test
    void dealCards() {
    }

    @Test
    void dealCard() {
    }

    @Test
    void sendCard() {
    }

    @Test
    void placeCard() {
    }

    @Test
    void attackEnemyPlayer() {
    }

    @Test
    void attackEnemyCreature() {
    }

    @Test
    void healPlayer() {
    }

    @Test
    void healCreature() {
    }

    @Test
    void checkPlayerAlive() {
        Server server = new Server();
        Player p = new Player(1, "Gary",3, null);
        assertTrue(server.checkPlayerAlive(p));
        p.setHealth(0);
        assertFalse(server.checkPlayerAlive(p));
        p.setHealth(5);
        assertTrue(server.checkPlayerAlive(p));
    }

    @Test
    void checkCreatureAlive() {
    }

    @Test
    void moveToGraveyard() {
    }

    @Test
    void endTurn() {
    }

    @Test
    void quitGame() {
    }
}
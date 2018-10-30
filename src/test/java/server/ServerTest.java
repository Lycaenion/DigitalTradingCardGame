package server;

import Game.Game;
import card.BasicCard;
import card.BasicCreatureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.DummyDB;

import java.util.ArrayList;
import java.util.List;
import card.BasicCreatureCard;
import player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerTest {
    
    @BeforeEach
    void setUp() {
    }

    @Test
    void receiveCommand() {
    }

    @Test
    void rollDice() {
        for(int i = 0; i <= 10000; ++i) {
            assertThat(Server.getInstance().rollDice(1,6)).isBetween(1, 6);
        }
    }

    @Test
    void dealCards() {
        /*List<BasicCard> playerB = new ArrayList<>(Arrays.asList(
                new BasicCreatureCard(1, "Marshmallow", "White soft treat", "does not exist yet", 3, 1, 2),
                new BasicCreatureCard(2, "Plopp","Chocolate with gooey caramel center", "does not excist", 2, 2, 1),
                new BasicCreatureCard(3, "Smash", "Crispy chocolate treat", "does not exist yet", 1, 5, 1),
                new BasicCreatureCard(4, "Crazy face", "Sour chewy candy", "does not exist yet", 4, 1, 1),
                new BasicCreatureCard(5, "Djungelvrål", "Licorice candy that makes you scream", "does not exist yet", 2, 2, 2)));
        
        Server.getInstance().setPlayerADeck(playerB);
        Server.getInstance().dealCard();
        Server.getInstance().dealCard();
        Server.getInstance().dealCard();
        Server.getInstance().dealCard();
        Server.getInstance().dealCard();
        
        

        verify(Server.getInstance()., times(5)).dealCard();*/
    }

    @Test
    void dealCard() {
        List<BasicCard> deckA = new ArrayList<BasicCard>();

        deckA.add(0, new BasicCard(1,"candy","asd","url"));
        deckA.add(1, new BasicCard(2,"candy","asd","url"));

        deckA.remove(1);
        assertEquals(1,deckA.get(0).id);
        assertEquals(1,deckA.size());

        List<BasicCard> deckB = new ArrayList<BasicCard>();

        deckB.add(0, new BasicCard(1,"candy","asd","url"));
        deckB.add(1, new BasicCard(2,"candy","asd","url"));

        deckB.remove(0);

        assertEquals(1,deckB.size());
        deckB.remove(0);

        assertEquals(0,deckB.size());

    }

    @Test
    void sendCard() {
    }

    @Test
    void placeCard() {
        List<BasicCard> playerA = new ArrayList<>(Arrays.asList(
                new BasicCreatureCard(1, "Marshmallow", "White soft treat", "does not exist yet", 3, 1, 2),
                new BasicCreatureCard(2, "Plopp","Chocolate with gooey caramel center", "does not excist", 2, 2, 1),
                new BasicCreatureCard(3, "Smash", "Crispy chocolate treat", "does not exist yet", 1, 5, 1),
                new BasicCreatureCard(4, "Crazy face", "Sour chewy candy", "does not exist yet", 4, 1, 1),
                new BasicCreatureCard(5, "Djungelvrål", "Licorice candy that makes you scream", "does not exist yet", 2, 2, 2)));
        List<BasicCard> playerB = new ArrayList<>(Arrays.asList(
                new BasicCreatureCard(1, "Marshmallow", "White soft treat", "does not exist yet", 3, 1, 2),
                new BasicCreatureCard(2, "Plopp","Chocolate with gooey caramel center", "does not excist", 2, 2, 1),
                new BasicCreatureCard(3, "Smash", "Crispy chocolate treat", "does not exist yet", 1, 5, 1),
                new BasicCreatureCard(4, "Crazy face", "Sour chewy candy", "does not exist yet", 4, 1, 1),
                new BasicCreatureCard(5, "Djungelvrål", "Licorice candy that makes you scream", "does not exist yet", 2, 2, 2)));
        Server.getInstance().players[0].setHand(playerA);
        Server.getInstance().players[1].setHand(playerB);
        Server.getInstance().setTurn(0);
        assertEquals(5, Server.getInstance().players[0].getHand().size());
        assertEquals(0, Server.getInstance().getPlayerATableCards().size());
        Server.getInstance().placeCard(4);
        assertEquals(4, Server.getInstance().players[0].getHand().size());
        assertEquals(1, Server.getInstance().getPlayerATableCards().size());
        
        assertEquals(5, Server.getInstance().players[1].getHand().size());
        Server.getInstance().setTurn(1);
        assertEquals(0, Server.getInstance().getPlayerBTableCards().size());
        Server.getInstance().placeCard(4);
        assertEquals(4, Server.getInstance().players[1].getHand().size());
        assertEquals(1, Server.getInstance().getPlayerBTableCards().size());
    }

    @Test
    void attackEnemyPlayer() {

        //Player A attacks player B
        assertEquals(0, Server.getInstance().getPlayerBTableCards().size());
        assertTrue(Server.getInstance().attackEnemyPlayer());

        Server.getInstance().getPlayerBTableCards().add( new BasicCreatureCard(6, "Nick's", "Sugar-free candy", "does not exist yet", 1, 3, 3));
        assertFalse(Server.getInstance().attackEnemyPlayer());

        assertEquals(8, Server.getInstance().players[1].getHealth());
        assertEquals(8, Game.getInstance().getPlayerB().getHealth());

        Server.getInstance().getPlayerBTableCards().clear();
        assertTrue(Server.getInstance().attackEnemyPlayer());

        assertEquals(6, Server.getInstance().players[1].getHealth());
        assertEquals(6, Game.getInstance().getPlayerB().getHealth());



        assertTrue(Server.getInstance().checkPlayerAlive(Server.getInstance().players[1]));

        Server.getInstance().attackEnemyPlayer();
        Server.getInstance().attackEnemyPlayer();
        Server.getInstance().attackEnemyPlayer();
        assertFalse(Server.getInstance().checkPlayerAlive(Server.getInstance().players[1]));
        Server.getInstance().setTurn(1);

        //Player B attacks player A

        assertEquals(0, Server.getInstance().getPlayerATableCards().size());
        assertTrue(Server.getInstance().attackEnemyPlayer());

        Server.getInstance().getPlayerATableCards().add( new BasicCreatureCard(6, "Nick's", "Sugar-free candy", "does not exist yet", 1, 3, 3));
        assertFalse(Server.getInstance().attackEnemyPlayer());

        assertEquals(9, Server.getInstance().players[0].getHealth());
        assertEquals(9, Game.getInstance().getPlayerA().getHealth());

        Server.getInstance().getPlayerATableCards().clear();
        assertTrue(Server.getInstance().attackEnemyPlayer());

        assertEquals(8, Server.getInstance().players[0].getHealth());
        assertEquals(8, Game.getInstance().getPlayerA().getHealth());



        assertTrue(Server.getInstance().checkPlayerAlive(Server.getInstance().players[0]));
        for(int i = 0; i < 8; i++){
            Server.getInstance().attackEnemyPlayer();
        }
       
        assertFalse(Server.getInstance().checkPlayerAlive(Server.getInstance().players[0]));



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
        Player p = new Player(1, "Gary",3, null);
        assertTrue(Server.getInstance().checkPlayerAlive(p));
        p.setHealth(0);
        assertFalse(Server.getInstance().checkPlayerAlive(p));
        p.setHealth(5);
        assertTrue(Server.getInstance().checkPlayerAlive(p));
    }

    @Test
    void checkCreatureAlive() {
        BasicCreatureCard creature = new BasicCreatureCard(2, "abc", "abc", "abc", 5, 2, 3);
        assertTrue(Server.getInstance().checkCreatureAlive(creature));
        creature.setHealth(0);
        assertFalse(Server.getInstance().checkCreatureAlive(creature));
        creature.setHealth(8);
        assertTrue(Server.getInstance().checkCreatureAlive(creature));
        BasicCreatureCard creature2 = new BasicCreatureCard(3, "abc", "abc", "abc", 0, 2, 1);
        assertFalse(Server.getInstance().checkCreatureAlive(creature2));
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
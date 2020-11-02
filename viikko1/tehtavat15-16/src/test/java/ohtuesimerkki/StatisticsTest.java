/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hilla
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54)); //99
            players.add(new Player("Kurri",   "EDM", 37, 53)); //90
            players.add(new Player("Yzerman", "DET", 42, 56)); //98
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void searchFindsPlayer() {
        Player pelaaja = stats.search("Semenko");
        assertEquals("Semenko", pelaaja.getName());
        assertEquals("EDM", pelaaja.getTeam());
        assertEquals(4, pelaaja.getGoals());
        assertEquals(12, pelaaja.getAssists());
    }
    
    @Test
    public void searchReturnsNull() {
        assertEquals(null, stats.search("Korpinen"));
    }
    
    @Test
    public void teamIsFormedCorrectly() {
        List<Player> testijoukkue = stats.team("PIT");
        boolean found = false;
        for (Player pelaaja : testijoukkue) {
            if (pelaaja.getName().equals("Lemieux"));
            found = true;
        }
        assertEquals(true, found);
    }
    
    @Test 
    public void topScorersAreFound() {
        List<Player> parhaat = stats.topScorers(3);
        assertEquals(3, parhaat.size());
        assertEquals("Gretzky", parhaat.get(0).getName());
        assertEquals("Lemieux", parhaat.get(1).getName());
        assertEquals("Yzerman", parhaat.get(2).getName());
        
    }
}

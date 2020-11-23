/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Hilla
 */
public class KauppaTest {

    Pankki pankki;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
    }
    
    @Test
    public void poistaKoristaPoistaaJaPalauttaaVarastoon() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
  
        k.aloitaAsiointi();
        k.lisaaKoriin(1);    
        k.poistaKorista(1);
        verify(varasto).palautaVarastoon(new Tuote(1, "maito", 5));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        //Kauppa k = new Kauppa();

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei v‰litetty kutsussa k‰ytetyist‰ parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        // luodaan ensin mock-oliot
        //Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        //Kauppa k = new Kauppa();

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345"); //kumman tilinumero? 

        //kaupan tili "33333-44455"
        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        //oikeilla parametreilla nimi, viite, tililt‰, tilille, summa
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);

    }

    @Test
    public void ostetaanKaksiEriTuotettaJoitaOn() {
        /*
        aloitetaan asiointi, koriin lis‰t‰‰n kaksi eri tuotetta, joita varastossa on 
        ja suoritetaan ostos, varmista ett‰ kutsutaan pankin metodia tilisiirto 
        oikealla asiakkaalla, tilinumerolla ja summalla
         */
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leip‰", 7));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        //Kauppa k = new Kauppa();

        // tehd‰‰n ostokset
        k.aloitaAsiointi();

        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        //kaupan tili "33333-44455"
        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        //oikeilla parametreilla nimi, viite, tililt‰, tilille, summa
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 12);
    }

    @Test
    public void ostetaanKaksiSamaaTuotettaJoitaOn() {
        /*
        aloitetaan asiointi, koriin lis‰t‰‰n kaksi samaa tuotetta, 
        jota on varastossa tarpeeksi ja suoritetaan ostos, varmista ett‰ kutsutaan 
        pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla    
         */
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        //Kauppa k = new Kauppa();

        // tehd‰‰n ostokset
        k.aloitaAsiointi();

        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        //kaupan tili "33333-44455"
        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        //oikeilla parametreilla nimi, viite, tililt‰, tilille, summa
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);

    }

    @Test
    public void ostetaanTuoteJokaOnLoppuJaTuoteJotaOn() {
        /*
    aloitetaan asiointi, koriin lis‰t‰‰n tuote, jota on varastossa tarpeeksi 
    ja tuote joka on loppu ja suoritetaan ostos, varmista 
    tt‰ kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla    
         */

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leip‰", 7));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);
        //Kauppa k = new Kauppa();

        // tehd‰‰n ostokset
        k.aloitaAsiointi();

        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        //kaupan tili "33333-44455"
        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        //oikeilla parametreilla nimi, viite, tililt‰, tilille, summa
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);

    }

    @Test
    public void asioinninAlkaessaTiedotTyhjentyvat() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        Kauppa k = new Kauppa(varasto, pankki, viite);       
        
        k.aloitaAsiointi();
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
          
        when(viite.uusi()).thenReturn(43);
        k.aloitaAsiointi();
        
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leip‰", 7));
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto("pekka", 43, "12345", "33333-44455", 7);
         
    }
    
    @Test
    public void uusiViiteUudelleOstokselle() {
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        //when(viite.uusi()).thenReturn(42);
        
        when(viite.uusi())
            .thenReturn(1)
            .thenReturn(2);            
        
        Kauppa k = new Kauppa(varasto, pankki, viite);       
        
        k.aloitaAsiointi();
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());
        //verify(pankki).maksa(anyString(), anyInt(), eq(1));
        
        k.aloitaAsiointi();
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leip‰", 7));
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());
    }

    /*
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }*/
}

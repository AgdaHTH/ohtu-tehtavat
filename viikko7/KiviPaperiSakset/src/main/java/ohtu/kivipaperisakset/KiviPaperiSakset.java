/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author Hilla
 */
public abstract class KiviPaperiSakset {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    // t‰m‰ on ns template metodi
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        
        String ekanSiirto = ensimmaisenSiirto();
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = toisenSiirto();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
           // ...
           
           tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.println("Ensimm√§isen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            
            System.out.println("Toisen pelaajan siirto: ");           
            tokanSiirto = toisenSiirto();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected String ensimmaisenSiirto() {
        System.out.print("Ensimm‰isen pelaajan siirto: ");
        return scanner.nextLine();
    }

    // t‰m‰ on abstrakti metodi sill‰ sen toteutus vaihtelee eri pelityypeiss‰
    abstract protected String toisenSiirto();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    public static KiviPaperiSakset luoKaksinPeli () {
        return new KPSPelaajaVsPelaaja();
    }
    
    public static KiviPaperiSakset luoYksinPeli () {
        return new KPSTekoaly();
    }
    
    public static KiviPaperiSakset luoPahaYksinPeli () {
        return new KPSParempiTekoaly();
    }
    
}

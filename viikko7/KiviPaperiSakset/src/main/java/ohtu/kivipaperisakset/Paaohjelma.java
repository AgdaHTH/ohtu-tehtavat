package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);
    //private KiviPaperiSakset;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");               
                KiviPaperiSakset kaksinpeli = KiviPaperiSakset.luoKaksinPeli();                
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KiviPaperiSakset yksinpeli = KiviPaperiSakset.luoYksinPeli();        
                yksinpeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KiviPaperiSakset pahaYksinpeli = KiviPaperiSakset.luoPahaYksinPeli();
                pahaYksinpeli.pelaa();
            } else {
                break;
            }

        }

    }
}

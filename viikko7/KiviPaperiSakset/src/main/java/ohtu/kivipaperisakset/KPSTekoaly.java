package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset{

    private static final Scanner scanner = new Scanner(System.in);
    Tekoaly tekoaly = new Tekoaly();
    
    @Override
    protected String toisenSiirto() {
        return tekoaly.annaSiirto();
    }

}
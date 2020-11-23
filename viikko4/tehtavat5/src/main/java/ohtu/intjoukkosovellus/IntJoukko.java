package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        taulukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (parametriEiNegatiivinen(kapasiteetti)) {
            taulukko = new int[kapasiteetti];
            alkioidenLkm = 0;
            this.kasvatuskoko = OLETUSKASVATUS;
        }
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (parametriEiNegatiivinen(kapasiteetti) && parametriEiNegatiivinen(kasvatuskoko)) {
            taulukko = new int[kapasiteetti];
            alkioidenLkm = 0;
            this.kasvatuskoko = kasvatuskoko;
        }
    }

    public boolean parametriEiNegatiivinen(int parametri) {
        if (parametri < 0) {
            return false;
        }
        return true;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            taulukko[0] = luku;
            alkioidenLkm++;
            return true;
        } 
        if (!kuuluu(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if  (alkioidenLkm == taulukko.length){           
                int [] vanhaTaulukko = taulukko;
                taulukko = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(vanhaTaulukko, taulukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        boolean kuuluu = false;
        for (int i = 0; i < alkioidenLkm; i++) { //oli alkioidenLkm
            if (taulukko[i] == luku) {
                kuuluu = true;
            }
        }
        return kuuluu;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                taulukko[kohta] = 0;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = taulukko[j];
                taulukko[j] = taulukko[j + 1];
                taulukko[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + taulukko[0] + "}";
        } else {
            String tuloste = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuloste += taulukko[i];
                tuloste += ", ";
            }
            tuloste += taulukko[alkioidenLkm - 1];
            tuloste += "}";
            return tuloste;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko yhdiste = new IntJoukko();
        for (int i = 0; i < joukkoA.alkioidenLkm; i++) {
            yhdiste.lisaa(joukkoA.taulukko[i]);
        }
        for (int i = 0; i < joukkoB.alkioidenLkm; i++) {
            yhdiste.lisaa(joukkoB.taulukko[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko joukkoA, IntJoukko joukkoB) {       
        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < joukkoB.taulukko.length; i++) {
            if (joukkoA.kuuluu(joukkoB.taulukko[i])) {
                leikkaus.lisaa(joukkoB.taulukko[i]);
            }
        }   
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko joukkoA, IntJoukko joukkoB) {
        IntJoukko erotus = joukkoA;
        for (int i = 0; i < joukkoA.taulukko.length; i++){
            erotus.poista(joukkoB.taulukko[i]);
        }
        return erotus;
    }

}

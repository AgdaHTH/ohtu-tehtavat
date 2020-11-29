/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Hilla
 */
public class Erotus extends Komento {
    
    int edellinen;
    
    public Erotus (TextField tuloskentta, TextField syotekentta, 
            Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        edellinen = sovellus.tulos();
        sovellus.nollaa(); 
        
        int arvo1 = Integer.parseInt(tuloskentta.getText());
        int arvo2 = Integer.parseInt(syotekentta.getText());
        sovellus.plus(arvo1);
        sovellus.miinus(arvo2);
        tuloskentta.setText("" + sovellus.tulos());
        syotekentta.setText("");
        
        undo.disableProperty().set(false);  
        nollaa.disableProperty().set(false);
    }
    
    @Override
    public void peru() {
        tuloskentta.setText("" + edellinen);
    }
}

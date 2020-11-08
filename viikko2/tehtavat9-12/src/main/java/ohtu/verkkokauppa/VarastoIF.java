/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

/**
 *
 * @author Hilla
 */
public interface VarastoIF {

    //@Override
    Tuote haeTuote(int id);

    //@Override
    void otaVarastosta(Tuote t);

    //@Override
    void palautaVarastoon(Tuote t);

    //@Override
    int saldo(int id);
    
}

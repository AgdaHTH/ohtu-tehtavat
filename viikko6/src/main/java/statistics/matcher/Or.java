/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author Hilla
 */
public class Or implements Matcher {
    //Or, joka on tosi silloin jos ainakin yksi 
    //sen parametrina saamista ehdoista on tosi.
    
    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    
    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            //palauttaa true
            //heti jos yksi matcher palauttaa truen
            if (matcher.matches(p)) {
                return true;
            }
        }

        return false;
    }         
    
}

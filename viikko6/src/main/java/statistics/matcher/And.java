package statistics.matcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import statistics.Player;

public class And implements Matcher {

    private Matcher[] matchers;

    public And(Matcher... matchers) {
        this.matchers = matchers;
    }
    
    public And(List<Matcher> matchersList) {
        Matcher[] newMatchers = new Matcher[matchersList.size()];
        this.matchers = matchersList.toArray(newMatchers);
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            if (!matcher.matches(p)) {
                return false;
            }
        }

        return true;
    }
}

package GraphFundamentals.GraphFundamentals;

import java.util.ArrayList;
import java.util.List;

public class Tragarz extends Plaszczak {
    // 1 (true) - ręce z przodu | 0 (false) - ręce z tylu
    private boolean rodzajTragarza;
    private List<Integer> nieLubi;

    public Tragarz(boolean rodzajTragarza) {
        super();
        this.rodzajTragarza = rodzajTragarza;
        this.nieLubi = new ArrayList<>();
    }

    public boolean getRodzajTragarza() {
        return rodzajTragarza;
    }

    public String printRodzajTragarza() {
        if(rodzajTragarza) return "przód";
        else return "tył";
    }

    public void dodajNieLubi(Tragarz tragarz) {
        if (!nieLubi.contains(tragarz.getId())) {
            nieLubi.add(tragarz.getId());
        }
    }

    public boolean sprawdzNieLubi(Tragarz tragarz) {
        return nieLubi.contains(tragarz.getId());
    }

    @Override
    public String toString() {
        return "Tragarz ID: " + getId() + " | Ręce: " + printRodzajTragarza();
    }
}

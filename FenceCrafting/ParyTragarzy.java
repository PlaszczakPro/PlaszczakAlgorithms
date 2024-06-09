package FenceCrafting;

import GraphFundamentals.GraphFundamentals.Tragarz;

import java.util.ArrayList;
import java.util.List;

public class ParyTragarzy {
    private List<Para> pary;

    public ParyTragarzy(List<Tragarz> tragarze) {
        this.pary = new ArrayList<>();
        polaczWParach(tragarze);
    }

    private void polaczWParach(List<Tragarz> tragarze) {
        List<Tragarz> dostepni = new ArrayList<>();
        List<Tragarz> niedostepni = new ArrayList<>();

        for (Tragarz tragarz : tragarze) {
            if (tragarz.getRodzajTragarza()) {
                dostepni.add(tragarz);
            } else {
                niedostepni.add(tragarz);
            }
        }

        for (Tragarz dostepny : dostepni) {
            for (Tragarz niedostepny : niedostepni) {
                if (!dostepny.sprawdzNieLubi(niedostepny) && !niedostepny.sprawdzNieLubi(dostepny)) {
                    pary.add(new Para(dostepny.getId(), niedostepny.getId()));
                    niedostepni.remove(niedostepny);
                    break;
                }
            }
        }
    }

    public List<Para> getPary() {
        return pary;
    }

    public void wypiszPary() {
        for (Para para : pary) {
            System.out.println(para);
        }
    }

    public static class Para {
        private int tragarzId1;
        private int tragarzId2;

        public Para(int tragarzId1, int tragarzId2) {
            this.tragarzId1 = tragarzId1;
            this.tragarzId2 = tragarzId2;
        }

        @Override
        public String toString() {
            return "Para: Tragarz ID: " + tragarzId1 + " i Tragarz ID: " + tragarzId2;
        }
    }
}

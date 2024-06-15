package FenceCrafting;

import GraphFundamentals.GraphFundamentals.Tragarz;

import java.util.ArrayList;
import java.util.List;

public class ParyTragarzy {
    public List<Para> pary;

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

        BipartiteGraph graf = new BipartiteGraph(dostepni.size(), niedostepni.size());

        for (int i = 0; i < dostepni.size(); i++) {
            for (int j = 0; j < niedostepni.size(); j++) {
                if (!(dostepni.get(i).sprawdzNieLubi(niedostepni.get(j))) && !(niedostepni.get(j).sprawdzNieLubi(dostepni.get(i)))) {
                    graf.dodajKrawedz(i, j);
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
        public int tragarzId1;
        public int tragarzId2;

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

package FenceCrafting;

import GraphFundamentals.GraphFundamentals.*;

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
       graf.maksymalneSkojarzenie();

       pary.addAll(graf.getPary(dostepni, niedostepni));
    }

    public List<Para> getPary() {
        return pary;
    }

    public void wypiszPary() {
        for (Para para : pary) {
            System.out.println(para.toString());
        }
    }

    public static class Para {
        public int tragarzId1;
        public int tragarzId2;
        public int dlugoscPlotu;
        public Vertex point;
        public Vertex lastPoint;

        public List<ResidualLink> trasa;

        public Para(int tragarzId1, int tragarzId2) {
            this.tragarzId1 = tragarzId1;
            this.tragarzId2 = tragarzId2;
            this.dlugoscPlotu=0;
        }

        public void move(ResidualGraph graph){
            for(ResidualLink link : graph.getListOfResidualLinks()){
                trasa.add(link);
                if(link.getCurrentStream()<link.getMaxStream()){
                    this.point=link.getvE();
                    this.lastPoint=link.getvS();
                    int missingStream = link.getMaxStream()-link.getCurrentStream();
                    if(missingStream>=dlugoscPlotu){
                        link.setCurrentStream(link.getCurrentStream()+dlugoscPlotu);
                        dlugoscPlotu=0;
                        break;
                    }
                    else{
                        dlugoscPlotu-=missingStream;
                        link.setCurrentStream(link.getMaxStream());
                    }
                }
            }
        }
        public void moveBack(Vertex point, Vertex lastPoint){
            this.point=point;
            this.lastPoint=lastPoint;
        }

        public void goBack(){
            for(ResidualLink link : trasa){
                System.out.println("Tragarz "+tragarzId1+" i Tragarz "+tragarzId2+" cofaja sie z punktu "+link.getvE().getId()+" do punktu "+link.getvS().getId());
                moveBack(link.getvS(), link.getvE());
            }
        }
        public void setDlugoscPlotu(int dlugoscPlotu){
            this.dlugoscPlotu=dlugoscPlotu;
        }

        @Override
        public String toString() {
            return "Para: Tragarz ID: " + tragarzId1 + " i Tragarz ID: " + tragarzId2;
        }
    }
}

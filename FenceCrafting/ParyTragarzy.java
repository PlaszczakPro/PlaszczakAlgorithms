package FenceCrafting;

import GraphFundamentals.GraphFundamentals.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

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
        System.out.println("Maksymalna ilość par: " + graf.maxMatching());
        //graf.printMatching();
        pary.addAll(graf.getPary(przod, tyl));
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

        public List<ResidualLink> trasa=new ArrayList<ResidualLink>();
        public List<ResidualLink> currentTrasa=new ArrayList<ResidualLink>();

        public Para(int tragarzId1, int tragarzId2) {
            this.tragarzId1 = tragarzId1;
            this.tragarzId2 = tragarzId2;
            this.dlugoscPlotu=0;
        }

        public void move(ResidualGraph graph) throws InterruptedException {
            for(ResidualLink link : graph.getListOfResidualLinks()){
                trasa.add(link);
                if(!(currentTrasa.contains(link))) {currentTrasa.add(link);}
                sleep(150);
                System.out.println("S: " + link.getvS().getId() + ",  E: " + link.getvE().getId() + ", Current: " + link.getCurrentStream() + " Max: " + link.getMaxStream());
                if(link.getCurrentStream()<link.getMaxStream()){
                    this.point=link.getvE();
                    this.lastPoint=link.getvS();
                    int missingStream = link.getMaxStream()-link.getCurrentStream();
                    System.out.println("Dodano płot");
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
                else if(graph.allLinksFull()){
                    break;
                }
            }
        }
        public void moveBack(Vertex point, Vertex lastPoint){
            this.point=point;
            this.lastPoint=lastPoint;
        }

//        public void goBack(ResidualGraph plot){
//            System.out.println("Tragarze Wracają");
//            trasatest=plot.dijkstra(plot.getListOfResidualLinks().getFirst(), point);
//            for(ResidualLink link:trasatest){
//                moveBack(link.getvS(), link.getvE());
//                System.out.print("(E: " + link.getvE().getId() + ", S: " + link.getvS().getId() + ") -> ");
//            }
//        }

        public void goBackRoute() throws InterruptedException {
            System.out.println("Tragarze Wracają");
            for(ResidualLink link:trasa){
                moveBack(link.getvS(), link.getvE());
            }
            for(ResidualLink cl:currentTrasa.reversed()) {
                System.out.print("(E: " + cl.getvE().getId() + ", S: " + cl.getvS().getId() + ") -> ");
            }
            System.out.println("\n");
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

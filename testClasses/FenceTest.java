package testClasses;

import FenceCrafting.Fence;
import FenceCrafting.ParyTragarzy;
import GraphFundamentals.GraphFundamentals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FenceTest {
    public static void main(String[] args) {
        Fence fence = new Fence();

        //rozne punkty na plaszczyznie x, y
        fence.addPunkt(new Point(0, 0, 10));
        fence.addPunkt(new Point(0, 6, 5));
        fence.addPunkt(new Point(1, 17, 7));
        fence.addPunkt(new Point(0, 0, 1));
        fence.addPunkt(new Point(1, 9, 11));
        fence.addPunkt(new Point(3, 7, 17));
        fence.addPunkt(new Point(8, 1, 25));
        fence.addPunkt(new Point(13, 11, 20));
        fence.addPunkt(new Point(2, 1, 13));
        fence.addPunkt(new Point(4, 3, 6));

        //planowanie ksztaltu plotu oraz polozenia punktow kontrolnych
        Graph otoczka = fence.planujPlot();

        //tragarze oraz laczenie ich w pary
        Tragarz tragarz1 = new Tragarz(true);
                Tragarz tragarz2 = new Tragarz(true);
                Tragarz tragarz3 = new Tragarz(true);
                Tragarz tragarz4 = new Tragarz(false);
                Tragarz tragarz5 = new Tragarz(false);
                Tragarz tragarz6 = new Tragarz(false);
                Tragarz tragarz7 = new Tragarz(false);

                tragarz1.dodajNieLubi(tragarz2);
                tragarz1.dodajNieLubi(tragarz3);
                tragarz1.dodajNieLubi(tragarz4);

                List<Tragarz> tragarze = new ArrayList<>();
                tragarze.add(tragarz1);
                tragarze.add(tragarz2);
                tragarze.add(tragarz3);
                tragarze.add(tragarz4);
                tragarze.add(tragarz5);
                tragarze.add(tragarz6);
                tragarze.add(tragarz7);
                ParyTragarzy paraTragarzy = new ParyTragarzy(tragarze);

                paraTragarzy.wypiszPary();

                List<ParyTragarzy.Para> pary;
                pary = paraTragarzy.getPary();
                System.out.println("--------------");
                System.out.println(pary.getFirst());

        ResidualGraph residualPlot = new ResidualGraph(otoczka);

        int prevVertexId = residualPlot.getlistOfVertexes().getFirst().getId();

        Random rand = new Random();

        for(Vertex vertex : residualPlot.getlistOfVertexes()){
            if(prevVertexId != vertex.getId()){
                residualPlot.updateResiLinkMax(prevVertexId, vertex.getId(), rand.nextInt(1050) + 50);
                prevVertexId = vertex.getId();
            }
        }

        residualPlot.updateResiLinkMax(prevVertexId, residualPlot.getlistOfVertexes().getFirst().getId(), rand.nextInt(2000) + 10);

        //fence.budujPlot(paryTragarzy.getPary(), residualPlot);

        /*
        //wypisz punkty otoczki
        for (Vertex v : otoczka.getlistOfVertexes()) {
            System.out.println(v.getPoint().getX() + ", " + v.getPoint().getY() + ", " + v.getPoint().getBrightness());
        }
        Straznik straznik = new Straznik(5);
        Straznik straznik2 = new Straznik(10);
        Straznik straznik3 = new Straznik(8);
        Straznik straznik4 = new Straznik(3);
        Straznik straznik5 = new Straznik(7);
        Straznik straznik6 = new Straznik(1);
        Straznik straznik7 = new Straznik(2);
        List<Straznik> straznicy = List.of(straznik, straznik2, straznik3, straznik4, straznik5, straznik6, straznik7);
        PlaszczakQueue plaszczakQueue = new PlaszczakQueue(straznicy);
        plaszczakQueue.getReadyList();
        try {
            plaszczakQueue.guardFence(otoczka);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}

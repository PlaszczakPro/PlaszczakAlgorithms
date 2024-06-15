package testClasses;

import FenceCrafting.Fence;
import FenceCrafting.ParyTragarzy;
import GraphFundamentals.GraphFundamentals.*;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class FenceTest {
    public static void main(String[] args) {
        Fence fence = new Fence();

        //rozne punkty na plaszczyznie x, y
        fence.getPunkty().add(new Point(0, 0, 10));
        fence.getPunkty().add(new Point(0, 6, 5));
        fence.getPunkty().add(new Point(1, 17, 7));
        fence.getPunkty().add(new Point(0, 0, 1));
        fence.getPunkty().add(new Point(1, 9, 11));
        fence.getPunkty().add(new Point(3, 7, 17));
        fence.getPunkty().add(new Point(8, 1, 25));
        fence.getPunkty().add(new Point(13, 11, 20));
        fence.getPunkty().add(new Point(2, 1, 13));
        fence.getPunkty().add(new Point(4, 3, 6));

        //planowanie ksztaltu plotu oraz polozenia punktow kontrolnych
        Graph otoczka = fence.planujPlot();

        //tragarze oraz laczenie ich w pary
        Tragarz tragarz = new Tragarz(false);
        Tragarz tragarz2 = new Tragarz(true);
        Tragarz tragarz3 = new Tragarz(true);
        Tragarz tragarz4 = new Tragarz(false);
        Tragarz tragarz5 = new Tragarz(false);
        Tragarz tragarz6 = new Tragarz(true);
        Tragarz tragarz7 = new Tragarz(false);
        Tragarz tragarz8 = new Tragarz(true);

        ParyTragarzy paryTragarzy = new ParyTragarzy(List.of(tragarz, tragarz2, tragarz3, tragarz4, tragarz5, tragarz6, tragarz7, tragarz8));

        int prevVertexId = otoczka.getlistOfVertexes().getFirst().getId();

        Random rand = new Random();

        for(Vertex vertex : otoczka.getlistOfVertexes()){
            if(prevVertexId != vertex.getId()){
                otoczka.addLink(prevVertexId, vertex.getId(), rand.nextInt(1050) + 50);
                prevVertexId = vertex.getId();
            }
        }

        otoczka.addLink(prevVertexId, otoczka.getlistOfVertexes().getFirst().getId(), rand.nextInt(2000) + 10);

        ResidualGraph residualPlot = new ResidualGraph(otoczka);

        fence.budujPlot(paryTragarzy.getPary(), residualPlot);

        //wypisz punkty otoczki
        /*for (Vertex v : otoczka.getlistOfVertexes()) {
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

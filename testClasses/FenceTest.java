package testClasses;

import FenceCrafting.Fence;
import GraphFundamentals.GraphFundamentals.*;

import java.util.List;
import java.util.Stack;

public class FenceTest {
    public static void main(String[] args) {
        Fence fence = new Fence(1);

        //punkty do testowania (mozna zmieniac)
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

        Graph otoczka = fence.budujPlot();

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
        }
    }
}

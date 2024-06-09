package testClasses;

import FenceCrafting.Fence;
import GraphFundamentals.GraphFundamentals.Graph;
import GraphFundamentals.GraphFundamentals.Point;
import GraphFundamentals.GraphFundamentals.Vertex;

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
            System.out.println(v.getPoint().getX() + ", " + v.getPoint().getY());
        }
    }
}

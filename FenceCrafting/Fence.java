package FenceCrafting;

import GraphFundamentals.GraphFundamentals.*;

import java.util.*;

public class Fence {
    private ArrayList<Point> punkty;

    public Fence() {
        this.punkty = new ArrayList<>();
    }
    public ArrayList<Point> getPunkty() {
        return punkty;
    }

    //zwracamy drugi element na stosie, bez zdejmowania pierwszego elementu
    public static Point drugiNaStosie(Stack<Point> S) {
        Point p = S.pop();
        Point res = S.peek();
        S.push(p);
        return res;
    }

    //obliczamy kwadrat odleglosci miedzy dwoma punktami
    public static int odlegloscKw(Point p1, Point p2) {
        return (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
                (p1.getY() - p2.getY()) * (p1.getY() - p2.getY());
    }

    //porownujemy punkty wzgledem kata:
    //0 jak punkty sa wspolliniowe
    //1 jak punkty sa zgodne z ruchem wskazowek zegara
    //2 jak punkty sa zgodne z ruchem przeciwnie do wskazowek zegara
    public static int orientacja(Point p, Point q, Point r) {
        int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    //algorytm Grahama do obliczenia otoczki wypuklej
    public Graph planujPlot() {
        int n = punkty.size();
        if (n < 3) return null;

        Vector<Point> otoczka = new Vector<Point>();
        Graph graph = new Graph();

        int l = 0;
        for (int i = 1; i < n; i++)
            if (punkty.get(i).getX() < punkty.get(l).getX())
                l = i;

        int p = l, q;
        do {
            otoczka.add(punkty.get(p));
            graph.add(new Vertex(p, punkty.get(p)));

            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                if (orientacja(punkty.get(p), punkty.get(i), punkty.get(q))
                        == 2)
                    q = i;
            }

            p = q;

        } while (p != l);

        return graph;
    }

    public void budujPlot(List<ParyTragarzy.Para> paryTragarzy, ResidualGraph plot){
        System.out.println(plot.toString());
    }
}
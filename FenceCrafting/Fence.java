package FenceCrafting;

import GraphFundamentals.GraphFundamentals.*;

import java.util.*;

public class Fence {
    private ArrayList<Point> punkty;
    private Vertex fabryka;
    private Vertex startPoint;
    private boolean plotToBuild = true;
    public Fence() {
        this.punkty = new ArrayList<>();
    }
    public ArrayList<Point> getPunkty() {
        return punkty;
    }

    public void addPunkt(Point p) {
        punkty.add(p);
    }

    public int getFabrykaId() {
        return this.fabryka.getId();
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

        Graph graph = new Graph();

        int l = 0;
        for (int i = 1; i < n; i++)
            if (punkty.get(i).getX() < punkty.get(l).getX())
                l = i;

        int p = l, q;
        do {
            graph.add(new Vertex(p, punkty.get(p)));

            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                if (orientacja(punkty.get(p), punkty.get(i), punkty.get(q))
                        == 2)
                    q = i;
            }

            p = q;

        } while (p != l);

        for (int i = 1; i < graph.getlistOfVertexes().size(); i++) {
            graph.addLink(graph.getlistOfVertexes().get(i - 1).getId(), graph.getlistOfVertexes().get(i).getId());
        }
        graph.addLink(graph.getlistOfVertexes().getLast().getId(), graph.getlistOfVertexes().getFirst().getId());

        return graph;
    }

    public void losujFabryke(){
        Random rand = new Random();
        int index = rand.nextInt(punkty.size());
        this.fabryka = new Vertex(index, punkty.get(index));
    }

    public String toStringFabryka() {
        return "Fabryka ID: [ " + getFabrykaId() + " ]\n";
    }

    public boolean isPlotDone(ResidualGraph plot){
        for (ResidualLink link : plot.getListOfResidualLinks()) {
            if (link.getCurrentStream() < link.getMaxStream()) {
                return false;
            }
        }
        return true;
    }



    public void budujPlot(List<ParyTragarzy.Para> paryTragarzy, ResidualGraph plot){
        losujFabryke();

        System.out.println(plot.toString());
        System.out.println(paryTragarzy.toString());
        System.out.println(this.toStringFabryka());

        this.startPoint=plot.getlistOfVertexes().getFirst();
        for(Vertex v:plot.getlistOfVertexes()){
            if(fabryka.distance(v)<fabryka.distance(startPoint)){
                startPoint=v;
            }
        }

        System.out.println("Start point: " + startPoint.getId());

        plot.addResiLink(fabryka, startPoint);

        while(plotToBuild){
            if(isPlotDone(plot)){
                plotToBuild=false;
                break;
            }
            for (ParyTragarzy.Para para : paryTragarzy) {
                if(para.dlugoscPlotu == 0) {
                    //para.wrocDoFabryki(plot);
                } else {
                    para.move(plot);
                }
            }
        }
        System.out.println("Pomyslnie wybudowano plot!");
    }
}
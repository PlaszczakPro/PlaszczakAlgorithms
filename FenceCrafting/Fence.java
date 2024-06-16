package FenceCrafting;

import GraphFundamentals.GraphFundamentals.*;

import java.util.*;

public class Fence {
    private ArrayList<Point> punkty;

    private List<ResidualLink> links;
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
    public ResidualGraph planujPlot() {
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
        ResidualGraph residualGraph = new ResidualGraph(graph);
        for(ResidualLink link : residualGraph.getListOfResidualLinks()){
            link.setMaxStream((int)link.getvS().distance(link.getvE()));
        }
        return residualGraph;
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
        System.out.println(plot.getlistOfVertexes());
        this.startPoint=plot.getlistOfVertexes().getFirst();
        do {
            losujFabryke();
            for (Vertex v : plot.getlistOfVertexes()) {
                if (fabryka.distance(v) < fabryka.distance(startPoint)) {
                    startPoint = v;
                }
            }
        } while(fabryka.getId() == startPoint.getId());

        System.out.println("Start point: " + startPoint.getId());
        System.out.println("Fabryka: " + fabryka.getId());
        System.out.println("Fabryka: " + fabryka.getPoint());
        System.out.println(" ");

        plot.addResiLinkFirst(fabryka, startPoint);

        while(plotToBuild){
            if(isPlotDone(plot)){
                plotToBuild=false;
                break;
            }
            for (ParyTragarzy.Para para : paryTragarzy) {
                para.dlugoscPlotu=2;
                while(para.dlugoscPlotu>0 && !plot.allLinksFull()){
                    System.out.println("Tragarz " + para.tragarzId1 + " i Tragarz " + para.tragarzId2);
                    para.move(plot, startPoint);
                }
                if(!(plot.allLinksFull())) {para.goBackRoute();}
            }
        }
        System.out.println("Płot skończony");
    }
}

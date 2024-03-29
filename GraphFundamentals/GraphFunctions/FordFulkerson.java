package GraphFundamentals.GraphFunctions;

import GraphFundamentals.GraphFundamentals.*;

import java.util.Arrays;
import java.util.LinkedList;

public class FordFulkerson {
    public static boolean bfsAugmentingPath(ResidualGraph residualGraph, int[] parent) {
        boolean[] visited = new boolean[residualGraph.getListOfVertices().size()];
        Arrays.fill(visited, false);

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        parent[0] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            int index = u;

            if(u == 99){
                index = residualGraph.getSize() - 1;
            }

            for (Link link : residualGraph.getListOfLinks(index)) {
                int v = link.getvE();
                int listPos = v;
                if(listPos == 99){
                    listPos = visited.length - 1;
                }
                if (!visited[listPos] && link.getStream() > 0) {
                    queue.add(v);
                    visited[listPos] = true;
                    parent[listPos] = u;
                    if(v == 99) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //TODO: PONIZEJ POWINIEN ZNALEZC SIE ALGORYTM FORDA-FULKERSONA
    /*public static int fordFulkerson(ResidualGraph residualGraph){
        ...
    }*/

    //TODO: DANE DO TESTOWANIA | USUNAC GDY JUZ FULKERSON ZOSTANIE UKONCZONY
    /*public static void main(String[] args) {
        Graph graph = new Graph();

        Vertice s = new Vertice(0);
        Vertice t = new Vertice(99);
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);

        Link sv1 = new Link(v1, 16, 0);
        Link sv2 = new Link(v2, 13, 0);
        s.addLink(sv1);
        s.addLink(sv2);

        Link v1v3 = new Link(v3, 12, 0);
        Link v1v2 = new Link(v2, 10, 0);
        v1.addLink(v1v3);
        v1.addLink(v1v2);

        Link v2v1 = new Link(v1, 4, 0);
        Link v2v4 = new Link(v4, 14, 0);
        v2.addLink(v2v1);
        v2.addLink(v2v4);

        Link v3v2 = new Link(v2, 9, 0);
        Link v3t = new Link(t, 20, 0);
        v3.addLink(v3v2);
        v3.addLink(v3t);

        Link v4t = new Link(t, 4, 0);
        v4.addLink(v4t);

        graph.add(s);
        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.add(t);

        ResidualGraph residualGraph = new ResidualGraph(graph);

        int[] sciezka = new int[residualGraph.getListOfVertices().size()];

        boolean hasAugmentingPath = bfsAugmentingPath(residualGraph, sciezka);
        if (hasAugmentingPath) {
            System.out.println("Znaleziono ścieżkę powiększającą.");
        } else {
            System.out.println("Nie znaleziono ścieżki powiększającej.");
        }

    }*/
}

package testClasses;

import GraphFundamentals.GraphFundamentals.*;

public class PathfinderTests {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    public static void test1() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1, new Point(5, 5, 0));
        Vertex v2 = new Vertex(2, new Point(6, 5, 0));
        Vertex v3 = new Vertex(3, new Point(7, 5, 0));
        Vertex v4 = new Vertex(4, new Point(8, 5, 0));
        Vertex v5 = new Vertex(5, new Point(0, 0, 0));
        Vertex v6 = new Vertex(6, new Point(8, 4, 0));

        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.add(v5);
        graph.add(v6);

        graph.addLink(1, 2);
        graph.addLink(2, 3);
        graph.addLink(3, 4);
        graph.addLink(4, 6);
        graph.addLink(1, 5);
        graph.addLink(5, 6);

        ResidualGraph residualGraph = new ResidualGraph(graph);
        Pathfinder.findShortestPath(residualGraph, v1);

        for (Vertex it : graph.getlistOfVertexes()){
            it.showPathtoFactory();
        }

    }

    public static void test2() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1, new Point(0, 0, 0));
        Vertex v2 = new Vertex(2, new Point(1, 0, 0));
        Vertex v3 = new Vertex(3, new Point(1, 1, 0));
        Vertex v4 = new Vertex(4, new Point(0, 1, 0));

        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);

        graph.addLink(1, 2);
        graph.addLink(2, 3);
        graph.addLink(3, 4);
        graph.addLink(4, 1);

        ResidualGraph residualGraph = new ResidualGraph(graph);
        Pathfinder.findShortestPath(residualGraph, v1);

        for (Vertex it : graph.getlistOfVertexes()) {
            it.showPathtoFactory();
        }
    }

    public static void test3() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1, new Point(0, 0, 0));
        Vertex v2 = new Vertex(2, new Point(1, 1, 0));
        Vertex v3 = new Vertex(3, new Point(2, 2, 0));
        Vertex v4 = new Vertex(4, new Point(3, 3, 0));
        Vertex v5 = new Vertex(5, new Point(4, 4, 0));

        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.add(v5);

        graph.addLink(1, 2);
        graph.addLink(1, 3);
        graph.addLink(1, 4);
        graph.addLink(1, 5);

        ResidualGraph residualGraph = new ResidualGraph(graph);
        Pathfinder.findShortestPath(residualGraph, v1);

        for (Vertex it : graph.getlistOfVertexes()) {
            it.showPathtoFactory();
        }
    }

    public static void test4() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1, new Point(0, 0, 0));
        Vertex v2 = new Vertex(2, new Point(1, 0, 0));
        Vertex v3 = new Vertex(3, new Point(2, 0, 0));
        Vertex v4 = new Vertex(4, new Point(1, 1, 0));
        Vertex v5 = new Vertex(5, new Point(2, 1, 0));

        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.add(v5);

        graph.addLink(1, 2);
        graph.addLink(2, 3);
        graph.addLink(2, 4);
        graph.addLink(3, 5);

        ResidualGraph residualGraph = new ResidualGraph(graph);
        Pathfinder.findShortestPath(residualGraph, v1);

        for (Vertex it : graph.getlistOfVertexes()) {
            it.showPathtoFactory();
        }
    }
}

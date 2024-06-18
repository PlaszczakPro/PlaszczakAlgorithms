package GraphFundamentals.GraphFundamentals;

import java.util.*;

public class Pathfinder {

    public static void findShortestPath(ResidualGraph graph, Vertex source) {

        Map<Vertex, Double> minDistanceFromSource = new HashMap<>();
        Map<Vertex, Vertex> prevVertex = new HashMap<>();
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(minDistanceFromSource::get));

        for (Vertex vertex :graph.getlistOfVertexes()) {
            if (vertex.equals(source)) {
                minDistanceFromSource.put(vertex, 0.0);
            } else {
                minDistanceFromSource.put(vertex,Double.MAX_VALUE);
            }
            priorityQueue.add(vertex);
        }

        while (!priorityQueue.isEmpty()   ) {
            Vertex curVertex = priorityQueue.poll();


            for (Vertex neighbour : graph.getNeighbours(curVertex)) {
                ResidualLink link = graph.getResiLink(curVertex, neighbour);
                if (link != null) {


                    double newDist = minDistanceFromSource.get(curVertex) + curVertex.distance(neighbour);

                    if (newDist < minDistanceFromSource.get(neighbour)) {
                        priorityQueue.remove(neighbour);
                        minDistanceFromSource.put(neighbour, newDist);
                        prevVertex.put(neighbour, curVertex);
                        priorityQueue.add(neighbour);
                    }

                }

            }
        }

        for (Vertex curVertex : graph.getlistOfVertexes()) {
            if (!curVertex.equals(source) && prevVertex.containsKey(curVertex)) {

                List<Vertex> path = new ArrayList<>();

                Vertex it = curVertex;
                while (it!=null){
                    path.add(it);
                    it = prevVertex.get(it);
                }

               // Collections.reverse(path);
                curVertex.setShortestPathToStart(path);
            }
        }

    }
}

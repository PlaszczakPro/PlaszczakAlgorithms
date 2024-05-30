package GraphFundamentals.GraphFunctions;

import GraphFundamentals.GraphFundamentals.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EdmondsKarp {

    private static final int sinkIndex = Integer.MAX_VALUE;

    public static boolean bfsAugmentingPath(ResidualGraph residualGraph, int[] parent) {
        boolean[] visited = new boolean[residualGraph.getlistOfVertexes().size()];
        Arrays.fill(visited, false);

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        parent[0] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            int index = u;

            if (u == sinkIndex) {
                index = residualGraph.getSize() - 1;
            }

            for (Link link : residualGraph.getListOfLinks(index)) {
                int v = link.getvE();
                int listPos = v;
                if (listPos == sinkIndex) {
                    listPos = visited.length - 1;
                }
                if (!visited[listPos] && link.getStream() > 0) {
                    queue.add(v);
                    visited[listPos] = true;
                    parent[listPos] = u;
                    if (v == sinkIndex) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //zmniejsza dla kazdej krawedzi na danej sciezce pojemnosc rezydualna o dana wartosc
    private static void updatePathCapacity(List<ResidualLink> path, int bottleneckCapacity) {
        for (ResidualLink it : path) {
            it.setStream(it.getStream() - bottleneckCapacity);
        }
    }

    //znajduje najmniejsza pojemnosc rezydualna w danej sciezce
    private static int findBottleneckCapacity(List<ResidualLink> path) {
        int smallestResCapacity = Integer.MAX_VALUE;
        int resCapacity;
        for (ResidualLink it : path) {
            resCapacity = it.getStream();
            if (resCapacity < smallestResCapacity) smallestResCapacity = resCapacity;
        }
        return smallestResCapacity;
    }

    //tlumaczy tablice zwracana przez bfsAugmentingPath na liste krawedzi - najkrotsza droge miedzy zrodlem, a ujsciem
    private static List<ResidualLink> convertParentArrayToPath(ResidualGraph residualGraph, int[] parent) {
        List<ResidualLink> path = new LinkedList<>();

        int parentIt = parent.length - 1;
        Vertex curVertex = residualGraph.getVertice(sinkIndex);
        Vertex prevVertex = residualGraph.getVertice(parent[parentIt]);
        while (parentIt != 0) {
            path.add(0, prevVertex.getResiLink(curVertex));
            parentIt = parent[parentIt];
            curVertex = prevVertex;
            prevVertex = residualGraph.getVertice(parent[parentIt]);
        }
        return path;
    }

    //znajduje najwieksza mozliwa przepustowosc w sieci, korzysta z algorytmu E-K
    public static int findMaxFlow(ResidualGraph residualGraph) {
        int maxFlow = 0;

        int bottleneckCapacity;

        int[] parent = new int[residualGraph.getlistOfVertexes().size()];
        boolean hasAugmentingPath = bfsAugmentingPath(residualGraph, parent);

        while (hasAugmentingPath) {
            List<ResidualLink> path = convertParentArrayToPath(residualGraph, parent);
            bottleneckCapacity = findBottleneckCapacity(path);
            maxFlow += bottleneckCapacity;
            updatePathCapacity(path, bottleneckCapacity);

            parent = new int[residualGraph.getlistOfVertexes().size()];
            hasAugmentingPath = bfsAugmentingPath(residualGraph, parent);
        }

        return maxFlow;
    }
}

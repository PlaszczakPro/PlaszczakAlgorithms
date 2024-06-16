package GraphFundamentals.GraphFundamentals;

import java.util.*;

public class ResidualGraph extends Graph{
    List<Vertex> listOfVertexes = new ArrayList<Vertex>();
    List<ResidualLink> listOfResidualLinks = new ArrayList<ResidualLink>();
    public ResidualGraph(Graph graph){
        this.listOfVertexes = graph.listOfVertexes;
        for(Link link: graph.listOfLinks){
            addResiLink(link.getvS(), link.getvE());
        }
    }

    public boolean allLinksFull(){
        for(ResidualLink link: listOfResidualLinks){
            if(link.currentStream < link.maxStream){
                return false;
            }
        }
        return true;
    }

    public void addResiLink(Vertex v1, Vertex v2){
        ResidualLink link = new ResidualLink(v1, v2, 0, 0);
        listOfResidualLinks.add(link);
    }

    public void addResiLinkFirst(Vertex v1, Vertex v2){
        ResidualLink link = new ResidualLink(v1, v2, 0, 0);
        listOfResidualLinks.addFirst(link);
    }

    public void swapResiLinks(){
        Collections.rotate(listOfResidualLinks, 1);
    }

    public List<ResidualLink> getListOfResidualLinks(){
        return this.listOfResidualLinks;
    }

    public ResidualLink getResiLink(Vertex v1, Vertex v2){
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS==v1 && link.vE==v2){
                return link;
            }
        }
        return null;
    }

    public void updateResiLinkMax(int id1, int id2, int newMaxStream){
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS.id==id1 && link.vE.id==id2){
                link.maxStream = newMaxStream;
            }
        }
    }


    public ResidualLink getResiLink(int id1, int id2){
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS.id==id1 && link.vE.id==id2){
                return link;
            }
        }
        return null;
    }

    public void addToCurrentStream(int id1, int id2, int value){
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS.id==id1 && link.vE.id==id2){
                link.currentStream += value;
            }
        }
    }

    public int checkRemainingFlow(int id1, int id2){
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS.id==id1 && link.vE.id==id2){
                return link.maxStream - link.currentStream;
            }
        }
        return 0;
    }

    public boolean checkIfFlowIsFull(int id1, int id2){
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS.id==id1 && link.vE.id==id2){
                return link.maxStream == link.currentStream;
            }
        }
        return false;
    }

    public List<ResidualLink> shortestRouteVertextoVertex(Vertex start, Vertex end){
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previousVertices = new HashMap<>();
        PriorityQueue<Vertex> verticesQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Vertex vertex : listOfVertexes) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
            }
            previousVertices.put(vertex, null);
        }

        verticesQueue.add(start);

        while (!verticesQueue.isEmpty()) {
            Vertex currentVertex = verticesQueue.poll();

            for (ResidualLink link : listOfResidualLinks) {
                if (link.vS.equals(currentVertex)) {
                    Vertex adjacentVertex = link.vE;
                    int alternateDist = distances.get(currentVertex) + 1;

                    if (alternateDist < distances.get(adjacentVertex)) {
                        distances.put(adjacentVertex, alternateDist);
                        previousVertices.put(adjacentVertex, currentVertex);
                        verticesQueue.remove(adjacentVertex);
                        verticesQueue.add(adjacentVertex);
                    }
                }
            }
        }

        List<ResidualLink> shortestPath = new ArrayList<>();
        for (Vertex vertex = end; vertex != null; vertex = previousVertices.get(vertex)) {
            Vertex previousVertex = previousVertices.get(vertex);
            if (previousVertex != null) {
                shortestPath.add(getResiLink(previousVertex, vertex));
            }
        }

        Collections.reverse(shortestPath);
        return shortestPath;
    }

    @Override
    public List<Vertex> getlistOfVertexes (){
        return this.listOfVertexes;
    }

    @Override
    public int getSize (){
        return this.listOfVertexes.size();
    }

    public List<Vertex> getNeighbours(Vertex vertex){
        List<Vertex> neighbours = new ArrayList<Vertex>();
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS==vertex){
                neighbours.add(link.vE);
            }
        }
        return neighbours;
    }

    @Override
    public String toString() {
        return "ResidualGraph: " +
                "id=" + id +
                "\n, Vertexes: " + listOfVertexes +
                "\n, Links: " + listOfResidualLinks +
                "}\n";
    }
}

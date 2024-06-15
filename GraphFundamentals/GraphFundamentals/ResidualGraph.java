package GraphFundamentals.GraphFundamentals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResidualGraph extends Graph{
    List<Vertex> listOfVertexes = new ArrayList<Vertex>();
    List<ResidualLink> listOfResidualLinks = new ArrayList<ResidualLink>();
    public ResidualGraph(Graph graph){
        this.listOfVertexes = graph.listOfVertexes;
        for(Link link: graph.listOfLinks){
            addResiLink(link.getvS(), link.getvE());
        }
    }

    public void addResiLink(Vertex v1, Vertex v2){
        ResidualLink link = new ResidualLink(v1, v2, 0, 0);
        listOfResidualLinks.add(link);
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
    /*public List<ResidualLink> shortestRouteToCompany(Vertex ve,ResidualGraph plot,Vertex fabryka){
        int V = plot.getlistOfVertexes().size();
        double[] dist = new double[V];
        double[] prev = new double[V];

        for (int i = 0; i < V; ++i) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        dist[fabryka.getId()] = 0;

        for (int i = 1; i < V; ++i) {
            for (ResidualLink link : plot.getListOfResidualLinks()) {
                int u = link.getvS().getId();
                int v = link.getvE().getId();
                double weight = fabryka.distance(link.getvE());
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;
                }
            }
        }

        List<ResidualLink> path = new ArrayList<>();
        for (int v = ve.getId(); v != -1; v = (int)prev[v]) {
            path.add(plot.getResiLink((int)prev[v], v));
        }

        Collections.reverse(path);

        return path;
    }*/

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

    @Override
    public List<Vertex> getlistOfVertexes (){
        return this.listOfVertexes;
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

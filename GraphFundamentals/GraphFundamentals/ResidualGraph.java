package GraphFundamentals.GraphFundamentals;

import java.util.ArrayList;
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

    public void updateResiLinkMax(int id1, int id2, int newMaxStream){
        for(ResidualLink link: listOfResidualLinks){
            if(link.vS.id==id1 && link.vE.id==id2){
                link.maxStream = newMaxStream;
            }
        }
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

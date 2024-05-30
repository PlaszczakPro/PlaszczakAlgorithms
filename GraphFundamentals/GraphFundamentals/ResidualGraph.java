package GraphFundamentals.GraphFundamentals;

public class ResidualGraph extends Graph{
    public ResidualGraph(Graph graph){
        for(Vertex v: graph.listOfVertexes) {
            Vertex newVertex = new Vertex(v.id);
            add(newVertex);
        }
        for(Vertex v:graph.listOfVertexes){
            for(Link link:v.listOfLinks){
                if(!linkExists(link.vE.id, v.id)){
                    if(!(link.maxStream-link.currentStream==0)) {
                        getVertice(v.id).addResiLink(new ResidualLink(getVertice(link.vE.id), link.maxStream - link.currentStream));
                    }
                    getVertice(link.vE.id).addResiLink(new ResidualLink(getVertice(v.id), link.currentStream));
                }
                else{
                    getVertice(v.id).getResiLink(getVertice(link.vE.id)).setStream(link.maxStream-link.currentStream);
                }
            }
        }
    }
}

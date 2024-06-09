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
                        getVertex(v.id).addResiLink(new ResidualLink(getVertex(link.vE.id), link.maxStream - link.currentStream));
                    }
                    getVertex(link.vE.id).addResiLink(new ResidualLink(getVertex(v.id), link.currentStream));
                }
                else{
                    getVertex(v.id).getResiLink(getVertex(link.vE.id)).setStream(link.maxStream-link.currentStream);
                }
            }
        }
    }
}

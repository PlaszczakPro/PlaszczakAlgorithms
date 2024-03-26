package GraphFundamentals.GraphFundamentals;

public class ResidualGraph extends Graph{
    public ResidualGraph(Graph graph){
        for(Vertice v: graph.listOfVertices) {
            Vertice newVertice = new Vertice(v.id);
            add(newVertice);
        }
        for(Vertice v:graph.listOfVertices){
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

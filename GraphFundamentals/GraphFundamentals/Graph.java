package GraphFundamentals.GraphFundamentals;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int id;
    static int counter=-1;
    List<Vertice> listOfVertices =new ArrayList<Vertice>();
    public Graph(){
        counter++;
        id=counter;
    }
    public void add(Vertice vertice){
        listOfVertices.add(vertice);
    }
    public void add(List<Vertice> vertices){
        listOfVertices.addAll(vertices);
    }

    public Graph residualGraph(){
        Graph residualGraph=new Graph();
        for(Vertice v: listOfVertices) {
            Vertice newVertice = new Vertice(v.id);
            residualGraph.add(newVertice);
        }
        for(Vertice v:listOfVertices){
            for(Link link:v.listOfLinks){
                if(!residualGraph.linkExists(link.vE.id, v.id)){
                    if(!(link.maxStream-link.currentStream==0)) {
                        residualGraph.getVertice(v.id).addLink(new Link(getVertice(link.vE.id), link.maxStream - link.currentStream));
                    }
                    residualGraph.getVertice(link.vE.id).addLink(new Link(getVertice(v.id), link.currentStream));
                }
                else{
                    residualGraph.getVertice(v.id).getLink(getVertice(link.vE.id)).setMaxStream(link.maxStream-link.currentStream);
                }
            }
        }
        return residualGraph;
    }
    public Vertice getVertice(int id){
        for(Vertice v: listOfVertices){
            if(v.id==id){
                return v;
            }
        }
        return null;
    }
    public boolean linkExists(int id1, int id2){
        Vertice v1=getVertice(id1);
        for(Link link:v1.listOfLinks){
            if(link.vE.id==id2){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "Graph: " +
                "id=" + id +
                "\n, Vertices: " + listOfVertices +
                "}\n";
    }
}

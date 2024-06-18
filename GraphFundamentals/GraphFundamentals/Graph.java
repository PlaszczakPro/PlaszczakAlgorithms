package GraphFundamentals.GraphFundamentals;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int id;
    static int counter=-1;
    List<Vertex> listOfVertexes =new ArrayList<Vertex>();
    List<Link> listOfLinks = new ArrayList<Link>();

    public Graph(){
        counter++;
        id=counter;
    }
    public void add(Vertex vertex){
        listOfVertexes.add(vertex);
    }
    public void add(List<Vertex> vertices){
        listOfVertexes.addAll(vertices);
    }

    public Vertex getVertex(int id){
        for(Vertex v: listOfVertexes){
            if(v.getId()==id){
                return v;
            }
        }
        return null;
    }

    public void addLink(int id1, int id2){
        Vertex v1 = getVertex(id1);
        Vertex v2 = getVertex(id2);
        Link link = new Link(v1, v2);
        listOfLinks.add(link);
    }

    @Override
    public String toString() {
        return "Graph: " +
                "id=" + id +
                "\n, Vertexes: " + listOfVertexes +
                "\n, Links: \n" + listOfLinks +
                "\n}\n";
    }
    public int getSize (){
        return this.listOfVertexes.size();
    }

    public List<Vertex> getlistOfVertexes (){
        return this.listOfVertexes;
    }
}

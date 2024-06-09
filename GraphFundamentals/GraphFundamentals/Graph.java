package GraphFundamentals.GraphFundamentals;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int id;
    static int counter=-1;
    List<Vertex> listOfVertexes =new ArrayList<Vertex>();
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
            if(v.id==id){
                return v;
            }
        }
        return null;
    }
    public boolean linkExists(int id1, int id2){
        Vertex v1=getVertex(id1);
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
                "\n, Vertices: " + listOfVertexes +
                "}\n";
    }

    public int getSize (){
        return this.listOfVertexes.size();
    }

    public List<Vertex> getlistOfVertexes (){
        return this.listOfVertexes;
    }

    public List<Link> getListOfLinks (int index){
        return this.listOfVertexes.get(index).listOfLinks;
    }
}

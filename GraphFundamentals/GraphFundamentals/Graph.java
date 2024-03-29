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

    public int getSize (){
        return this.listOfVertices.size();
    }

    public List<Vertice> getListOfVertices (){
        return this.listOfVertices;
    }

    public List<Link> getListOfLinks (int index){
        return this.listOfVertices.get(index).listOfLinks;
    }
}

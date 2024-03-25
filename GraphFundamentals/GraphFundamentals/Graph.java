package GraphFundamentals;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    int id;
    List<Vertice> listOfVertices =new ArrayList<Vertice>();
    public Graph(){

    }
    public void add(Vertice vertice){
        listOfVertices.add(vertice);
    }
    public void add(List<Vertice> vertices){
        for(int i=0;i<vertices.size();i++){
            listOfVertices.add(vertices.get(i));
        }
    }

    @Override
    public String toString() {
        return "Graph: " +
                "id=" + id +
                "\n, Vertices: " + listOfVertices +
                "}\n";
    }
}

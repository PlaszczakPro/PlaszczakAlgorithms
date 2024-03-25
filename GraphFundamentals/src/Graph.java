import java.util.ArrayList;
import java.util.List;

public class Graph {
    int id;
    List<Vertice> listOfVertices =new ArrayList<Vertice>();
    public Graph(){

    }
    public void addVertice(Vertice vertice){
        listOfVertices.add(vertice);
    }

}

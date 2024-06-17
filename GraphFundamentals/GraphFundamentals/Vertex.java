package GraphFundamentals.GraphFundamentals;


import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int id;
    Point point;

    List<ResidualLink> shortestPathToStart= new ArrayList<ResidualLink>();

    public Vertex(int id){
        this.id=id;
    }

    public Vertex(int id, Point point){
        this.id=id;
        this.point=point;
    }

    public double distance(Vertex v){
        return point.distance(v.getPoint());
    }

    public Point getPoint(){
        return point;
    }

    public void setShortestPathToStart(List<ResidualLink> shortestPathToStart){
        this.shortestPathToStart=shortestPathToStart;
    }

    public List<ResidualLink> getShortestPathToStart(){
        return shortestPathToStart;
    }

    @Override
    public String toString() {
        return "Vertex: " +
                "id=" + id +
                "\n";
    }

    public int getId(){
        return id;
    }
}
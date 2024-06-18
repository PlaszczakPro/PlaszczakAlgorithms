package GraphFundamentals.GraphFundamentals;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vertex {
    int id;
    Point point;

    List<Vertex> shortestPathToStart= new ArrayList<>();

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

    public void setShortestPathToStart(List<Vertex> shortestPathToStart){
        this.shortestPathToStart=shortestPathToStart;
    }

    public List<Vertex> getShortestPathToStart(){
        return shortestPathToStart;
    }

    public void showPathtoFactory(){
        System.out.print("Tragarze wracaja trasa : ");
        for (int it = 0; it < shortestPathToStart.size(); it++) {
            System.out.print(shortestPathToStart.get(it).getId());
            if (it < shortestPathToStart.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("-> Fabryka");
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
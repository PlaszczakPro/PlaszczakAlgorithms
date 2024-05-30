package GraphFundamentals.GraphFundamentals;


import java.util.ArrayList;
import java.util.List;

public class Vertex {
    int id;
    List<Link> listOfLinks=new ArrayList<Link>();
    public Vertex(int id){
        this.id=id;
    }
    public void addLink(Link link){
        listOfLinks.add(link);
    }
    public void addResiLink(ResidualLink link){
        listOfLinks.add(link);
    }
    public void printLinks(){
        for (Link listOfLink : listOfLinks) {
            System.out.println(listOfLink);
        }
    }
    public Link getLink(Vertex v){
        for(Link link: listOfLinks){
            if(link.vE.id==v.id){
                return link;
            }
        }
        return null;
    }
    public ResidualLink getResiLink(Vertex v){
        for(Link link: listOfLinks){
            if(link.vE.id==v.id){
                return (ResidualLink) link;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Vertice: " +
                "id=" + id +
                ",\n listOfLinks=\n" + listOfLinks +
                "\n";
    }

    public int getId(){
        return id;
    }
}
package GraphFundamentals.GraphFundamentals;


import java.util.ArrayList;
import java.util.List;

public class Vertice {
    int id;
    List<Link> listOfLinks=new ArrayList<Link>();
    public Vertice(int id){
        this.id=id;
    }
    public void addLink(Link link){
        listOfLinks.add(link);
    }
    public void addResiLink(ResidualLink link){
        listOfLinks.add(link);
    }
    public void printLinks(){
        for(int i=0;i< listOfLinks.size();i++){
            System.out.println(listOfLinks.get(i));
        }
    }
    public Link getLink(Vertice v){
        for(Link link: listOfLinks){
            if(link.vE.id==v.id){
                return link;
            }
        }
        return null;
    }
    public ResidualLink getResiLink(Vertice v){
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
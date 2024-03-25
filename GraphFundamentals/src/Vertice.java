import java.util.ArrayList;
import java.util.List;

public class Vertice {
    int id;
    char name;
    List<Link> listOfLinks=new ArrayList<Link>();
    public Vertice(int id){
        this.id=id;
    }
    public void addLink(Link link){
        listOfLinks.add(link);
    }
    public void printLinks(){
        for(int i=0;i< listOfLinks.size();i++){
            System.out.println(listOfLinks.get(i));
        }
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "id=" + id +
                ", listOfLinks=" + listOfLinks +
                "}\n";
    }

    public int getId(){
        return id;
    }
}

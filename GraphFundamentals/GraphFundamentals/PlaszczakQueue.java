package GraphFundamentals.GraphFundamentals;

import java.util.*;

import static java.lang.Thread.sleep;
import static java.lang.Thread.startVirtualThread;

public class PlaszczakQueue {
    List<Straznik> readylist=new ArrayList<>();

    public PlaszczakQueue(List<Straznik> straznicy) {
        for(Straznik straznik:straznicy){
            readylist.add(straznik);
        }
        readylist.sort(Straznik::compareTo);
    }

    public boolean isReadyEmpty(){
        return readylist.isEmpty();
    }
    public Straznik getFromReady(){
        return readylist.getFirst();
    }

    public void addToReady(Straznik straznik){
        readylist.add(straznik);
        readylist.sort(Straznik::compareTo);
    }

    public void getReadyList(){
        for(Straznik straznik:readylist){
            System.out.println(straznik.getID()+" "+straznik.getStamina());
        }
    }

    public void guardFence(Graph fence) throws InterruptedException {
        while(true){
            Straznik straznik=readylist.getFirst();
            Point pierwszy=fence.getlistOfVertexes().getFirst().getPoint();
            assert straznik != null;
            straznik.setPoint(pierwszy);
            System.out.println("Straznik "+straznik.getID()+" patroluje punkt "+pierwszy.getX()+" "+pierwszy.getY());
            sleep(1000);
            straznik.move(fence.getlistOfVertexes().get(1).getPoint());
            System.out.println("Straznik "+straznik.getID()+" patroluje punkt "+fence.getlistOfVertexes().get(1).getPoint().getX()+" "+fence.getlistOfVertexes().get(1).getPoint().getY());
            sleep(1000);
            for(int j=2;j<fence.getSize();j++){
                Vertex v=fence.getlistOfVertexes().get(j);
                straznik.move(v.getPoint());
                System.out.println("Straznik "+straznik.getID()+" patroluje punkt "+v.getPoint().getX()+" "+v.getPoint().getY());
                sleep(1000);
                if(straznik.getPoint()==pierwszy){
                    break;
                }
            }
            readylist.remove(straznik);
            straznik.setStamina(0);
            readylist.add(straznik);
            for(Straznik s1:readylist){
                s1.setStamina(s1.getStamina()+1);
            }
            System.out.println("\n zmiana straznika \n");
        }
    }
}

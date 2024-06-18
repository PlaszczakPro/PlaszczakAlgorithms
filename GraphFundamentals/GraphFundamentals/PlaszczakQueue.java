package GraphFundamentals.GraphFundamentals;

import FenceCrafting.Fence;
import Other.Introsort;

import java.util.*;

import static java.lang.Thread.sleep;

public class PlaszczakQueue {
    List<Straznik> readylist=new ArrayList<>();

    public PlaszczakQueue(List<Straznik> straznicy) {
        for(Straznik straznik:straznicy){
            readylist.add(straznik);
        }
        Introsort<Straznik> sorter = new Introsort<>();
        sorter.sortList(readylist);
    }

    public void getReadyList(){
        for(Straznik straznik:readylist){
            System.out.println(straznik.getID()+" "+straznik.getStamina());
        }
    }

    public void guardFence(ResidualGraph fence) throws InterruptedException {
        while(true){
            Straznik straznik=readylist.getFirst();
            Point pierwszy=fence.getlistOfVertexes().getFirst().getPoint();
            assert straznik != null;
            straznik.setPoint(pierwszy);
            straznik.setLastListenPoint(pierwszy);
            System.out.println("Straznik "+straznik.getID()+" patroluje punkt: "+fence.getlistOfVertexes().getFirst().getId()+" x: "+fence.getlistOfVertexes().getFirst().getPoint().getX()+" y: "+fence.getlistOfVertexes().getFirst().getPoint().getY());
            sleep(250);
            straznik.move(fence.getlistOfVertexes().get(1));
            sleep(250);
            for(int j=2;j<fence.getlistOfVertexes().size();j++){
                Vertex v=fence.getlistOfVertexes().get(j);
                straznik.move(v);
                sleep(250);
                if(straznik.getPoint()==pierwszy){
                    break;
                }
            }
            readylist.remove(straznik);
            straznik.setStamina(0);
            straznik.setCounter(1);
            readylist.add(straznik);
            for(Straznik s1:readylist){
                s1.setStamina(s1.getStamina()+1);
            }
            System.out.println("\n zmiana straznika \n");
        }
    }
}

package GraphFundamentals.GraphFundamentals;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PlaszczakQueue {
    PriorityQueue<Straznik> readylist = new PriorityQueue<>();

    public PlaszczakQueue(List<Straznik> straznicy) {
        this.readylist.addAll(straznicy);
    }
    public void addToReady(Straznik straznik){
        readylist.offer(straznik);
    }

    public boolean isReadyEmpty(){
        return readylist.isEmpty();
    }
    public Straznik getFromReady(){
        return readylist.poll();
    }

}

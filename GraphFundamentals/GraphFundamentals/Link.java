package GraphFundamentals;

public class Link {
    static int counter=0;
    Vertice vE;
    int maxStream;
    int currentStream;
    int id;
    public Link(Vertice vE, int maxStream, int currentStream){
        counter++;
        id=counter;
        this.vE=vE;
        this.maxStream=maxStream;
        this.currentStream=currentStream;
    }
    public void setCurrentStream(int currentStream){
        this.currentStream=currentStream;
    }

    @Override
    public String toString() {
        return "Link: "+
                "[ vE=" + vE.getId() +
                ", maxStream=" + maxStream +
                ", currentStream=" + currentStream +
                ", id=" + id +
                "]\n";
    }
}
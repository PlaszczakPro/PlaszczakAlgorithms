package GraphFundamentals.GraphFundamentals;

public class Link {
    Vertice vE;
    int maxStream;
    int currentStream;
    int id;
    public Link(Vertice vE, int maxStream, int currentStream){
        this.vE=vE;
        this.maxStream=maxStream;
        this.currentStream=currentStream;
    }

    public Link(Vertice v, int maxStream) {
        this.vE=v;
        this.maxStream=maxStream;
    }
    public int getvE(){
        return vE.getId();
    }
    public void setCurrentStream(int currentStream){
        this.currentStream=currentStream;
    }
    public void setMaxStream(int maxStream){
        this.maxStream=maxStream;
    }


    @Override
    public String toString() {
        return "Link: "+
                "[ vE=" + vE.getId() +
                ", maxStream=" + maxStream +
                ", currentStream=" + currentStream +
                "]\n";
    }
}
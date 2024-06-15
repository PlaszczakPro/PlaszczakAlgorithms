package GraphFundamentals.GraphFundamentals;

public class ResidualLink extends Link{
    int maxStream;
    int currentStream;
    public ResidualLink(Vertex vS, Vertex vE, int maxStream, int currentStream) {
        super(vS, vE);
        this.maxStream=maxStream;
        this.currentStream=currentStream;
    }

    public void setMaxStream(int maxStream){
        this.maxStream=maxStream;
    }
    public void getMaxStream(int maxStream){
        this.maxStream=maxStream;
    }

    public void setCurrentStream(int currentStream){
        this.currentStream=currentStream;
    }
    public void getCurrentStream(int currentStream) {
        this.currentStream = currentStream;
    }

    @Override
    public String toString() {
        return "ResidualLink: "+
                "[ vS=" + vS.getId() +" "+
                "vE=" + vE.getId() +" "+
                "currentStream=" + currentStream +" "+
                "maxStream=" + maxStream +
                "]\n";
    }
}

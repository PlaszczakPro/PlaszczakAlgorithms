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
    public int getMaxStream(){
        return this.maxStream;
    }

    public void setCurrentStream(int currentStream){
        this.currentStream=currentStream;
    }
    public int getCurrentStream() {
        return this.currentStream;
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

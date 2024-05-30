package GraphFundamentals.GraphFundamentals;

public class ResidualLink extends Link{
    int stream;
    public ResidualLink(Vertex vE, int stream) {
        super(vE);
        this.stream=stream;
    }
    public void setStream(int stream){
        this.stream=stream;
    }

    @Override
    public int getStream(){
        return stream;
    }

    @Override
    public String toString() {
        return "ResidualLink: "+
                "[ vE=" + vE.getId() +
                ", stream=" + stream +
                "]\n";
    }
}
